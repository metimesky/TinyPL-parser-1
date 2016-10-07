/**
 * stmts - > stmt [ stmts ]
 *
 * @author Wasif Aleem (wasifale@buffalo.edu).
 */
public class Stmts {
	Stmt stmt;
	Stmts stmts;

	public Stmts() {
		stmt = new Stmt();
		if (Lexer.nextToken == Token.KEY_END) {
			return;
		} 
		if(Lexer.nextToken != Token.RIGHT_BRACE){
			stmts = new Stmts();
		}
	}
}
