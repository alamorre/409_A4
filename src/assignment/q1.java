package assignment;

public class q1 {
    public static void main(String[] args){
        Board board = new Board();

        int p = 5;      // threads
        int n = 20;     // node cap
        int b = 3;      // edge max
        double r = 0.1; // radius
        
        Node seed = board.plantNode();
        System.out.println("seed: " + seed.toString());

        Node adj = board.plantAdjacent(seed);
        System.out.println("adj: " + adj.toString());
    }

}
