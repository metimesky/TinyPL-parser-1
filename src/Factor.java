/**
 * factor - >  int_lit | id | '('expr ')'
 */
class Factor {
    Expr e;
    int i;

    public Factor() {
        switch (Lexer.nextToken) {
            case Token.INT_LIT: // int_lit
                i = Lexer.intValue;
                Code.gen(Code.intCode(i));
                Lexer.lex();
                break;
            case Token.ID:
                Code.gen(Code.loadId(Lexer.ident));
                Lexer.lex();
                break;
            case Token.LEFT_PAREN: // '('
                Lexer.lex();
                e = new Expr();
                Lexer.lex(); // skip over ')'
                break;
            default:
                break;
        }
    }
}
