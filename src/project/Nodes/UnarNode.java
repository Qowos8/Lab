package project.Nodes;

import project.Token;

import java.util.ArrayList;

public class UnarNode extends ExNode {
    Token operator;
    ExNode operand;

    public UnarNode(Token operator, ExNode operand) {
        this.operator = operator;
        this.operand = operand;
    }
}
