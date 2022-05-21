package project.Nodes;


import project.Token;

public class VarNode extends ExNode {
    Token var;

    public VarNode(Token var) {
        super();
        this.var = var;
    }
}

