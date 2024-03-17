package nodes;

import visitors.NodeVisitor;

public class LetNode extends Node {
  private final String identifierName;
  private final Node expression;

  public LetNode(String identifierName, Node expression) {
    this.identifierName = identifierName;
    this.expression = expression;
  }

  public String getIdentifierName() {
    return identifierName;
  }

  public Node getExpression() {
    return expression;
  }

  @Override
  public <R> R accept(NodeVisitor<R> visitor) {
    return visitor.visit(this);
  }
}
