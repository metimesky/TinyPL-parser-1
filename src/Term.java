/**
 * term - >  factor [ ('* '| '/ ') term ]
 */
class Term {
    Factor f;
    Term t;
    char op;

    public Term() {
        f = new Factor();
        if (Lexer.nextToken == Token.MULT_OP || Lexer.nextToken == Token.DIV_OP) {
            op = Lexer.nextChar;
            Lexer.lex();
            t = new Term();
            Code.gen(Code.opCode(op));
        }
    }
}
