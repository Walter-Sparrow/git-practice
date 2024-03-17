import java.util.ArrayList;
import java.util.List;

public class Lexer {
  private final String input;
  private int position;

  public Lexer(String input) {
    this.input = input;
    this.position = 0;
  }

  public List<Token> tokenize() {
    List<Token> tokens = new ArrayList<>();

    while (position < input.length()) {
      char currentChar = input.charAt(position);

      if (Character.isWhitespace(currentChar)) {
        position++;
      } else {
        tokens.add(tokenizeCurrentChar(currentChar));
      }
    }

    return tokens;
  }

  private Token tokenizeCurrentChar(char currentChar) {
    if (Character.isDigit(currentChar)) {
      return new Token(TokenType.NUMBER, consumeNumber());
    }

    if (Character.isLetter(currentChar)) {
      return consumeIdentifierOrKeyword();
    }

    var token = tokenizeSymbol(currentChar);
    position++;
    return token;
  }

  private Token consumeIdentifierOrKeyword() {
    StringBuilder identifier = new StringBuilder();
    while (position < input.length() && isValidIdentifierCharacter(input.charAt(position))) {
      identifier.append(input.charAt(position));
      position++;
    }
    String identifierString = identifier.toString();
    return mapToTokenType(identifierString);
  }

  private Token mapToTokenType(String identifierString) {
    return switch (identifierString) {
      case "let" -> new Token(TokenType.LET, "let");
      case "print" -> new Token(TokenType.PRINT, "print");
      default -> new Token(TokenType.IDENTIFIER, identifierString);
    };
  }

  private boolean isValidIdentifierCharacter(char c) {
    return Character.isLetter(c) || Character.isDigit(c);
  }

  private Token tokenizeSymbol(char currentChar) {
    return switch (currentChar) {
      case '+' -> new Token(TokenType.PLUS, "+");
      case '-' -> new Token(TokenType.MINUS, "-");
      case '*' -> new Token(TokenType.MULTIPLY, "*");
      case '/' -> new Token(TokenType.DIVIDE, "/");
      case ';' -> new Token(TokenType.SEMICOLON, ";");
      case '(' -> new Token(TokenType.LPAREN, "(");
      case ')' -> new Token(TokenType.RPAREN, ")");
      case '=' -> new Token(TokenType.EQUALS, "=");
      default -> throw new IllegalArgumentException("Invalid character: " + currentChar);
    };
  }

  private String consumeNumber() {
    StringBuilder number = new StringBuilder();
    while (position < input.length() && Character.isDigit(input.charAt(position))) {
      number.append(input.charAt(position));
      position++;
    }
    return number.toString();
  }
}
