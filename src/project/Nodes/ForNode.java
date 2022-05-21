package project.Nodes;

import project.Token;
import java.util.ArrayList;

    public class ForNode extends ExNode{
        Token operator;
        ExNode leftNode;
        ExNode rightNode;
        ExNode action;
        public ArrayList<ExNode> operations = new ArrayList<>();

        public ForNode(Token operator, ExNode leftNode, ExNode rightNode, ExNode action) {
            this.operator = operator;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.action = action;
        }
        public void addOperations(ExNode op){

            operations.add(op);
        }
    }