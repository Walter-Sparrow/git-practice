package nodes;

import visitors.NodeVisitor;

public class NumberNode extends Node {
  private final int value;

  public NumberNode(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  @Override
  public <R> R accept(NodeVisitor<R> visitor) {
    return visitor.visit(this);
  }
}
