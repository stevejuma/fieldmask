// Generated from FieldsGrammar.g4 by ANTLR 4.8
package ma.ju.fieldmask.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FieldsGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, COMMA=8, LPAREN=9, 
		RPAREN=10, RSTART=11, DQUOTE=12, SQUOTE=13, TQUOTE=14, STAR=15, COLON=16, 
		WS=17, PHRASE=18, IDENTIFIER=19, INT=20, FLOAT=21;
	public static final int
		RULE_mainQ = 0, RULE_clause = 1, RULE_clauseGroup = 2, RULE_alias = 3, 
		RULE_variable = 4, RULE_variableTerm = 5, RULE_variableDeclarator = 6, 
		RULE_variableDeclaratorPath = 7, RULE_expr = 8, RULE_arguments = 9, RULE_argument = 10, 
		RULE_value = 11, RULE_intValue = 12, RULE_floatValue = 13, RULE_name = 14, 
		RULE_booleanValue = 15, RULE_stringValue = 16, RULE_termValue = 17, RULE_nullValue = 18, 
		RULE_listValue = 19, RULE_listItem = 20, RULE_objectValue = 21, RULE_objectField = 22, 
		RULE_ws = 23, RULE_sep = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"mainQ", "clause", "clauseGroup", "alias", "variable", "variableTerm", 
			"variableDeclarator", "variableDeclaratorPath", "expr", "arguments", 
			"argument", "value", "intValue", "floatValue", "name", "booleanValue", 
			"stringValue", "termValue", "nullValue", "listValue", "listItem", "objectValue", 
			"objectField", "ws", "sep"
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

	@Override
	public String getGrammarFileName() { return "FieldsGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FieldsGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class MainQContext extends ParserRuleContext {
		public ClauseGroupContext clauseGroup() {
			return getRuleContext(ClauseGroupContext.class,0);
		}
		public TerminalNode EOF() { return getToken(FieldsGrammarParser.EOF, 0); }
		public SepContext sep() {
			return getRuleContext(SepContext.class,0);
		}
		public TerminalNode RSTART() { return getToken(FieldsGrammarParser.RSTART, 0); }
		public MainQContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainQ; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterMainQ(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitMainQ(this);
		}
	}

	public final MainQContext mainQ() throws RecognitionException {
		MainQContext _localctx = new MainQContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_mainQ);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(50);
				sep();
				}
				break;
			}
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RSTART) {
				{
				setState(53);
				match(RSTART);
				}
			}

			setState(56);
			clauseGroup();
			setState(57);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClauseContext extends ParserRuleContext {
		public VariableDeclaratorContext variableDeclarator() {
			return getRuleContext(VariableDeclaratorContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(FieldsGrammarParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FieldsGrammarParser.COMMA, i);
		}
		public List<ClauseContext> clause() {
			return getRuleContexts(ClauseContext.class);
		}
		public ClauseContext clause(int i) {
			return getRuleContext(ClauseContext.class,i);
		}
		public ClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitClause(this);
		}
	}

	public final ClauseContext clause() throws RecognitionException {
		ClauseContext _localctx = new ClauseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_clause);
		try {
			int _alt;
			setState(68);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(59);
				variableDeclarator();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				expr();
				setState(65);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(61);
						match(COMMA);
						setState(62);
						clause();
						}
						} 
					}
					setState(67);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClauseGroupContext extends ParserRuleContext {
		public List<ClauseContext> clause() {
			return getRuleContexts(ClauseContext.class);
		}
		public ClauseContext clause(int i) {
			return getRuleContext(ClauseContext.class,i);
		}
		public List<SepContext> sep() {
			return getRuleContexts(SepContext.class);
		}
		public SepContext sep(int i) {
			return getRuleContext(SepContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FieldsGrammarParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FieldsGrammarParser.COMMA, i);
		}
		public ClauseGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clauseGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterClauseGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitClauseGroup(this);
		}
	}

	public final ClauseGroupContext clauseGroup() throws RecognitionException {
		ClauseGroupContext _localctx = new ClauseGroupContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_clauseGroup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(70);
				sep();
				}
			}

			setState(73);
			clause();
			setState(75);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(74);
				sep();
				}
				break;
			}
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(77);
				match(COMMA);
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(78);
					sep();
					}
				}

				setState(81);
				clause();
				}
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AliasContext extends ParserRuleContext {
		public VariableTermContext variableTerm() {
			return getRuleContext(VariableTermContext.class,0);
		}
		public TerminalNode COLON() { return getToken(FieldsGrammarParser.COLON, 0); }
		public List<SepContext> sep() {
			return getRuleContexts(SepContext.class);
		}
		public SepContext sep(int i) {
			return getRuleContext(SepContext.class,i);
		}
		public AliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alias; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterAlias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitAlias(this);
		}
	}

	public final AliasContext alias() throws RecognitionException {
		AliasContext _localctx = new AliasContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_alias);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			variableTerm();
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(88);
				sep();
				}
			}

			setState(91);
			match(COLON);
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(92);
				sep();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public VariableTermContext variableTerm() {
			return getRuleContext(VariableTermContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variable);
		try {
			setState(106);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				alias();
				setState(96);
				variableTerm();
				setState(97);
				arguments();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				alias();
				setState(100);
				variableTerm();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(102);
				variableTerm();
				setState(103);
				arguments();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(105);
				variableTerm();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableTermContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(FieldsGrammarParser.IDENTIFIER, 0); }
		public TerminalNode PHRASE() { return getToken(FieldsGrammarParser.PHRASE, 0); }
		public TerminalNode STAR() { return getToken(FieldsGrammarParser.STAR, 0); }
		public VariableTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterVariableTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitVariableTerm(this);
		}
	}

	public final VariableTermContext variableTerm() throws RecognitionException {
		VariableTermContext _localctx = new VariableTermContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_variableTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STAR) | (1L << PHRASE) | (1L << IDENTIFIER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclaratorContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public VariableDeclaratorPathContext variableDeclaratorPath() {
			return getRuleContext(VariableDeclaratorPathContext.class,0);
		}
		public VariableDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterVariableDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitVariableDeclarator(this);
		}
	}

	public final VariableDeclaratorContext variableDeclarator() throws RecognitionException {
		VariableDeclaratorContext _localctx = new VariableDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_variableDeclarator);
		try {
			setState(112);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				variable();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
				variableDeclaratorPath();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclaratorPathContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public List<SepContext> sep() {
			return getRuleContexts(SepContext.class);
		}
		public SepContext sep(int i) {
			return getRuleContext(SepContext.class,i);
		}
		public List<TerminalNode> RSTART() { return getTokens(FieldsGrammarParser.RSTART); }
		public TerminalNode RSTART(int i) {
			return getToken(FieldsGrammarParser.RSTART, i);
		}
		public VariableDeclaratorPathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaratorPath; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterVariableDeclaratorPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitVariableDeclaratorPath(this);
		}
	}

	public final VariableDeclaratorPathContext variableDeclaratorPath() throws RecognitionException {
		VariableDeclaratorPathContext _localctx = new VariableDeclaratorPathContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_variableDeclaratorPath);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			variable();
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(115);
				sep();
				}
			}

			setState(123); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(118);
				match(RSTART);
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(119);
					sep();
					}
				}

				setState(122);
				variable();
				}
				}
				setState(125); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RSTART );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public VariableDeclaratorContext variableDeclarator() {
			return getRuleContext(VariableDeclaratorContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(FieldsGrammarParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(FieldsGrammarParser.RPAREN, 0); }
		public List<ClauseGroupContext> clauseGroup() {
			return getRuleContexts(ClauseGroupContext.class);
		}
		public ClauseGroupContext clauseGroup(int i) {
			return getRuleContext(ClauseGroupContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			variableDeclarator();
			setState(128);
			match(LPAREN);
			setState(130); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(129);
				clauseGroup();
				}
				}
				setState(132); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STAR) | (1L << WS) | (1L << PHRASE) | (1L << IDENTIFIER))) != 0) );
			setState(134);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitArguments(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(T__0);
			setState(138); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(137);
				argument();
				}
				}
				setState(140); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << IDENTIFIER))) != 0) );
			setState(142);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(FieldsGrammarParser.COLON, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public List<SepContext> sep() {
			return getRuleContexts(SepContext.class);
		}
		public SepContext sep(int i) {
			return getRuleContext(SepContext.class,i);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitArgument(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_argument);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			name();
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(145);
				sep();
				}
			}

			setState(148);
			match(COLON);
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(149);
				sep();
				}
			}

			setState(152);
			value();
			setState(153);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public IntValueContext intValue() {
			return getRuleContext(IntValueContext.class,0);
		}
		public FloatValueContext floatValue() {
			return getRuleContext(FloatValueContext.class,0);
		}
		public StringValueContext stringValue() {
			return getRuleContext(StringValueContext.class,0);
		}
		public BooleanValueContext booleanValue() {
			return getRuleContext(BooleanValueContext.class,0);
		}
		public NullValueContext nullValue() {
			return getRuleContext(NullValueContext.class,0);
		}
		public TermValueContext termValue() {
			return getRuleContext(TermValueContext.class,0);
		}
		public ListValueContext listValue() {
			return getRuleContext(ListValueContext.class,0);
		}
		public ObjectValueContext objectValue() {
			return getRuleContext(ObjectValueContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_value);
		try {
			setState(163);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(155);
				intValue();
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(156);
				floatValue();
				}
				break;
			case PHRASE:
				enterOuterAlt(_localctx, 3);
				{
				setState(157);
				stringValue();
				}
				break;
			case T__2:
			case T__3:
				enterOuterAlt(_localctx, 4);
				{
				setState(158);
				booleanValue();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 5);
				{
				setState(159);
				nullValue();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 6);
				{
				setState(160);
				termValue();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 7);
				{
				setState(161);
				listValue();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 8);
				{
				setState(162);
				objectValue();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntValueContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(FieldsGrammarParser.INT, 0); }
		public IntValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterIntValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitIntValue(this);
		}
	}

	public final IntValueContext intValue() throws RecognitionException {
		IntValueContext _localctx = new IntValueContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_intValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FloatValueContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(FieldsGrammarParser.FLOAT, 0); }
		public FloatValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterFloatValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitFloatValue(this);
		}
	}

	public final FloatValueContext floatValue() throws RecognitionException {
		FloatValueContext _localctx = new FloatValueContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_floatValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(FieldsGrammarParser.IDENTIFIER, 0); }
		public BooleanValueContext booleanValue() {
			return getRuleContext(BooleanValueContext.class,0);
		}
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitName(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_name);
		try {
			setState(171);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(169);
				match(IDENTIFIER);
				}
				break;
			case T__2:
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(170);
				booleanValue();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BooleanValueContext extends ParserRuleContext {
		public BooleanValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterBooleanValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitBooleanValue(this);
		}
	}

	public final BooleanValueContext booleanValue() throws RecognitionException {
		BooleanValueContext _localctx = new BooleanValueContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_booleanValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			_la = _input.LA(1);
			if ( !(_la==T__2 || _la==T__3) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringValueContext extends ParserRuleContext {
		public TerminalNode PHRASE() { return getToken(FieldsGrammarParser.PHRASE, 0); }
		public StringValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterStringValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitStringValue(this);
		}
	}

	public final StringValueContext stringValue() throws RecognitionException {
		StringValueContext _localctx = new StringValueContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_stringValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(PHRASE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermValueContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(FieldsGrammarParser.IDENTIFIER, 0); }
		public TermValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterTermValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitTermValue(this);
		}
	}

	public final TermValueContext termValue() throws RecognitionException {
		TermValueContext _localctx = new TermValueContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_termValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NullValueContext extends ParserRuleContext {
		public NullValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nullValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterNullValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitNullValue(this);
		}
	}

	public final NullValueContext nullValue() throws RecognitionException {
		NullValueContext _localctx = new NullValueContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_nullValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListValueContext extends ParserRuleContext {
		public List<ListItemContext> listItem() {
			return getRuleContexts(ListItemContext.class);
		}
		public ListItemContext listItem(int i) {
			return getRuleContext(ListItemContext.class,i);
		}
		public ListValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterListValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitListValue(this);
		}
	}

	public final ListValueContext listValue() throws RecognitionException {
		ListValueContext _localctx = new ListValueContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_listValue);
		int _la;
		try {
			setState(191);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(181);
				match(T__0);
				setState(182);
				match(T__1);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(183);
				match(T__0);
				setState(185); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(184);
					listItem();
					}
					}
					setState(187); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << WS) | (1L << PHRASE) | (1L << IDENTIFIER) | (1L << INT) | (1L << FLOAT))) != 0) );
				setState(189);
				match(T__1);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListItemContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public SepContext sep() {
			return getRuleContext(SepContext.class,0);
		}
		public ListItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterListItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitListItem(this);
		}
	}

	public final ListItemContext listItem() throws RecognitionException {
		ListItemContext _localctx = new ListItemContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_listItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(193);
				sep();
				}
			}

			setState(196);
			value();
			setState(197);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectValueContext extends ParserRuleContext {
		public List<ObjectFieldContext> objectField() {
			return getRuleContexts(ObjectFieldContext.class);
		}
		public ObjectFieldContext objectField(int i) {
			return getRuleContext(ObjectFieldContext.class,i);
		}
		public ObjectValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterObjectValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitObjectValue(this);
		}
	}

	public final ObjectValueContext objectValue() throws RecognitionException {
		ObjectValueContext _localctx = new ObjectValueContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_objectValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(T__5);
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(200);
				objectField();
				}
				}
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(206);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectFieldContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(FieldsGrammarParser.COLON, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public List<SepContext> sep() {
			return getRuleContexts(SepContext.class);
		}
		public SepContext sep(int i) {
			return getRuleContext(SepContext.class,i);
		}
		public ObjectFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterObjectField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitObjectField(this);
		}
	}

	public final ObjectFieldContext objectField() throws RecognitionException {
		ObjectFieldContext _localctx = new ObjectFieldContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_objectField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			name();
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(209);
				sep();
				}
			}

			setState(212);
			match(COLON);
			setState(214);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(213);
				sep();
				}
			}

			setState(216);
			value();
			setState(217);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WsContext extends ParserRuleContext {
		public List<SepContext> sep() {
			return getRuleContexts(SepContext.class);
		}
		public SepContext sep(int i) {
			return getRuleContext(SepContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(FieldsGrammarParser.COMMA, 0); }
		public WsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ws; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterWs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitWs(this);
		}
	}

	public final WsContext ws() throws RecognitionException {
		WsContext _localctx = new WsContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_ws);
		int _la;
		try {
			setState(228);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(220);
				sep();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(222);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(221);
					sep();
					}
				}

				setState(224);
				match(COMMA);
				setState(226);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(225);
					sep();
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SepContext extends ParserRuleContext {
		public List<TerminalNode> WS() { return getTokens(FieldsGrammarParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(FieldsGrammarParser.WS, i);
		}
		public SepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).enterSep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FieldsGrammarListener ) ((FieldsGrammarListener)listener).exitSep(this);
		}
	}

	public final SepContext sep() throws RecognitionException {
		SepContext _localctx = new SepContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_sep);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(231); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(230);
					match(WS);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(233); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\27\u00ee\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\5\2\66\n\2\3\2\5\29\n\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3"+
		"B\n\3\f\3\16\3E\13\3\5\3G\n\3\3\4\5\4J\n\4\3\4\3\4\5\4N\n\4\3\4\3\4\5"+
		"\4R\n\4\3\4\7\4U\n\4\f\4\16\4X\13\4\3\5\3\5\5\5\\\n\5\3\5\3\5\5\5`\n\5"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6m\n\6\3\7\3\7\3\b\3\b"+
		"\5\bs\n\b\3\t\3\t\5\tw\n\t\3\t\3\t\5\t{\n\t\3\t\6\t~\n\t\r\t\16\t\177"+
		"\3\n\3\n\3\n\6\n\u0085\n\n\r\n\16\n\u0086\3\n\3\n\3\13\3\13\6\13\u008d"+
		"\n\13\r\13\16\13\u008e\3\13\3\13\3\f\3\f\5\f\u0095\n\f\3\f\3\f\5\f\u0099"+
		"\n\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00a6\n\r\3\16\3"+
		"\16\3\17\3\17\3\20\3\20\5\20\u00ae\n\20\3\21\3\21\3\22\3\22\3\23\3\23"+
		"\3\24\3\24\3\25\3\25\3\25\3\25\6\25\u00bc\n\25\r\25\16\25\u00bd\3\25\3"+
		"\25\5\25\u00c2\n\25\3\26\5\26\u00c5\n\26\3\26\3\26\3\26\3\27\3\27\7\27"+
		"\u00cc\n\27\f\27\16\27\u00cf\13\27\3\27\3\27\3\30\3\30\5\30\u00d5\n\30"+
		"\3\30\3\30\5\30\u00d9\n\30\3\30\3\30\3\30\3\31\3\31\3\31\5\31\u00e1\n"+
		"\31\3\31\3\31\5\31\u00e5\n\31\5\31\u00e7\n\31\3\32\6\32\u00ea\n\32\r\32"+
		"\16\32\u00eb\3\32\2\2\33\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&("+
		"*,.\60\62\2\4\4\2\21\21\24\25\3\2\5\6\2\u00fc\2\65\3\2\2\2\4F\3\2\2\2"+
		"\6I\3\2\2\2\bY\3\2\2\2\nl\3\2\2\2\fn\3\2\2\2\16r\3\2\2\2\20t\3\2\2\2\22"+
		"\u0081\3\2\2\2\24\u008a\3\2\2\2\26\u0092\3\2\2\2\30\u00a5\3\2\2\2\32\u00a7"+
		"\3\2\2\2\34\u00a9\3\2\2\2\36\u00ad\3\2\2\2 \u00af\3\2\2\2\"\u00b1\3\2"+
		"\2\2$\u00b3\3\2\2\2&\u00b5\3\2\2\2(\u00c1\3\2\2\2*\u00c4\3\2\2\2,\u00c9"+
		"\3\2\2\2.\u00d2\3\2\2\2\60\u00e6\3\2\2\2\62\u00e9\3\2\2\2\64\66\5\62\32"+
		"\2\65\64\3\2\2\2\65\66\3\2\2\2\668\3\2\2\2\679\7\r\2\28\67\3\2\2\289\3"+
		"\2\2\29:\3\2\2\2:;\5\6\4\2;<\7\2\2\3<\3\3\2\2\2=G\5\16\b\2>C\5\22\n\2"+
		"?@\7\n\2\2@B\5\4\3\2A?\3\2\2\2BE\3\2\2\2CA\3\2\2\2CD\3\2\2\2DG\3\2\2\2"+
		"EC\3\2\2\2F=\3\2\2\2F>\3\2\2\2G\5\3\2\2\2HJ\5\62\32\2IH\3\2\2\2IJ\3\2"+
		"\2\2JK\3\2\2\2KM\5\4\3\2LN\5\62\32\2ML\3\2\2\2MN\3\2\2\2NV\3\2\2\2OQ\7"+
		"\n\2\2PR\5\62\32\2QP\3\2\2\2QR\3\2\2\2RS\3\2\2\2SU\5\4\3\2TO\3\2\2\2U"+
		"X\3\2\2\2VT\3\2\2\2VW\3\2\2\2W\7\3\2\2\2XV\3\2\2\2Y[\5\f\7\2Z\\\5\62\32"+
		"\2[Z\3\2\2\2[\\\3\2\2\2\\]\3\2\2\2]_\7\22\2\2^`\5\62\32\2_^\3\2\2\2_`"+
		"\3\2\2\2`\t\3\2\2\2ab\5\b\5\2bc\5\f\7\2cd\5\24\13\2dm\3\2\2\2ef\5\b\5"+
		"\2fg\5\f\7\2gm\3\2\2\2hi\5\f\7\2ij\5\24\13\2jm\3\2\2\2km\5\f\7\2la\3\2"+
		"\2\2le\3\2\2\2lh\3\2\2\2lk\3\2\2\2m\13\3\2\2\2no\t\2\2\2o\r\3\2\2\2ps"+
		"\5\n\6\2qs\5\20\t\2rp\3\2\2\2rq\3\2\2\2s\17\3\2\2\2tv\5\n\6\2uw\5\62\32"+
		"\2vu\3\2\2\2vw\3\2\2\2w}\3\2\2\2xz\7\r\2\2y{\5\62\32\2zy\3\2\2\2z{\3\2"+
		"\2\2{|\3\2\2\2|~\5\n\6\2}x\3\2\2\2~\177\3\2\2\2\177}\3\2\2\2\177\u0080"+
		"\3\2\2\2\u0080\21\3\2\2\2\u0081\u0082\5\16\b\2\u0082\u0084\7\13\2\2\u0083"+
		"\u0085\5\6\4\2\u0084\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0084\3\2"+
		"\2\2\u0086\u0087\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\7\f\2\2\u0089"+
		"\23\3\2\2\2\u008a\u008c\7\3\2\2\u008b\u008d\5\26\f\2\u008c\u008b\3\2\2"+
		"\2\u008d\u008e\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090"+
		"\3\2\2\2\u0090\u0091\7\4\2\2\u0091\25\3\2\2\2\u0092\u0094\5\36\20\2\u0093"+
		"\u0095\5\62\32\2\u0094\u0093\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\3"+
		"\2\2\2\u0096\u0098\7\22\2\2\u0097\u0099\5\62\32\2\u0098\u0097\3\2\2\2"+
		"\u0098\u0099\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\5\30\r\2\u009b\u009c"+
		"\5\60\31\2\u009c\27\3\2\2\2\u009d\u00a6\5\32\16\2\u009e\u00a6\5\34\17"+
		"\2\u009f\u00a6\5\"\22\2\u00a0\u00a6\5 \21\2\u00a1\u00a6\5&\24\2\u00a2"+
		"\u00a6\5$\23\2\u00a3\u00a6\5(\25\2\u00a4\u00a6\5,\27\2\u00a5\u009d\3\2"+
		"\2\2\u00a5\u009e\3\2\2\2\u00a5\u009f\3\2\2\2\u00a5\u00a0\3\2\2\2\u00a5"+
		"\u00a1\3\2\2\2\u00a5\u00a2\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a4\3\2"+
		"\2\2\u00a6\31\3\2\2\2\u00a7\u00a8\7\26\2\2\u00a8\33\3\2\2\2\u00a9\u00aa"+
		"\7\27\2\2\u00aa\35\3\2\2\2\u00ab\u00ae\7\25\2\2\u00ac\u00ae\5 \21\2\u00ad"+
		"\u00ab\3\2\2\2\u00ad\u00ac\3\2\2\2\u00ae\37\3\2\2\2\u00af\u00b0\t\3\2"+
		"\2\u00b0!\3\2\2\2\u00b1\u00b2\7\24\2\2\u00b2#\3\2\2\2\u00b3\u00b4\7\25"+
		"\2\2\u00b4%\3\2\2\2\u00b5\u00b6\7\7\2\2\u00b6\'\3\2\2\2\u00b7\u00b8\7"+
		"\3\2\2\u00b8\u00c2\7\4\2\2\u00b9\u00bb\7\3\2\2\u00ba\u00bc\5*\26\2\u00bb"+
		"\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2"+
		"\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\7\4\2\2\u00c0\u00c2\3\2\2\2\u00c1"+
		"\u00b7\3\2\2\2\u00c1\u00b9\3\2\2\2\u00c2)\3\2\2\2\u00c3\u00c5\5\62\32"+
		"\2\u00c4\u00c3\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7"+
		"\5\30\r\2\u00c7\u00c8\5\60\31\2\u00c8+\3\2\2\2\u00c9\u00cd\7\b\2\2\u00ca"+
		"\u00cc\5.\30\2\u00cb\u00ca\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2"+
		"\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d0\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0"+
		"\u00d1\7\t\2\2\u00d1-\3\2\2\2\u00d2\u00d4\5\36\20\2\u00d3\u00d5\5\62\32"+
		"\2\u00d4\u00d3\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d8"+
		"\7\22\2\2\u00d7\u00d9\5\62\32\2\u00d8\u00d7\3\2\2\2\u00d8\u00d9\3\2\2"+
		"\2\u00d9\u00da\3\2\2\2\u00da\u00db\5\30\r\2\u00db\u00dc\5\60\31\2\u00dc"+
		"/\3\2\2\2\u00dd\u00e7\3\2\2\2\u00de\u00e7\5\62\32\2\u00df\u00e1\5\62\32"+
		"\2\u00e0\u00df\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e4"+
		"\7\n\2\2\u00e3\u00e5\5\62\32\2\u00e4\u00e3\3\2\2\2\u00e4\u00e5\3\2\2\2"+
		"\u00e5\u00e7\3\2\2\2\u00e6\u00dd\3\2\2\2\u00e6\u00de\3\2\2\2\u00e6\u00e0"+
		"\3\2\2\2\u00e7\61\3\2\2\2\u00e8\u00ea\7\23\2\2\u00e9\u00e8\3\2\2\2\u00ea"+
		"\u00eb\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\63\3\2\2"+
		"\2!\658CFIMQV[_lrvz\177\u0086\u008e\u0094\u0098\u00a5\u00ad\u00bd\u00c1"+
		"\u00c4\u00cd\u00d4\u00d8\u00e0\u00e4\u00e6\u00eb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}