package nodes;

import visitors.NodeVisitor;

public abstract class Node {
  public abstract <R> R accept(NodeVisitor<R> visitor);
}
