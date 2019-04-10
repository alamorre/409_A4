package assignment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class q1 {

    private static Integer count = 1;   // Task count staring with root

    private static Integer n;           // Radius for nodes
    private static Integer b;           // Node count cap
    private static Integer p;           // Degree for nodes
    private static Double r;            // Radius for nodes

    private static Board board;         // Board obj with obstacles + nodes
    private static Node root;           // Root obj where to start
    private static NodeQueue nodeQueue; // Queue obj of to-do jobs

    public static void main(String[] args){
        // Algorithm specific variables
        p = Integer.parseInt(args[0]);      // Thread count
        n = Integer.parseInt(args[1]);      // Node count
        r = Double.parseDouble(args[2]);    // Limit adjacent distances
        b = Integer.parseInt(args[3]);      // Limit adjacent set size

        // Define the board and node queue
        board = new Board(r, b, count);
        nodeQueue = new NodeQueue();

        // Add a root
        root = board.plantNode();
        nodeQueue.enq(root);

        // Start timer
        long startTime = System.currentTimeMillis();

        // Define all the threads
        ExecutorService executor = Executors.newFixedThreadPool(p);
        Runner[] threads = new Runner[p];
        for(int i = 0; i < p; i++){
            threads[i] = new Runner(n, board, nodeQueue);
        }

        // Run parallel code
        for(int i = 0; i < p; i++){
            executor.execute(threads[i]);
        }

        // Shit down the threads
        executor.shutdown();
        while (!executor.isTerminated());


        // End timer
        long runTime = (System.currentTimeMillis() - startTime);

        // Return time delta
        System.out.println("Runtime: " + runTime);
        System.out.println("Tasks: " + board.getCount());

    }

}
