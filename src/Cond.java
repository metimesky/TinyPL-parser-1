/**
 * if '(' rexp ')' stmt [ else stmt ]
 * @author Wasif Aleem (wasifale@buffalo.edu).
 */
public class Cond {
    Rexpr rexpr;
    Stmt ifBody;
    Stmt elseBody;

    public Cond() {
        if (Lexer.nextToken == Token.KEY_IF) {
            Lexer.lex(); // if
            if (Lexer.nextToken == Token.LEFT_PAREN) {
                Lexer.lex(); // (
                rexpr = new Rexpr();
                Lexer.lex(); // )
                final int ifIndex = Code.gen(Code.ifPlaceHolder(rexpr.op));
                ifBody = new Stmt();
                if (Lexer.nextToken == Token.KEY_ELSE) {
                    final int gotoIndex = Code.gen(Code.gotoPlaceHolder());
                    Code.appendCurrentAddress(ifIndex);
                    Lexer.lex();
                    elseBody = new Stmt();
                    Code.appendCurrentAddress(gotoIndex);
                } else {
                    Code.appendCurrentAddress(ifIndex);
                }
            }
        }
    }
}
