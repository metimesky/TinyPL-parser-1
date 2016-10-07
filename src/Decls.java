/**
 * decls - >  int idlist ';'
 *
 * @author Wasif Aleem (wasifale@buffalo.edu).
 */
public class Decls {
    IdList idList;

    public Decls() {
        if (Lexer.nextToken == Token.KEY_INT) {
            Lexer.lex();
            idList = new IdList();
            Lexer.lex(); // skip ;
        }
    }
}
