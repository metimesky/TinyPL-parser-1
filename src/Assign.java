/**
 * assign - >  id '=' expr
 *
 * @author Wasif Aleem (wasifale@buffalo.edu).
 */
public class Assign {
    char id;
    Expr e;

    public Assign() {
        id = Lexer.ident;
        if (Lexer.nextToken == Token.ID) {
            Lexer.lex(); // id
            if (Lexer.nextToken == Token.ASSIGN_OP) {
                Lexer.lex(); // =
                e = new Expr();
                Code.gen(Code.storeId(id));
            }
        }
    }
}
