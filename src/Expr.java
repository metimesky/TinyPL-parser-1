/**
 * expr - >  term   [ ('+ '| '- ') expr ]
 */
class Expr {
    Term t;
    Expr e;
    char op;

    public Expr() {
        t = new Term();
        if (Lexer.nextToken == Token.ADD_OP || Lexer.nextToken == Token.SUB_OP) {
            op = Lexer.nextChar;
            Lexer.lex();
            e = new Expr();
            Code.gen(Code.opCode(op));
        }
    }
}
