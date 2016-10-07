public class Parser {
    public static void main(String[] args) {
        System.out.println("Enter TinyPL program!");
        Lexer.lex();
        new Program();
        Code.output();
        System.exit(0);
    }
}










