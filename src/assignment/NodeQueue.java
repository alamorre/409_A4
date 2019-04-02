package assignment;

import java.util.ArrayList;

public class NodeQueue {

    private final ArrayList<Node> nodeQueue;

    public NodeQueue(){
        this.nodeQueue = new ArrayList<>();
    }

    /**
     * This method adds a node
     * */
    public void enq(Node n){
        synchronized(nodeQueue){
            nodeQueue.add(n);
        }
    }

    /**
     * This method pops a node from the front
     * */
    public Node deq(){
        synchronized(nodeQueue){
            Node n = nodeQueue.remove(0);
            return n;
        }
    }

}
