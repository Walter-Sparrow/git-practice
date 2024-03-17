package visitors;

import nodes.BinaryOperationNode;
import nodes.IdentifierNode;
import nodes.LetNode;
import nodes.NumberNode;

public class PrintVisitor implements NodeVisitor<String> {
  @Override
  public String visit(BinaryOperationNode node) {
    String left = node.getLeft().accept(this);
    String right = node.getRight().accept(this);
    return "(" + left + " " + node.getOperator().getValue() + " " + right + ")";
  }

  @Override
  public String visit(NumberNode node) {
    return Integer.toString(node.getValue());
  }

  @Override
  public String visit(IdentifierNode node) {
    return node.getName();
  }

  @Override
  public String visit(LetNode node) {
    return "let " + node.getIdentifierName() + " = " + node.getExpression().accept(this);
  }
}
