import java.util.List;

import core.Lexer;
import core.Parser;
import core.Token;
import visitors.PrintVisitor;

// Simple interpreter for the abstract language
public class App {
    public static void main(String[] args) throws Exception {
        var lexer = new Lexer("let x = 5;\nprint x;\n");
        List<Token> tokens = lexer.tokenize();
        System.out.println("Tokens: ");
        System.out.println(tokens.stream().map(Token::getValue).toList());

        var parser = new Parser(tokens);
        var ast = parser.parse();

        var visitor = new PrintVisitor();
        String result = ast.accept(visitor);
        System.out.println("AST: ");
        System.out.println(result);
    }
}
