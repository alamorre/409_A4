package assignment;

public class q1 {
    public static void main(String[] args){
        Board board = new Board();

        Node n1 = new Node(0.0, 0.0);
        Node n2 = new Node(0.15, 0.15);
        Node n3 = new Node(0.15, 0.0);

        // Test line is above
        System.out.println("E1 good: " + board.validateEdge(n1, n2));
        System.out.println("E2 good: " + board.validateEdge(n1, n3));

    }
}
