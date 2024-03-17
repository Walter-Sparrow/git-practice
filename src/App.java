import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        Lexer lexer = new Lexer("let x = 5;\nprint(x);\n");
        List<Token> tokens = lexer.tokenize();
        System.out.println(tokens.stream().map(Token::getValue).toList());
    }
}
