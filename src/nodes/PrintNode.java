package nodes;

import visitors.NodeVisitor;

public class PrintNode extends Node {
  private Node expression;

  public PrintNode(Node expression) {
    this.expression = expression;
  }

  public Node getExpression() {
    return expression;
  }

  @Override
  public <R> R accept(NodeVisitor<R> visitor) {
    return visitor.visit(this);
  }
}
