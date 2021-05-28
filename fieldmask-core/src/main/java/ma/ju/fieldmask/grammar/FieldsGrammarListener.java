// Generated from FieldsGrammar.g4 by ANTLR 4.8
package ma.ju.fieldmask.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FieldsGrammarParser}.
 */
public interface FieldsGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#mainQ}.
	 * @param ctx the parse tree
	 */
	void enterMainQ(FieldsGrammarParser.MainQContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#mainQ}.
	 * @param ctx the parse tree
	 */
	void exitMainQ(FieldsGrammarParser.MainQContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#clause}.
	 * @param ctx the parse tree
	 */
	void enterClause(FieldsGrammarParser.ClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#clause}.
	 * @param ctx the parse tree
	 */
	void exitClause(FieldsGrammarParser.ClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#clauseGroup}.
	 * @param ctx the parse tree
	 */
	void enterClauseGroup(FieldsGrammarParser.ClauseGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#clauseGroup}.
	 * @param ctx the parse tree
	 */
	void exitClauseGroup(FieldsGrammarParser.ClauseGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#alias}.
	 * @param ctx the parse tree
	 */
	void enterAlias(FieldsGrammarParser.AliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#alias}.
	 * @param ctx the parse tree
	 */
	void exitAlias(FieldsGrammarParser.AliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(FieldsGrammarParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(FieldsGrammarParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#variableTerm}.
	 * @param ctx the parse tree
	 */
	void enterVariableTerm(FieldsGrammarParser.VariableTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#variableTerm}.
	 * @param ctx the parse tree
	 */
	void exitVariableTerm(FieldsGrammarParser.VariableTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarator(FieldsGrammarParser.VariableDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarator(FieldsGrammarParser.VariableDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#variableDeclaratorPath}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorPath(FieldsGrammarParser.VariableDeclaratorPathContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#variableDeclaratorPath}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorPath(FieldsGrammarParser.VariableDeclaratorPathContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(FieldsGrammarParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(FieldsGrammarParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(FieldsGrammarParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(FieldsGrammarParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(FieldsGrammarParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(FieldsGrammarParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(FieldsGrammarParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(FieldsGrammarParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#intValue}.
	 * @param ctx the parse tree
	 */
	void enterIntValue(FieldsGrammarParser.IntValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#intValue}.
	 * @param ctx the parse tree
	 */
	void exitIntValue(FieldsGrammarParser.IntValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#floatValue}.
	 * @param ctx the parse tree
	 */
	void enterFloatValue(FieldsGrammarParser.FloatValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#floatValue}.
	 * @param ctx the parse tree
	 */
	void exitFloatValue(FieldsGrammarParser.FloatValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(FieldsGrammarParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(FieldsGrammarParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#booleanValue}.
	 * @param ctx the parse tree
	 */
	void enterBooleanValue(FieldsGrammarParser.BooleanValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#booleanValue}.
	 * @param ctx the parse tree
	 */
	void exitBooleanValue(FieldsGrammarParser.BooleanValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#stringValue}.
	 * @param ctx the parse tree
	 */
	void enterStringValue(FieldsGrammarParser.StringValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#stringValue}.
	 * @param ctx the parse tree
	 */
	void exitStringValue(FieldsGrammarParser.StringValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#termValue}.
	 * @param ctx the parse tree
	 */
	void enterTermValue(FieldsGrammarParser.TermValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#termValue}.
	 * @param ctx the parse tree
	 */
	void exitTermValue(FieldsGrammarParser.TermValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#nullValue}.
	 * @param ctx the parse tree
	 */
	void enterNullValue(FieldsGrammarParser.NullValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#nullValue}.
	 * @param ctx the parse tree
	 */
	void exitNullValue(FieldsGrammarParser.NullValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#listValue}.
	 * @param ctx the parse tree
	 */
	void enterListValue(FieldsGrammarParser.ListValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#listValue}.
	 * @param ctx the parse tree
	 */
	void exitListValue(FieldsGrammarParser.ListValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#listItem}.
	 * @param ctx the parse tree
	 */
	void enterListItem(FieldsGrammarParser.ListItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#listItem}.
	 * @param ctx the parse tree
	 */
	void exitListItem(FieldsGrammarParser.ListItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#objectValue}.
	 * @param ctx the parse tree
	 */
	void enterObjectValue(FieldsGrammarParser.ObjectValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#objectValue}.
	 * @param ctx the parse tree
	 */
	void exitObjectValue(FieldsGrammarParser.ObjectValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#objectField}.
	 * @param ctx the parse tree
	 */
	void enterObjectField(FieldsGrammarParser.ObjectFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#objectField}.
	 * @param ctx the parse tree
	 */
	void exitObjectField(FieldsGrammarParser.ObjectFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#ws}.
	 * @param ctx the parse tree
	 */
	void enterWs(FieldsGrammarParser.WsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#ws}.
	 * @param ctx the parse tree
	 */
	void exitWs(FieldsGrammarParser.WsContext ctx);
	/**
	 * Enter a parse tree produced by {@link FieldsGrammarParser#sep}.
	 * @param ctx the parse tree
	 */
	void enterSep(FieldsGrammarParser.SepContext ctx);
	/**
	 * Exit a parse tree produced by {@link FieldsGrammarParser#sep}.
	 * @param ctx the parse tree
	 */
	void exitSep(FieldsGrammarParser.SepContext ctx);
}