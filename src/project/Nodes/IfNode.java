package project.Nodes;


import project.Token;

import java.util.ArrayList;

public class IfNode extends ExNode {
    Token operator;
    ExNode leftNode;
    ExNode rightNode;
    public ArrayList<ExNode> thenOperations = new ArrayList<>();
    public ArrayList<ExNode> elseOperations = new ArrayList<>();
    public IfNode(Token operator, ExNode leftNode, ExNode rightNode) {
        this.operator = operator;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }
    public void addThenOperations(ExNode op){

        thenOperations.add(op);
    }
    public void addElseOperations(ExNode op){

        elseOperations.add(op);
    }
}

