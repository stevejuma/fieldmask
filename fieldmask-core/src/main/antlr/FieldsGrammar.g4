grammar FieldsGrammar;
// Parses grammar such as person/name(age)
options {
  language = Java;
}

mainQ
    : sep? RSTART? clauseGroup EOF
    ;

clause
   : variableDeclarator
   | expr (COMMA clause)*
   ;

clauseGroup
    : sep? clause sep? (COMMA sep? clause)*
    ;

alias
    : variableTerm sep? COLON sep?
    ;

variable
  : alias variableTerm arguments
  | alias variableTerm
  | variableTerm arguments
  | variableTerm
  ;

variableTerm
    : IDENTIFIER
    | PHRASE
    | STAR
    ;

variableDeclarator
    : variable
    | variableDeclaratorPath
    ;

variableDeclaratorPath
    : variable sep? (RSTART sep? variable)+
    ;

expr
    : variableDeclarator LPAREN clauseGroup+ RPAREN
    ;

arguments: '[' argument+ ']';
argument: name sep? ':' sep? value ws;

value:
	intValue
	| floatValue
	| stringValue
	| booleanValue
	| nullValue
    | termValue
	| listValue
	| objectValue
   ;

intValue: INT;
floatValue: FLOAT;
name: IDENTIFIER | booleanValue;
booleanValue
    : 'true'
    | 'false'
    ;
stringValue: PHRASE;
termValue: IDENTIFIER;
nullValue: 'null';
listValue: '[' ']'
    | '[' listItem+ ']'
    ;
listItem: sep? value ws;
objectValue: '{' objectField* '}';
objectField: name sep? ':' sep? value ws;
ws:
    | sep
    | sep? ',' sep?;

/* ================================================================
 * =                     LEXER                                    =
 * ================================================================
 */
COMMA   :    ',';
LPAREN  :    '(';
RPAREN  :    ')';
RSTART  :    '/';
DQUOTE  :    '"';
SQUOTE  :    '\'';
TQUOTE  :    '`';
STAR    :    '*';
COLON   :    ':';

sep : WS+;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        | '\u3000'
        )
    ;

PHRASE
  :  DQUOTE (ESC_CHAR|~('"'|'\\'))* DQUOTE
  |  SQUOTE (ESC_CHAR|~('\''|'\\'))* SQUOTE
  |  TQUOTE (ESC_CHAR|~('`'|'\\'))* TQUOTE
  ;

IDENTIFIER:  LETTER LETTER_OR_DIGIT*;

fragment ESC_CHAR:  '\\' .;

fragment LETTER_OR_DIGIT
    : LETTER
    | [0-9]
    ;

fragment DIGITS
    : [0-9] ([0-9_]* [0-9])?
    ;

fragment LETTER
    : [a-zA-Z$_] // these are the "java letters" below 0x7F
    | ~[\u0000-\u007F\uD800-\uDBFF] // covers all characters above 0x7F which are not a surrogate
    | [\uD800-\uDBFF] [\uDC00-\uDFFF] // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
    ;

fragment NEGATIVE_SIGN: '-';

fragment NONZERO_DIGIT: [1-9];
fragment DIGIT: [0-9];
fragment FRACTIONAL_PART: '.' DIGIT+;

INT: NEGATIVE_SIGN? '0'
    | NEGATIVE_SIGN? NONZERO_DIGIT DIGIT*
    ;

FLOAT: INT FRACTIONAL_PART;