package nodes;

import core.Token;
import visitors.NodeVisitor;

public class BinaryOperationNode extends Node {
  private final Node left;
  private final Token operator;
  private final Node right;

  public BinaryOperationNode(Node left, Token operator, Node right) {
    this.left = left;
    this.operator = operator;
    this.right = right;
  }

  public Node getLeft() {
    return left;
  }

  public Token getOperator() {
    return operator;
  }

  public Node getRight() {
    return right;
  }

  @Override
  public <R> R accept(NodeVisitor<R> visitor) {
    return visitor.visit(this);
  }
}
