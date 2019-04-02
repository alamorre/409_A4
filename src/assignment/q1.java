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
        p = 5;
        n = 20;

        // Node specific variables
        b = 3;

        // Board specific variables
        r = 0.3;

        board = new Board(r);

        // Add a root
        root = board.plantNode();
        System.out.println("seed: " + root.toString());

        // Expand on the root
        expand();
    }

    private static void expand(){
        int count = 1;
        while(count < n){
            Node adj = board.plantAdjacent(root);
            System.out.println("adj: " + adj.toString());
            count++;
        }
    }

}
