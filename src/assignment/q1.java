package assignment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class q1 {

    private static Integer count = 1; // Start count with root
    private static Integer n;  // Radius for nodes
    private static Integer b;  // Node count cap
    private static Integer p;  // Degree for nodes
    private static Double r;   // Radius for nodes

    private static Board board;// Board object with obstacles + nodes
    private static Node root;  // Root node where to start
    private static NodeQueue nodeQueue;  // Root node where to start

    public static void main(String[] args){
        // Algorithm specific variables
        p = 1;  // Thread count
        n = 9; // Node count
        r = 0.3;// Limit adjacent distances
        b = 3;  // Limit adjacent set size

        // Define the board and node queue
        board = new Board(r, b);
        nodeQueue = new NodeQueue();

        // Add a root
        root = board.plantNode();
        nodeQueue.enq(root);

        // Define all the threads
        ExecutorService executor = Executors.newFixedThreadPool(p);
        Runner[] threads = new Runner[p];
        for(int i = 0; i < p; i++){
            threads[i] = new Runner(i, count, n, board, nodeQueue);
        }

        // Start timer
        long startTime = System.currentTimeMillis();

        // Run parallel code
        for(int i = 0; i < p; i++){
            executor.execute(threads[i]);
        }

        // Shit down the threads
        executor.shutdown();
        while (!executor.isTerminated());

        // End timer
        long runTime = System.currentTimeMillis() - startTime;

        // Return time delta
        System.out.println("Runtime: " + runTime);

    }

}
