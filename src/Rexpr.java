/**
 * rexp - >  expr ('<'| '>'| '=='| '!=') expr
 *
 * @author Wasif Aleem (wasifale@buffalo.edu).
 */
public class Rexpr {
    Expr leftExpr;
    String op;
    Expr rightExpr;

    public Rexpr() {
        leftExpr = new Expr();
        switch (Lexer.nextToken) {
            case Token.LESSER_OP:
                op = "<";
                break;
            case Token.GREATER_OP:
                op = ">";
                break;
            case Token.EQ_OP:
                op = "==";
                break;
            case Token.NOT_EQ:
                op = "!=";
                break;
        }
        Lexer.lex(); // ('<'| '>'| '=='| '!=')
        rightExpr = new Expr();
    }
}
