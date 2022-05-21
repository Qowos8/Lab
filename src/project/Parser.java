package project;

import project.Nodes.*;

import java.util.ArrayList;

public class Parser {
    ArrayList<Token> tokens;
    int position = 0;

    public Parser(ArrayList<Token> tokens) {

        this.tokens = tokens;
    }
    public Token receive(String[] need){ // парсинг формулы со скобками
        Token curToken;
        if (position < tokens.size()) {
            curToken = tokens.get(position);// достаем токен
            for (String tokenTypeName : need)
                if (tokenTypeName.equals(curToken.type.nameType)) {
                    position++;
                    return curToken;
                }
        }
        return null;
    }
    public void need(String[] expected){
        Token token= receive(expected);
        if(token == null){
            throw new Error("На позииции "+ position +" ожидается "+expected[0]);
        }
    }
    public ExNode parseVarNum(){
        if (tokens.get(position).type.nameType.equals("NUMBER")){
            position++;
            return new NumberNode(tokens.get(position - 1));
        }
        if (tokens.get(position).type.nameType.equals("VAR")){
            position++;
            return new VarNode(tokens.get(position -1));
        }
        throw new Error("Ожидается переменная или число на позиции: "+ position);
    }
    public ExNode parsePar(){
        if (tokens.get(position).type.nameType.equals("LPAR")){
            position++;
            ExNode exnode = parseF();
            need(new String[]{"RPAR"});
            return exnode;
        }
        else
            return parseVarNum();
    }
    public ExNode parseMultDiv(){
        ExNode leftNode= parsePar();
        Token operator= receive(new String[]{"MULT","DIV"});
        while (operator!= null){
            ExNode rightVal = parsePar();
            leftNode = new BinOperationNode(operator,leftNode,rightVal);
            operator = receive(new String[]{"MULT","DIV"});
        }
        return leftNode;
    }
    public ExNode parseF(){
        ExNode leftNode = parseMultDiv();
        Token operator= receive(new String[]{"PLUS","MINUS"});
        while (operator!= null){
            ExNode rightNode = parseMultDiv();
            leftNode = new BinOperationNode(operator, leftNode, rightNode);
            operator = receive(new String[]{"PLUS","MINUS"});
        }
        return leftNode;
    }
    public ExNode parseString(){
        switch (tokens.get(position).type.nameType) {
            case "VAR":

                ExNode varNode = parseVarNum();
                Token operator = receive(new String[]{"ASSIGN", "ADD", "GET", "CONTAINS"});
                if (operator != null) {
                    ExNode rightNode = parseF();
                    return new BinOperationNode(operator, varNode, rightNode);
                }
                throw new Error("После переменной ожидается бинарный оператор на позиции:" + position);
            case "PRINT":
            case "CLEAR":
                position++;
                return new UnarNode(tokens.get(position - 1), this.parseF());
            case "IF":
                position++;
                return parseIf();
            case "WHILE":
                position++;
                return parseWhile();
            case "FOR":
                position++;
                return parseFor();
        }
        throw new Error("Ошибка на позиции: "+ position +". Ожидалось действие или переменная");
    }
    public ExNode parseIf(){
        ExNode leftNode = parseF();
        Token operator = receive(new String[]{"LESS","MORE","EQUAL"});
        ExNode rightNode = parseF();
        IfNode ifNode = new IfNode(operator, leftNode,rightNode);
        need(new String[]{"LRectPar"});
        while(!tokens.get(position).type.nameType.equals("RRectPAR")) {
            ifNode.addThenOperations(getOperations());
            if (position == tokens.size())
                throw new Error("Ошибка, ожидалось }");
        }
        position++;
        need(new String[]{"ELSE"});
        need(new String[]{"LRectPar"});
        while(!tokens.get(position).type.nameType.equals("RRectPAR")) {
            ifNode.addElseOperations(getOperations());
            if (position ==tokens.size())
                throw new Error("Ошибка, ожидалось }");
        }
        position++;
        return ifNode;
    }
    public ExNode parseFor(){
        ExNode leftNode = parseF();
        Token operator = receive(new String[]{"LESS","MORE","EQUAL"});
        ExNode rightNode = parseF();

        need(new String[]{"END"});

        ExNode varNode = parseVarNum();
        Token assign = receive(new String[]{"ASSIGN"});
        ExNode rightActVal = parseF();
        BinOperationNode action = new BinOperationNode(assign, varNode, rightActVal);
        if (assign == null)
            throw new Error("После переменной ожидается = на позиции:"+ position);
        ForNode forNode= new ForNode(operator,leftNode,rightNode,action);
        need(new String[]{"LRectPar"});
        while(!tokens.get(position).type.nameType.equals("RRectPAR")) {
            forNode.addOperations(getOperations());
            if (position ==tokens.size())
                throw new Error("Ошибка, ожидалось }");
        }
        position++;
        return forNode;
    }
    public ExNode parseWhile(){
        ExNode leftVal= parseF();
        Token operator = receive(new String[]{"LESS","MORE","EQUAL"});
        ExNode rightVal= parseF();
        WNode whileNode=new WNode(operator, leftVal, rightVal);
        need(new String[]{"LRectPar"});
        while(!tokens.get(position).type.nameType.equals("RRectPAR")) {
            whileNode.addOperations(getOperations());
            if (position ==tokens.size())
                throw new Error("Ошибка, ожидалось }");
        }
        position++;
        return whileNode;
    }
    public ExNode getOperations(){
        ExNode codeStringNode = parseString();
        need(new String[]{"END"});
        return codeStringNode;
    }
    public RootNode parseTokens(){
        RootNode root = new RootNode();
        while (position < tokens.size()){
            ExNode codeStringNode = parseString();
            need(new String[]{"END"});
            root.addNode(codeStringNode);
        }
        return root;
    }
}
