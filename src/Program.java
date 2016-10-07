/**
 * program - >  decls stmts end
 *
 * @author Wasif Aleem (wasifale@buffalo.edu).
 */
public class Program {
    Decls decls;
    Stmts stmts;

    public Program() {
        decls = new Decls();
        stmts = new Stmts();
        if (Lexer.nextToken == Token.KEY_END) {
            Code.gen(Code.end());
        }
    }
}
