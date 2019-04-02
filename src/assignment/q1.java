package assignment;

public class q1 {

    private static Integer n;  // Radius for nodes
    private static Integer b;  // Node count cap
    private static Integer p;  // Degree for nodes
    private static Double r;   // Radius for nodes

    private static Board board;// Board object with obstacles + nodes
    private static Node root;  // Root node where to start
    private static NodeQueue nodeQueue;  // Root node where to start

    public static void main(String[] args){
        // Algorithm specific variables
        p = 5;  // Thread count
        n = 9; // Node count
        r = 0.3;// Limit adjacent distances
        b = 3;  // Limit adjacent set size

        // Define the board and node queue
        board = new Board(r, b);
        nodeQueue = new NodeQueue();

        // Add a root
        root = board.plantNode();
        System.out.println("seed: " + root.toString());

        // Expand on the root
        expand(root);
    }

    private static void expand(Node root){
        // Start a counter and current node at root
        int count = 1;
        Node curr = root;

        while(count < n){
            // Try to plant an adjacent node
            Node adj = board.plantAdjacent(curr);

            // If null transfer to a new node
            if(adj == null){
                curr = nodeQueue.deq();

            // Else add / print the new node and increment
            } else {
                nodeQueue.enq(adj);
                System.out.println("adj: " + adj.toString());
                count++;
            }
        }
    }

}
