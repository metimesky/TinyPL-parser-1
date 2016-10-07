/**
 * idlist - >  id [', 'idlist ]
 *
 * @author Wasif Aleem (wasifale@buffalo.edu).
 */
public class IdList {
    char id;
    IdList idList;

    public IdList() {
        if (Lexer.nextToken == Token.ID) {
            id = Lexer.ident;
            Code.idNumber.add(id);
            Lexer.lex();
        }
        if (Lexer.nextToken == Token.COMMA) {
            Lexer.lex();
            idList = new IdList();
        }
    }
}
