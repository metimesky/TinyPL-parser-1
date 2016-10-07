/**
 * stmt - >  assign ';'| cmpd | cond | loop
 *
 * @author Wasif Aleem (wasifale@buffalo.edu).
 */
public class Stmt {
    Assign a;
    Cmpd s;
    Cond c;
    Loop l;

    public Stmt() {
        switch (Lexer.nextToken) {
            case Token.ID:
                a = new Assign();
                Lexer.lex(); // skip ;
                break;
            case Token.LEFT_BRACE:
                s = new Cmpd();
                break;
            case Token.KEY_IF:
                c = new Cond();
                break;
            case Token.KEY_FOR:
                l = new Loop();
                break;
            default:
                break;
        }
    }
}
