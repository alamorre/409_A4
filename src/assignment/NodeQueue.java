package assignment;

import java.util.ArrayList;

class NodeQueue {

    private final ArrayList<Node> nodeQueue;

    NodeQueue(){
        this.nodeQueue = new ArrayList<>();
    }

    /**
     * This method adds a node
     * */
    synchronized void enq(Node n){
        nodeQueue.add(n);
    }

    /**
     * This method pops a node from the front
     * */
    synchronized Node deq(){
        // If empty, return null
        if(nodeQueue.size() == 0) return null;

        // Else, return a null
        else return nodeQueue.remove(0);

    }

}
