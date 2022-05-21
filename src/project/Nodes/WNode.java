package project.Nodes;

import project.Token;
import java.util.ArrayList;

public class WNode extends ExNode{
    Token oper;
    ExNode leftNode;
    ExNode rightNode;
    public ArrayList<ExNode> operations = new ArrayList<>();

    public WNode(Token oper, ExNode leftNode, ExNode rightNode) {
        this.oper = oper;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }
    public void addOperations(ExNode op){

        operations.add(op);
    }
}