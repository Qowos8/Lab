package project.Nodes;

import project.Token;

public class BinOperationNode extends ExNode {
    Token operator;
    ExNode leftNode;
    ExNode rightNode;

    public BinOperationNode(Token operator, ExNode leftNode, ExNode rightNode) {
        super();
        this.operator = operator;
        this.rightNode = rightNode;
        this.leftNode = leftNode;
    }
}
