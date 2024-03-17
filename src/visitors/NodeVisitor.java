package visitors;

import nodes.BinaryOperationNode;
import nodes.IdentifierNode;
import nodes.LetNode;
import nodes.NumberNode;
import nodes.PrintNode;

public interface NodeVisitor<R> {
    R visit(BinaryOperationNode node);

    R visit(NumberNode node);

    R visit(IdentifierNode node);

    R visit(LetNode node);

    R visit(PrintNode node);
}
