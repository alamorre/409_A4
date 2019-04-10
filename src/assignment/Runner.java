package assignment;

public class Runner implements Runnable  {

    // REFERENCE OBJECTS
    private int limit;
    private Integer count;
    private Integer tasks;
    private Board board;
    private NodeQueue nodeQueue;

    Runner(Integer count, Integer tasks, int limit, Board board, NodeQueue nodeQueue){
        this.count = count;
        this.tasks = tasks;
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

        // CONSTANTS
        while(count < this.limit){
            // Try to plant an adjacent node
            Node adj = board.plantAdjacent(curr);

            // If null transfer to a new node
            if(adj == null){
                curr = nodeQueue.deq();

                // Else add / print the new node and increment
            } else {
                synchronized (count){
                    nodeQueue.enq(adj);
                    System.out.println("Adjacent " +  count + ": " + adj.toString());
                    count++;
                }
                synchronized (tasks){
                    tasks = tasks + 1;
                }
            }
        }
    }
}
