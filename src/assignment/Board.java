package assignment;

import java.util.ArrayList;

class Board {

    private int obstableCount = 20;
    private ArrayList<Obstacle> obstacles = new ArrayList<>();

    private double r; // Radial bound
    private double b; // Adjacent limit

    Board(double r, int b){
        this.r = r;
        this.b = b;

        for(int i = 0; i < obstableCount; i++){
            Obstacle o = new Obstacle(Math.random(), Math.random());
            obstacles.add(o);
        }

    }

    /**
     * Disqualify out of range nodes and return line compare results
     * */
    private String compare(Node p, Node q, Node c){
        // Disqualify corners that are out of range
        if(c.x < Math.min(p.x, q.x)){
            return "Out of Range";
        }if(c.x > Math.max(p.x, q.x)){
            return "Out of Range";
        }if(c.y < Math.min(p.y, q.y)){
            return "Out of Range";
        }if(c.y > Math.max(p.y, q.y)){
            return "Out of Range";
        }

        // See if it's above, below or equal to line eq.
        Line line = new Line(p, q);
        return line.isNodeAbove(c);
    }

    /**
     * This method iterate over the corner's obstacles
     * and tally if the corners are above and below the line
     * */
    private boolean doesObstacleIntersect(Node p, Node q, Obstacle o){
        boolean isAbove = false;    // Mark if the obstacle is above line
        boolean isBelow = false;    // Mark if the obstacle is below line
        Node[] corners = {          // Array of obstacle corners (convex hull)
            new Node(o.x - 0.025, o.y - 0.025),
            new Node(o.x - 0.025, o.y + 0.025),
            new Node(o.x + 0.025, o.y - 0.025),
            new Node(o.x + 0.025, o.y + 0.025),
        };

        // Check every corner relative to line
        for(Node c : corners){
            // If above set above
            if(compare(p, q, c) == "Above"){
                isAbove = true;
            // If below set below
            } else if(compare(p, q, c) == "Below"){
                isBelow = true;
            // If equal set above and below
            } else if(compare(p, q, c) == "Equal"){
                isAbove = true;
                isBelow = true;
            }
        }
        return isAbove && isBelow;
    }

    /**
     * This method will validate every obstacle
     * and return if there is an obstruction
     * */
    boolean validateEdge(Node p, Node q){
        for(Obstacle o : obstacles){
            if(doesObstacleIntersect(p, q, o)){
                return false;
            }
        }
        return true;
    }

    /**
     * Take a Node and check if it's on any obstacle
     * */
    boolean isOnObstacle(Node n){
        // For each obstacle, if its within +-0.5 of o.x | o.y return true
        for(Obstacle o : obstacles){
            if(o.x - 0.025 <= n.x && n.x <= o.x + 0.025 && o.y - 0.025 <= n.y && n.y <= o.y + 0.025)
                return true;

        }

        // If passes, return false for clear
        return false;
    }

    /**
     * This method ensure two nodes are in the radius
     * */
    private boolean withinRadius(Node n1, Node n2){
        return Math.sqrt((n2.x - n1.x)*(n2.x - n1.x) + (n2.y - n1.y)*(n2.y - n1.y)) <= r;
    }

    /**
     * This method will keep planting seed until it covers no obstacle
     * */
    Node plantNode(){
        // Set Node and if plant was successful
        Node node = new Node(Math.random(), Math.random());
        boolean planted = false;

        // Keep trying until it covers no obstacles
        do{
            if(!isOnObstacle(node))
                planted = true;

            else
                node = new Node(Math.random(), Math.random());

        }while(!planted);

        // Return new node
        return node;
    }

    /**
     * This node will try to plant an adjacent node
     * */
    Node plantAdjacent(Node n1){
        if(n1.getB() == b){
            return null;
        }

        // Start with a node
        Node n2 = plantNode();

        // Make sure there are no
        boolean isValid = false;
        do{
           if(validateEdge(n1, n2) && withinRadius(n1, n2))
               isValid = true;

           else
               n2 = plantNode();

        }while(!isValid);

        // Return the result
        n1.incB();
        n2.incB();
        return n2;
    }

}
