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

/**
 * ANTLR error listener for checking for field parsing exceptions
 * @property errors The list of errors encountered
 */
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

/**
 * Segment is single property in a given depth for a specified [Path]
 * E.g /a/b/c all the parts `[a,b,c]` are segments. A segment can also
 * optionally have a prefix. The prefix comes before the segment and is
 * denoted by a colon. E.g `me:a/you:b` In this case `me` is an alias for `a`
 * and `you` is an alias of `b`
 *
 * @property value The value of the segment
 * @property alias The alias of the segment if any. Defaults to the value if not provided
 */
data class Segment(val value: String, val alias: String = value, val arguments: Map<String, Any?> = mutableMapOf()) {
    /**
     * A property indicating if the segment has an alias
     */
    val aliased: Boolean = (value != alias) && alias.isNotBlank()

    /**
     * Returns the [alias] if the segment is [aliased] otherwise returns
     * the [value] of the segment
     */
    val field get() = if (aliased) alias else value

    /**
     * Returns the [value] of the segment
     */
    override fun toString() = value
    override fun equals(other: Any?) = (other is Segment?) && value == other?.value
    override fun hashCode() = value.hashCode()

    companion object {
        private val parser = FieldQueryParser()

        /**
         * Creates a list of [Segment] from the provided [segments]
         * @param segments The strings to parse into segments
         * @return The list of parsed segments
         */
        fun from(vararg segments: String) = from(segments.toList())

        /**
         * Creates a list of [Segment] from the provided [segments]
         * @return The list of parsed segments
         */
        fun from(segments: List<String>, separator: String = "/") = segments.map { from(it, separator) }

        /**
         * Creates a [Segment] from the specified string. Throws a [ParseException] if multiple
         * segments are located in the [value]
         * @return The parsed segment
         */
        fun from(value: String, separator: String = "/"): Segment {
            val paths = parser.parse(value, separator)
            if (paths.size != 1 || paths.first().paths.size != 1) {
                throw ParseException("Invalid segment: $value. Returned [$paths] expected single segment")
            }
            return paths.first().paths.first()
        }
    }
}

/**
 * Path is a collection of segments denoting a hierarchy of an object.
 * You can think of this like a directory structure. Each segment after
 * the separator `/` denotes a new depth in the hierarchy.
 * Example:
 *  a/b/c, a/b, a
 * The separator is configurable and defaults to a `/` if not provided.
 * Irrespective of the separator the individual parts will always be stored
 * separately
 *
 * @param paths The list of segments that make up this path
 * @param separator The separator to use when displaying this path
 */
class Path(val paths: MutableList<Segment> = mutableListOf(), val separator: String = "/") {
    /**
     * Creates a [Path] with the specified variable  [segments]
     */
    constructor(vararg segments: String) : this() {
        paths.addAll(segments.toList().map { Segment(it) })
    }

    /**
     * Creates a [Path] with the specified variable [segments]
     */
    constructor(vararg segments: Segment) : this() {
        paths.addAll(segments.toList())
    }

    /**
     * Adds the specified [segment] to the [Path]
     */
    fun add(vararg segment: Segment) = add(segment.toList())

    /**
     * Adds the specified [segments] List to the [Path]
     */
    fun add(segments: List<Segment>) = paths.addAll(segments)

    /**
     * Fetches the depth of this [Path]
     * @return the size/depth of the path
     */
    val size get() = paths.size

    /**
     * Appends the specified [path] to this one, returning this [Path]
     * @return [Path]
     */
    fun append(path: Path): Path {
        paths.addAll(path.paths)
        return this
    }

    fun startsWith(vararg segments: Segment) = startsWith(segments.toList())

    /**
     * Checks if this path starts with the specified [segments]
     * @return boolean indicating if the path starts with the segment
     */
    fun startsWith(segments: List<Segment>): Boolean {
        if (segments.isNotEmpty() && paths.size >= segments.size) {
            for (i in segments.indices) {
                if (segments[i] == paths[i]) continue
                if (segments[i].value == "*" || paths[i].value == "*") continue
                return false
            }
            return true
        }
        return false
    }

    /**
     * Removes the specified [segments] from this path if the path starts
     * with the specified prefix
     * @return a new [Path] object with the prefix trimmed.
     */
    fun trimPrefix(segments: List<Segment>): Path {
        if (startsWith(segments)) {
            return Path(paths.subList(segments.size, paths.size), separator)
        }
        return this
    }

    /**
     * Joins the specified [path] to this one.
     */
    fun join(path: Path) = copy().apply { add(path.paths.toList()) }

    /**
     * Adds the specified [segments] to the path
     */
    fun join(vararg segments: Segment) = copy().apply { add(segments.toList()) }

    /**
     * Creates a new copy of this Path with the specified parameters
     */
    fun copy(separator: String? = null) = Path(paths.toMutableList(), separator ?: this.separator)

    /**
     * Returns the string representation of this [Path]. With the segments
     * concatenated with the [separator]
     */
    override fun toString() = paths.joinToString(separator)
}

/**
 * A simple tree structure for parsing the AST from ANTLR
 * @property field The [Path] tied to this Field
 * @property children the [Field] that have this one as their parent
 */
data class Field(
    var field: Path,
    var children: MutableList<Field> = mutableListOf()
) {
    /**
     * Fetches all the paths of this [Field] and all its descendants
     * @return List of [Path] for this tree
     */
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

/**
 * ANTLR grammar Listener for parsing the AST
 * @property separator The separator to use for the created [Path]
 */
class FieldsQueryListener(private val separator: String) : FieldsGrammarBaseListener() {
    private var roots = Stack<Field>()
    private var root = Field(Path(separator = separator))
    private var arguments = Stack<MutableMap<String, Any?>>()
    private var values = Stack<Any?>()

    /**
     * Fetches the result of a parsing run. Throws [ParseException] if there
     * was an error with the query string
     * @return list of [Path] located from the AST
     */
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

    override fun enterArguments(ctx: FieldsGrammarParser.ArgumentsContext) {
        arguments.push(linkedMapOf())
    }

    override fun exitArguments(ctx: FieldsGrammarParser.ArgumentsContext) {
        val segment = root.field.paths.removeLast()
        root.field.paths.add(segment.copy(arguments = arguments.pop()))
    }

    override fun exitArgument(ctx: FieldsGrammarParser.ArgumentContext) {
        arguments.peek()[ctx.name().text] = values.pop()
    }

    override fun enterIntValue(ctx: FieldsGrammarParser.IntValueContext) {
        values.push(ctx.INT().text.toInt())
    }

    override fun enterFloatValue(ctx: FieldsGrammarParser.FloatValueContext) {
        values.push(ctx.FLOAT().text.toDouble())
    }

    override fun enterStringValue(ctx: FieldsGrammarParser.StringValueContext) {
        val text = ctx.PHRASE().text
        values.push(FieldQueryParser.discardEscapeChar(text.substring(1, text.length - 1)))
    }

    override fun enterBooleanValue(ctx: FieldsGrammarParser.BooleanValueContext) {
        values.push(ctx.text == "true")
    }

    override fun enterNullValue(ctx: FieldsGrammarParser.NullValueContext) {
        values.push(null)
    }

    override fun enterTermValue(ctx: FieldsGrammarParser.TermValueContext) {
        values.push(ctx.IDENTIFIER().text)
    }

    override fun enterObjectValue(ctx: FieldsGrammarParser.ObjectValueContext) {
        values.push(linkedMapOf<String, Any?>())
    }

    override fun exitObjectField(ctx: FieldsGrammarParser.ObjectFieldContext) {
        (values.peek() as MutableMap<String, Any?>)[ctx.name().text] = values.pop()
    }

    override fun enterListValue(ctx: FieldsGrammarParser.ListValueContext) {
        values.push(mutableListOf<Any?>())
    }

    override fun exitListItem(ctx: FieldsGrammarParser.ListItemContext) {
        val item = values.pop()
        (values.peek() as MutableList<Any?>).add(item)
    }
}
