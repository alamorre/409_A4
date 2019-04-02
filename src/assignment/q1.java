package assignment;

public class q1 {

    private static Integer n;  // Radius for nodes
    private static Integer b;  // Node count cap
    private static Integer p;  // Degree for nodes
    private static Double r;   // Radius for nodes

    private static Board board;// Board object with obstacles + nodes
    private static Node root;  // Root node where to start

    public static void main(String[] args){
        // Algorithm specific variables
        p = 5;  // Thread count
        n = 20; // Node count
        r = 0.3;// Limit adjacent distances
        b = 3;  // Limit adjacent set size

        // Define the board
        board = new Board(r, b);

        // Add a root
        root = board.plantNode();
        System.out.println("seed: " + root.toString());

        // Expand on the root
        expand();
    }

    private static void expand(){
        // Start a counter to n
        int count = 1;
        while(count < n){
            // Try to plant an adjacent node
            Node adj = board.plantAdjacent(root);

            // If null transfer thread to a new node
            if(adj == null){
                System.out.println("Limit");
                break;
            }

            // Print the new node and increment
            System.out.println("adj: " + adj.toString());
            count++;
        }
    }

}
