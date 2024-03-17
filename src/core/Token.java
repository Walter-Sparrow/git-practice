package core;

enum TokenType {
  NUMBER,
  IDENTIFIER,
  LET,
  PRINT,
  PLUS,
  MINUS,
  MULTIPLY,
  DIVIDE,
  SEMICOLON,
  LPAREN,
  RPAREN,
  EQUALS,
  EOF
}

public class Token {
  private final TokenType type;
  private final String value;

  public Token(TokenType type, String value) {
    this.type = type;
    this.value = value;
  }

  public TokenType getType() {
    return type;
  }

  public String getValue() {
    return value;
  }
}
