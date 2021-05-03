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