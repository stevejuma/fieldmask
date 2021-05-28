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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, COMMA=8, LPAREN=9, 
		RPAREN=10, RSTART=11, DQUOTE=12, SQUOTE=13, TQUOTE=14, STAR=15, COLON=16, 
		WS=17, PHRASE=18, IDENTIFIER=19, INT=20, FLOAT=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "COMMA", "LPAREN", 
			"RPAREN", "RSTART", "DQUOTE", "SQUOTE", "TQUOTE", "STAR", "COLON", "WS", 
			"PHRASE", "IDENTIFIER", "ESC_CHAR", "LETTER_OR_DIGIT", "DIGITS", "LETTER", 
			"NEGATIVE_SIGN", "NONZERO_DIGIT", "DIGIT", "FRACTIONAL_PART", "INT", 
			"FLOAT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'['", "']'", "'true'", "'false'", "'null'", "'{'", "'}'", "','", 
			"'('", "')'", "'/'", "'\"'", "'''", "'`'", "'*'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "COMMA", "LPAREN", "RPAREN", 
			"RSTART", "DQUOTE", "SQUOTE", "TQUOTE", "STAR", "COLON", "WS", "PHRASE", 
			"IDENTIFIER", "INT", "FLOAT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27\u00c6\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17"+
		"\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\7\23m\n\23\f\23\16"+
		"\23p\13\23\3\23\3\23\3\23\3\23\3\23\7\23w\n\23\f\23\16\23z\13\23\3\23"+
		"\3\23\3\23\3\23\3\23\7\23\u0081\n\23\f\23\16\23\u0084\13\23\3\23\3\23"+
		"\5\23\u0088\n\23\3\24\3\24\7\24\u008c\n\24\f\24\16\24\u008f\13\24\3\25"+
		"\3\25\3\25\3\26\3\26\5\26\u0096\n\26\3\27\3\27\7\27\u009a\n\27\f\27\16"+
		"\27\u009d\13\27\3\27\5\27\u00a0\n\27\3\30\3\30\3\30\3\30\5\30\u00a6\n"+
		"\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\6\34\u00b0\n\34\r\34\16\34"+
		"\u00b1\3\35\5\35\u00b5\n\35\3\35\3\35\5\35\u00b9\n\35\3\35\3\35\7\35\u00bd"+
		"\n\35\f\35\16\35\u00c0\13\35\5\35\u00c2\n\35\3\36\3\36\3\36\2\2\37\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\2+\2-\2/\2\61\2\63\2\65\2\67\29\26;\27\3\2\r\6\2\13"+
		"\f\17\17\"\"\u3002\u3002\4\2$$^^\4\2))^^\4\2^^bb\3\2\62;\4\2\62;aa\6\2"+
		"&&C\\aac|\4\2\2\u0081\ud802\udc01\3\2\ud802\udc01\3\2\udc02\ue001\3\2"+
		"\63;\2\u00d0\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\3=\3\2\2"+
		"\2\5?\3\2\2\2\7A\3\2\2\2\tF\3\2\2\2\13L\3\2\2\2\rQ\3\2\2\2\17S\3\2\2\2"+
		"\21U\3\2\2\2\23W\3\2\2\2\25Y\3\2\2\2\27[\3\2\2\2\31]\3\2\2\2\33_\3\2\2"+
		"\2\35a\3\2\2\2\37c\3\2\2\2!e\3\2\2\2#g\3\2\2\2%\u0087\3\2\2\2\'\u0089"+
		"\3\2\2\2)\u0090\3\2\2\2+\u0095\3\2\2\2-\u0097\3\2\2\2/\u00a5\3\2\2\2\61"+
		"\u00a7\3\2\2\2\63\u00a9\3\2\2\2\65\u00ab\3\2\2\2\67\u00ad\3\2\2\29\u00c1"+
		"\3\2\2\2;\u00c3\3\2\2\2=>\7]\2\2>\4\3\2\2\2?@\7_\2\2@\6\3\2\2\2AB\7v\2"+
		"\2BC\7t\2\2CD\7w\2\2DE\7g\2\2E\b\3\2\2\2FG\7h\2\2GH\7c\2\2HI\7n\2\2IJ"+
		"\7u\2\2JK\7g\2\2K\n\3\2\2\2LM\7p\2\2MN\7w\2\2NO\7n\2\2OP\7n\2\2P\f\3\2"+
		"\2\2QR\7}\2\2R\16\3\2\2\2ST\7\177\2\2T\20\3\2\2\2UV\7.\2\2V\22\3\2\2\2"+
		"WX\7*\2\2X\24\3\2\2\2YZ\7+\2\2Z\26\3\2\2\2[\\\7\61\2\2\\\30\3\2\2\2]^"+
		"\7$\2\2^\32\3\2\2\2_`\7)\2\2`\34\3\2\2\2ab\7b\2\2b\36\3\2\2\2cd\7,\2\2"+
		"d \3\2\2\2ef\7<\2\2f\"\3\2\2\2gh\t\2\2\2h$\3\2\2\2in\5\31\r\2jm\5)\25"+
		"\2km\n\3\2\2lj\3\2\2\2lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2oq\3\2\2"+
		"\2pn\3\2\2\2qr\5\31\r\2r\u0088\3\2\2\2sx\5\33\16\2tw\5)\25\2uw\n\4\2\2"+
		"vt\3\2\2\2vu\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y{\3\2\2\2zx\3\2\2\2"+
		"{|\5\33\16\2|\u0088\3\2\2\2}\u0082\5\35\17\2~\u0081\5)\25\2\177\u0081"+
		"\n\5\2\2\u0080~\3\2\2\2\u0080\177\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080"+
		"\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0085\3\2\2\2\u0084\u0082\3\2\2\2\u0085"+
		"\u0086\5\35\17\2\u0086\u0088\3\2\2\2\u0087i\3\2\2\2\u0087s\3\2\2\2\u0087"+
		"}\3\2\2\2\u0088&\3\2\2\2\u0089\u008d\5/\30\2\u008a\u008c\5+\26\2\u008b"+
		"\u008a\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2"+
		"\2\2\u008e(\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0091\7^\2\2\u0091\u0092"+
		"\13\2\2\2\u0092*\3\2\2\2\u0093\u0096\5/\30\2\u0094\u0096\t\6\2\2\u0095"+
		"\u0093\3\2\2\2\u0095\u0094\3\2\2\2\u0096,\3\2\2\2\u0097\u009f\t\6\2\2"+
		"\u0098\u009a\t\7\2\2\u0099\u0098\3\2\2\2\u009a\u009d\3\2\2\2\u009b\u0099"+
		"\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009e\3\2\2\2\u009d\u009b\3\2\2\2\u009e"+
		"\u00a0\t\6\2\2\u009f\u009b\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0.\3\2\2\2"+
		"\u00a1\u00a6\t\b\2\2\u00a2\u00a6\n\t\2\2\u00a3\u00a4\t\n\2\2\u00a4\u00a6"+
		"\t\13\2\2\u00a5\u00a1\3\2\2\2\u00a5\u00a2\3\2\2\2\u00a5\u00a3\3\2\2\2"+
		"\u00a6\60\3\2\2\2\u00a7\u00a8\7/\2\2\u00a8\62\3\2\2\2\u00a9\u00aa\t\f"+
		"\2\2\u00aa\64\3\2\2\2\u00ab\u00ac\t\6\2\2\u00ac\66\3\2\2\2\u00ad\u00af"+
		"\7\60\2\2\u00ae\u00b0\5\65\33\2\u00af\u00ae\3\2\2\2\u00b0\u00b1\3\2\2"+
		"\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b28\3\2\2\2\u00b3\u00b5"+
		"\5\61\31\2\u00b4\u00b3\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6\3\2\2\2"+
		"\u00b6\u00c2\7\62\2\2\u00b7\u00b9\5\61\31\2\u00b8\u00b7\3\2\2\2\u00b8"+
		"\u00b9\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00be\5\63\32\2\u00bb\u00bd\5"+
		"\65\33\2\u00bc\u00bb\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be"+
		"\u00bf\3\2\2\2\u00bf\u00c2\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00b4\3\2"+
		"\2\2\u00c1\u00b8\3\2\2\2\u00c2:\3\2\2\2\u00c3\u00c4\59\35\2\u00c4\u00c5"+
		"\5\67\34\2\u00c5<\3\2\2\2\24\2lnvx\u0080\u0082\u0087\u008d\u0095\u009b"+
		"\u009f\u00a5\u00b1\u00b4\u00b8\u00be\u00c1\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}