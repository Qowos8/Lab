package project.Nodes;

import java.util.ArrayList;

public class RootNode extends ExNode {
    ArrayList<ExNode> codeStr = new ArrayList<>();
    public void addNode(ExNode exNode){

        codeStr.add(exNode);
    }
}
