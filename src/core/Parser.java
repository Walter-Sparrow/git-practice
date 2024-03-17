package core;

import java.util.List;

import nodes.BinaryOperationNode;
import nodes.IdentifierNode;
import nodes.LetNode;
import nodes.Node;
import nodes.NumberNode;

public class Parser {
  private final List<Token> tokens;
  private int currentTokenIndex;

  public Parser(List<Token> tokens) {
    this.tokens = tokens;
    this.currentTokenIndex = 0;
  }

  public Node parse() {
    return parseExpression();
  }

  private Node parseExpression() {
    Node left = parseTerm();

    while (match(TokenType.PLUS, TokenType.MINUS)) {
      Token operator = previousToken();
      Node right = parseTerm();
      left = new BinaryOperationNode(left, operator, right);
    }

    return left;
  }

  private Node parseTerm() {
    Node left = parseFactor();

    while (match(TokenType.MULTIPLY, TokenType.DIVIDE)) {
      Token operator = previousToken();
      Node right = parseFactor();
      left = new BinaryOperationNode(left, operator, right);
    }

    return left;
  }

  private Node parseFactor() {
    Token current = advance();

    if (current.getType() == TokenType.NUMBER) {
      return new NumberNode(Integer.parseInt(current.getValue()));
    } else if (current.getType() == TokenType.IDENTIFIER) {
      return new IdentifierNode(current.getValue());
    } else if (current.getType() == TokenType.LPAREN) {
      Node expression = parseExpression();
      consume(TokenType.RPAREN, "Expected ')' after expression.");
      return expression;
    } else if (current.getType() == TokenType.LET) {
      consume(TokenType.IDENTIFIER, "Expected identifier after 'let'");
      String identifierName = previousToken().getValue();
      consume(TokenType.EQUALS, "Expected '=' after identifier in 'let' statement");
      Node expression = parseExpression();
      return new LetNode(identifierName, expression);
    } else {
      throw new RuntimeException("Unexpected token: " + current.getValue());
    }
  }

  private boolean match(TokenType... types) {
    for (TokenType type : types) {
      if (check(type)) {
        advance();
        return true;
      }
    }
    return false;
  }

  private Token consume(TokenType type, String message) {
    if (check(type)) {
      return advance();
    }

    throw new RuntimeException(message);
  }

  private boolean check(TokenType type) {
    if (isAtEnd())
      return false;
    return peek().getType() == type;
  }

  private Token advance() {
    if (!isAtEnd()) {
      currentTokenIndex++;
    }

    return previousToken();
  }

  private boolean isAtEnd() {
    return peek().getType() == TokenType.EOF;
  }

  private Token peek() {
    return tokens.get(currentTokenIndex);
  }

  private Token previousToken() {
    return tokens.get(currentTokenIndex - 1);
  }
}
