package nodes;

import visitors.NodeVisitor;

public class IdentifierNode extends Node {
  private final String name;

  public IdentifierNode(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public <R> R accept(NodeVisitor<R> visitor) {
    return visitor.visit(this);
  }
}
