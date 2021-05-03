// Generated from FieldsGrammar.g4 by ANTLR 4.8
package ma.ju.fieldmask.grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FieldsGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMA=1, LPAREN=2, RPAREN=3, RSTART=4, DQUOTE=5, SQUOTE=6, TQUOTE=7, STAR=8, 
		COLON=9, WS=10, PHRASE=11, IDENTIFIER=12;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"COMMA", "LPAREN", "RPAREN", "RSTART", "DQUOTE", "SQUOTE", "TQUOTE", 
			"STAR", "COLON", "WS", "PHRASE", "IDENTIFIER", "ESC_CHAR", "LETTER_OR_DIGIT", 
			"DIGITS", "LETTER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "'('", "')'", "'/'", "'\"'", "'''", "'`'", "'*'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COMMA", "LPAREN", "RPAREN", "RSTART", "DQUOTE", "SQUOTE", "TQUOTE", 
			"STAR", "COLON", "WS", "PHRASE", "IDENTIFIER"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public FieldsGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FieldsGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\16r\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3"+
		"\13\3\f\3\f\3\f\6\f;\n\f\r\f\16\f<\3\f\3\f\3\f\3\f\3\f\6\fD\n\f\r\f\16"+
		"\fE\3\f\3\f\3\f\3\f\3\f\6\fM\n\f\r\f\16\fN\3\f\3\f\5\fS\n\f\3\r\3\r\7"+
		"\rW\n\r\f\r\16\rZ\13\r\3\16\3\16\3\16\3\17\3\17\5\17a\n\17\3\20\3\20\7"+
		"\20e\n\20\f\20\16\20h\13\20\3\20\5\20k\n\20\3\21\3\21\3\21\3\21\5\21q"+
		"\n\21\2\2\22\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\2\35\2\37\2!\2\3\2\f\6\2\13\f\17\17\"\"\u3002\u3002\4\2$$^^\4\2))"+
		"^^\4\2^^bb\3\2\62;\4\2\62;aa\6\2&&C\\aac|\4\2\2\u0081\ud802\udc01\3\2"+
		"\ud802\udc01\3\2\udc02\ue001\2{\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\3#\3\2\2\2\5%\3\2\2\2\7\'"+
		"\3\2\2\2\t)\3\2\2\2\13+\3\2\2\2\r-\3\2\2\2\17/\3\2\2\2\21\61\3\2\2\2\23"+
		"\63\3\2\2\2\25\65\3\2\2\2\27R\3\2\2\2\31T\3\2\2\2\33[\3\2\2\2\35`\3\2"+
		"\2\2\37b\3\2\2\2!p\3\2\2\2#$\7.\2\2$\4\3\2\2\2%&\7*\2\2&\6\3\2\2\2\'("+
		"\7+\2\2(\b\3\2\2\2)*\7\61\2\2*\n\3\2\2\2+,\7$\2\2,\f\3\2\2\2-.\7)\2\2"+
		".\16\3\2\2\2/\60\7b\2\2\60\20\3\2\2\2\61\62\7,\2\2\62\22\3\2\2\2\63\64"+
		"\7<\2\2\64\24\3\2\2\2\65\66\t\2\2\2\66\26\3\2\2\2\67:\5\13\6\28;\5\33"+
		"\16\29;\n\3\2\2:8\3\2\2\2:9\3\2\2\2;<\3\2\2\2<:\3\2\2\2<=\3\2\2\2=>\3"+
		"\2\2\2>?\5\13\6\2?S\3\2\2\2@C\5\r\7\2AD\5\33\16\2BD\n\4\2\2CA\3\2\2\2"+
		"CB\3\2\2\2DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2FG\3\2\2\2GH\5\r\7\2HS\3\2\2\2"+
		"IL\5\17\b\2JM\5\33\16\2KM\n\5\2\2LJ\3\2\2\2LK\3\2\2\2MN\3\2\2\2NL\3\2"+
		"\2\2NO\3\2\2\2OP\3\2\2\2PQ\5\17\b\2QS\3\2\2\2R\67\3\2\2\2R@\3\2\2\2RI"+
		"\3\2\2\2S\30\3\2\2\2TX\5!\21\2UW\5\35\17\2VU\3\2\2\2WZ\3\2\2\2XV\3\2\2"+
		"\2XY\3\2\2\2Y\32\3\2\2\2ZX\3\2\2\2[\\\7^\2\2\\]\13\2\2\2]\34\3\2\2\2^"+
		"a\5!\21\2_a\t\6\2\2`^\3\2\2\2`_\3\2\2\2a\36\3\2\2\2bj\t\6\2\2ce\t\7\2"+
		"\2dc\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2gi\3\2\2\2hf\3\2\2\2ik\t\6\2"+
		"\2jf\3\2\2\2jk\3\2\2\2k \3\2\2\2lq\t\b\2\2mq\n\t\2\2no\t\n\2\2oq\t\13"+
		"\2\2pl\3\2\2\2pm\3\2\2\2pn\3\2\2\2q\"\3\2\2\2\17\2:<CELNRX`fjp\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}