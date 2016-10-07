import java.util.Collections;
import java.util.List;

/**
 * loop - > for '('[assign] ';' [rexp] ';'[assign] ')' stmt
 *
 * @author Wasif Aleem (wasifale@buffalo.edu).
 */
public class Loop {
	Assign init;
	Rexpr test;
	Assign incr;
	Stmt stmt;

	public Loop() {
		if (Lexer.nextToken == Token.KEY_FOR) {
			Lexer.lex(); // for
			if (Lexer.nextToken == Token.LEFT_PAREN) {
				Lexer.lex(); // (

				// INIT
				if (Lexer.nextToken == Token.ID) {
					init = new Assign();
				}
				Lexer.lex(); // ;
				int gotoIndex = Code.byteOffset;

				// TEST
				if (Lexer.nextToken != Token.SEMICOLON) {
					test = new Rexpr();
				}
				int testIndex = Integer.MIN_VALUE;
				if (test != null && test.op != null) {
					testIndex = Code.gen(Code.ifPlaceHolder(test.op));
				}
				Lexer.lex(); // ;

				// generate, store & pop increment
				int incrIndex = Code.byteOffset;
				if (Lexer.nextToken == Token.ID) {
					incr = new Assign();
				}
				List<Code.Instruction> incrs = Collections.emptyList();
				if (incr != null && incr.e != null) {
					incrs = Code.popInstructionsUntil(incrIndex);
				}

				if (Lexer.nextToken == Token.RIGHT_PAREN) {
					Lexer.lex(); // )

					// STMT
					stmt = new Stmt();

					// generate stored increment
					if (!incrs.isEmpty()) {
						for (Code.Instruction i : incrs) {
							i.byteOffset = Code.byteOffset;
							Code.gen(i);
							Code.byteOffset++;
						}
					}

					Code.gen(Code.gotoOffset(gotoIndex));
					if (testIndex != Integer.MIN_VALUE) {
						Code.appendCurrentAddress(testIndex);
					}
				}
			}
		}
	}
}
