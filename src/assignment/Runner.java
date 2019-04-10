package assignment;

public class Runner implements Runnable  {

    // REFERENCE OBJECTS
    private int limit;
    private Board board;
    private NodeQueue nodeQueue;

    Runner(int limit, Board board, NodeQueue nodeQueue){
        this.limit = limit;
        this.board = board;
        this.nodeQueue = nodeQueue;
    }

    @Override
    public void run() {
        // Start a counter and current node at root
        Node curr;
        do{
            curr = nodeQueue.deq();
        }while(curr == null);

        // Condition of node max
        while(board.getCount() < this.limit){
            // Try to plant an adjacent node
            Node adj = board.plantAdjacent(curr);

            // If null transfer to a new node
            if(adj == null){
                curr = nodeQueue.deq();

            // Else add / print the new node and increment
            } else {
                board.incCount();       // Mark node as added
                nodeQueue.enq(adj);     // Add the node
                System.out.println("Adjacent: " + adj.toString());
            }
        }
    }
}
