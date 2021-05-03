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
		COMMA=1, LPAREN=2, RPAREN=3, RSTART=4, DQUOTE=5, SQUOTE=6, TQUOTE=7, STAR=8, 
		COLON=9, WS=10, PHRASE=11, IDENTIFIER=12;
	public static final int
		RULE_mainQ = 0, RULE_clause = 1, RULE_clauseGroup = 2, RULE_alias = 3, 
		RULE_variable = 4, RULE_variableTerm = 5, RULE_variableDeclarator = 6, 
		RULE_variableDeclaratorPath = 7, RULE_expr = 8, RULE_sep = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"mainQ", "clause", "clauseGroup", "alias", "variable", "variableTerm", 
			"variableDeclarator", "variableDeclaratorPath", "expr", "sep"
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
			setState(21);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(20);
				sep();
				}
				break;
			}
			setState(24);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RSTART) {
				{
				setState(23);
				match(RSTART);
				}
			}

			setState(26);
			clauseGroup();
			setState(27);
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
			setState(38);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(29);
				variableDeclarator();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(30);
				expr();
				setState(35);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(31);
						match(COMMA);
						setState(32);
						clause();
						}
						} 
					}
					setState(37);
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
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(40);
				sep();
				}
			}

			setState(43);
			clause();
			setState(45);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(44);
				sep();
				}
				break;
			}
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(47);
				match(COMMA);
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(48);
					sep();
					}
				}

				setState(51);
				clause();
				}
				}
				setState(56);
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
			setState(57);
			variableTerm();
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(58);
				sep();
				}
			}

			setState(61);
			match(COLON);
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(62);
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
			setState(69);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				alias();
				setState(66);
				variableTerm();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(68);
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
			setState(71);
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
			setState(75);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(73);
				variable();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
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
			setState(77);
			variable();
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(78);
				sep();
				}
			}

			setState(86); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(81);
				match(RSTART);
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(82);
					sep();
					}
				}

				setState(85);
				variable();
				}
				}
				setState(88); 
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
			setState(90);
			variableDeclarator();
			setState(91);
			match(LPAREN);
			setState(93); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(92);
				clauseGroup();
				}
				}
				setState(95); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STAR) | (1L << WS) | (1L << PHRASE) | (1L << IDENTIFIER))) != 0) );
			setState(97);
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
		enterRule(_localctx, 18, RULE_sep);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(100); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(99);
					match(WS);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(102); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\16k\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3"+
		"\2\5\2\30\n\2\3\2\5\2\33\n\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3$\n\3\f\3"+
		"\16\3\'\13\3\5\3)\n\3\3\4\5\4,\n\4\3\4\3\4\5\4\60\n\4\3\4\3\4\5\4\64\n"+
		"\4\3\4\7\4\67\n\4\f\4\16\4:\13\4\3\5\3\5\5\5>\n\5\3\5\3\5\5\5B\n\5\3\6"+
		"\3\6\3\6\3\6\5\6H\n\6\3\7\3\7\3\b\3\b\5\bN\n\b\3\t\3\t\5\tR\n\t\3\t\3"+
		"\t\5\tV\n\t\3\t\6\tY\n\t\r\t\16\tZ\3\n\3\n\3\n\6\n`\n\n\r\n\16\na\3\n"+
		"\3\n\3\13\6\13g\n\13\r\13\16\13h\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24\2"+
		"\3\4\2\n\n\r\16\2q\2\27\3\2\2\2\4(\3\2\2\2\6+\3\2\2\2\b;\3\2\2\2\nG\3"+
		"\2\2\2\fI\3\2\2\2\16M\3\2\2\2\20O\3\2\2\2\22\\\3\2\2\2\24f\3\2\2\2\26"+
		"\30\5\24\13\2\27\26\3\2\2\2\27\30\3\2\2\2\30\32\3\2\2\2\31\33\7\6\2\2"+
		"\32\31\3\2\2\2\32\33\3\2\2\2\33\34\3\2\2\2\34\35\5\6\4\2\35\36\7\2\2\3"+
		"\36\3\3\2\2\2\37)\5\16\b\2 %\5\22\n\2!\"\7\3\2\2\"$\5\4\3\2#!\3\2\2\2"+
		"$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&)\3\2\2\2\'%\3\2\2\2(\37\3\2\2\2( \3\2"+
		"\2\2)\5\3\2\2\2*,\5\24\13\2+*\3\2\2\2+,\3\2\2\2,-\3\2\2\2-/\5\4\3\2.\60"+
		"\5\24\13\2/.\3\2\2\2/\60\3\2\2\2\608\3\2\2\2\61\63\7\3\2\2\62\64\5\24"+
		"\13\2\63\62\3\2\2\2\63\64\3\2\2\2\64\65\3\2\2\2\65\67\5\4\3\2\66\61\3"+
		"\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29\7\3\2\2\2:8\3\2\2\2;=\5\f\7"+
		"\2<>\5\24\13\2=<\3\2\2\2=>\3\2\2\2>?\3\2\2\2?A\7\13\2\2@B\5\24\13\2A@"+
		"\3\2\2\2AB\3\2\2\2B\t\3\2\2\2CD\5\b\5\2DE\5\f\7\2EH\3\2\2\2FH\5\f\7\2"+
		"GC\3\2\2\2GF\3\2\2\2H\13\3\2\2\2IJ\t\2\2\2J\r\3\2\2\2KN\5\n\6\2LN\5\20"+
		"\t\2MK\3\2\2\2ML\3\2\2\2N\17\3\2\2\2OQ\5\n\6\2PR\5\24\13\2QP\3\2\2\2Q"+
		"R\3\2\2\2RX\3\2\2\2SU\7\6\2\2TV\5\24\13\2UT\3\2\2\2UV\3\2\2\2VW\3\2\2"+
		"\2WY\5\n\6\2XS\3\2\2\2YZ\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[\21\3\2\2\2\\]\5"+
		"\16\b\2]_\7\4\2\2^`\5\6\4\2_^\3\2\2\2`a\3\2\2\2a_\3\2\2\2ab\3\2\2\2bc"+
		"\3\2\2\2cd\7\5\2\2d\23\3\2\2\2eg\7\f\2\2fe\3\2\2\2gh\3\2\2\2hf\3\2\2\2"+
		"hi\3\2\2\2i\25\3\2\2\2\23\27\32%(+/\638=AGMQUZah";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}