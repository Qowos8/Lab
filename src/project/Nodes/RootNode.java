package project.Nodes;

import java.util.ArrayList;

public class RootNode extends ExNode {// массив каждый элемент которого строчка кода
    ArrayList<ExNode> codeStr = new ArrayList<>();
    public void addNode(ExNode exNode){

        codeStr.add(exNode);
    }
}
