package ma.ju.fieldmask.core

import ma.ju.fieldmask.grammar.FieldsGrammarBaseListener
import ma.ju.fieldmask.grammar.FieldsGrammarLexer
import ma.ju.fieldmask.grammar.FieldsGrammarParser
import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.Recognizer
import org.antlr.v4.runtime.tree.ParseTreeWalker
import java.util.Stack

class ParseException(message: String) : RuntimeException(message)

class SyntaxErrorListener(val errors: MutableList<String> = mutableListOf()) : BaseErrorListener() {
    override fun syntaxError(
        recognizer: Recognizer<*, *>?,
        offendingSymbol: Any?,
        line: Int,
        charPositionInLine: Int,
        msg: String?,
        e: RecognitionException?
    ) {
        errors.add("$line:$charPositionInLine $msg")
    }
}

data class Segment(val value: String, val alias: String = value) {
    val aliased: Boolean = (value != alias) && alias.isNotBlank()
    val field get() = if (aliased) alias else value

    override fun toString() = value
    override fun equals(other: Any?) = (other is Segment?) && value == other?.value
    override fun hashCode() = value.hashCode()

    companion object {
        private val parser = FieldQueryParser()
        fun from(vararg paths: String) = from(paths.toList())

        fun from(paths: List<String>, separator: String = "/") = paths.map { from(it, separator) }

        fun from(value: String, separator: String = "/"): Segment {
            val paths = parser.parse(value, separator)
            if (paths.size != 1 || paths.first().paths.size != 1) {
                throw IllegalArgumentException("Invalid segment: $value. Returned [$paths] expected single segment")
            }
            return paths.first().paths.first()
        }
    }
}

class Path(val paths: MutableList<Segment> = mutableListOf(), private val separator: String = "/") {
    fun add(vararg path: Segment) = add(path.toList())
    fun add(path: List<Segment>) = paths.addAll(path)

    constructor(vararg segments: String) : this() {
        paths.addAll(segments.toList().map { Segment(it) })
    }

    constructor(vararg segments: Segment) : this() {
        paths.addAll(segments.toList())
    }

    val size get() = paths.size

    fun append(path: Path): Path {
        paths.addAll(path.paths)
        return this
    }

    fun startsWith(path: List<Segment>): Boolean {
        if (path.isNotEmpty() && paths.size >= path.size) {
            return paths.subList(0, path.size) == path
        }
        return false
    }

    fun trimPrefix(path: List<Segment>): Path {
        if (startsWith(path)) {
            return Path(paths.subList(path.size, paths.size), separator)
        }
        return this
    }

    fun join(path: Path) = copy().apply { add(path.paths.toList()) }
    fun join(vararg path: Segment) = copy().apply { add(path.toList()) }

    fun copy() = Path(paths.toMutableList(), separator)

    override fun toString() = paths.joinToString(separator)
}

data class Field(
    var field: Path,
    var children: MutableList<Field> = mutableListOf()
) {
    fun fields(): MutableList<Path> {
        var paths = mutableListOf<Path>()
        if (children.isEmpty()) {
            paths.add(field)
        } else {
            children.forEach { c ->
                c.fields().forEach {
                    paths.add(field.copy().append(it))
                }
            }
        }
        return paths
    }
}

class FieldQueryParser {
    fun parse(query: String, separator: String = "/"): List<Path> {
        val parser =
            FieldsGrammarParser(
                CommonTokenStream(
                    FieldsGrammarLexer(
                        CharStreams.fromString(query)
                    )
                )
            )
        val errorListener = SyntaxErrorListener()
        parser.removeErrorListeners()
        parser.addErrorListener(errorListener)
        val tree = parser.mainQ()
        if (errorListener.errors.isNotEmpty()) {
            throw ParseException("error parsing fields [$query]: ${errorListener.errors.joinToString("\n")}")
        }
        val extractor = FieldsQueryListener(separator)
        ParseTreeWalker.DEFAULT.walk(extractor, tree)
        return extractor.fields()
    }

    companion object {
        /**
         * Returns the numeric value of the hexadecimal character
         */
        @Throws(ParseException::class)
        private fun hexToInt(c: Char): Int {
            return when (c) {
                in '0'..'9' -> {
                    c - '0'
                }
                in 'a'..'f' -> {
                    c - 'a' + 10
                }
                in 'A'..'F' -> {
                    c - 'A' + 10
                }
                else -> {
                    throw ParseException("None-hex character in unicode escape sequence: $c")
                }
            }
        }

        /**
         * Returns a String where the escape char has been removed, or kept only
         * once if there was a double escape.
         *
         * Supports escaped unicode characters, e. g. translates
         * `\\u0041` to `A`.
         *
         */
        @Throws(ParseException::class)
        fun discardEscapeChar(input: String): String {
            // Create char array to hold unescaped char sequence
            val output = CharArray(input.length)

            // The length of the output can be less than the input
            // due to discarded escape chars. This variable holds
            // the actual length of the output
            var length = 0

            // We remember whether the last processed character was
            // an escape character
            var lastCharWasEscapeChar = false

            // The multiplier the current unicode digit must be multiplied with.
            // E. g. the first digit must be multiplied with 16^3, the second with 16^2...
            var codePointMultiplier = 0

            // Used to calculate the codepoint of the escaped unicode character
            var codePoint = 0
            for (element in input) {
                if (codePointMultiplier > 0) {
                    codePoint += hexToInt(element) * codePointMultiplier
                    codePointMultiplier = codePointMultiplier ushr 4
                    if (codePointMultiplier == 0) {
                        output[length++] = codePoint.toChar()
                        codePoint = 0
                    }
                } else if (lastCharWasEscapeChar) {
                    if (element == 'u') {
                        // found an escaped unicode character
                        codePointMultiplier = 16 * 16 * 16
                    } else {
                        // this character was escaped
                        // but it is necessary to keep the BACKSLASH for "*" and "?", because otherwise it is
                        // not possible to decide whether to restore it or not e.g. hel*o and hel?o.
                        // if (element == '*' || element == '?') {
                        //     output[length] = '\u005c'
                        //     length++
                        // }
                        output[length] = element
                        length++
                    }
                    lastCharWasEscapeChar = false
                } else {
                    if (element == '\u005c') {
                        lastCharWasEscapeChar = true
                    } else {
                        output[length] = element
                        length++
                    }
                }
            }
            if (codePointMultiplier > 0) {
                throw ParseException("Truncated unicode escape sequence.")
            }
            if (lastCharWasEscapeChar) {
                throw ParseException("Term can not end with escape character.")
            }
            return String(output, 0, length)
        }
    }
}

class FieldsQueryListener(private val separator: String) : FieldsGrammarBaseListener() {
    private var roots = Stack<Field>()
    private var root = Field(Path(separator = separator))

    fun fields(): List<Path> {
        if (roots.isEmpty()) {
            return root.fields()
        }
        throw ParseException("unable to parse the query. no results")
    }

    override fun enterClause(ctx: FieldsGrammarParser.ClauseContext) {
        roots.add(root)
        root = Field(Path(separator = separator))
        roots.peek().children.add(root)
    }

    override fun exitClause(ctx: FieldsGrammarParser.ClauseContext) {
        root = roots.pop()
    }

    override fun enterExpr(ctx: FieldsGrammarParser.ExprContext) {
        roots.add(root)
        root = Field(Path(separator = separator))
        roots.peek().children.add(root)
    }

    override fun exitExpr(ctx: FieldsGrammarParser.ExprContext) {
        root = roots.pop()
    }

    override fun enterVariable(ctx: FieldsGrammarParser.VariableContext) {
        var segment = Segment("")
        ctx.variableTerm().IDENTIFIER()?.let {
            segment = segment.copy(value = it.text)
        }
        ctx.variableTerm().STAR()?.let { segment = segment.copy(value = it.text) }
        ctx.variableTerm().PHRASE()?.let {
            segment = segment.copy(value = FieldQueryParser.discardEscapeChar(it.text.substring(1, it.text.length - 1)))
        }
        ctx.alias()?.variableTerm()?.let { term ->
            term.IDENTIFIER()?.let { segment = segment.copy(alias = it.text) }
            term.STAR()?.let { segment = segment.copy(alias = it.text) }
            term.PHRASE()?.let {
                segment =
                    segment.copy(value = FieldQueryParser.discardEscapeChar(it.text.substring(1, it.text.length - 1)))
            }
        }
        root.field.add(segment)
    }
}
