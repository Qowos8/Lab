package project.Nodes;//узел для переменной


import project.Token;

// значение токена
public class VarNode extends ExNode {
    Token var;

    public VarNode(Token var) {
        super();
        this.var = var;
    }
}

