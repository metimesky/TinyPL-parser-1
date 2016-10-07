/**
 * cmpd - > '{' stmts '}'
 *
 * @author Wasif Aleem (wasifale@buffalo.edu).
 */
public class Cmpd {
	Stmts stmts;

	public Cmpd() {
		if (Lexer.nextToken == Token.LEFT_BRACE) {
			Lexer.lex(); // {
			stmts = new Stmts();
			Lexer.lex(); // }
		}
	}
}
