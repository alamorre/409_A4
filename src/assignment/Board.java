package assignment;

import java.util.ArrayList;

class Board {

    private ArrayList<Obstacle> obstacles = new ArrayList<>();

    Board(){
        obstacles.add(new Obstacle(0.0, 0.5));
        obstacles.add(new Obstacle(0.05, 0.5));

        obstacles.add(new Obstacle(0.1, 0.5));
        obstacles.add(new Obstacle(0.15, 0.5));

        obstacles.add(new Obstacle(0.2, 0.5));
        obstacles.add(new Obstacle(0.25, 0.5));

        obstacles.add(new Obstacle(0.3, 0.5));
        obstacles.add(new Obstacle(0.35, 0.5));

        obstacles.add(new Obstacle(0.4, 0.5));
        obstacles.add(new Obstacle(0.45, 0.5));

        obstacles.add(new Obstacle(0.5, 0.5));
        obstacles.add(new Obstacle(0.55, 0.5));

        obstacles.add(new Obstacle(0.6, 0.5));
        obstacles.add(new Obstacle(0.65, 0.5));

        obstacles.add(new Obstacle(0.7, 0.5));
        obstacles.add(new Obstacle(0.75, 0.5));

        obstacles.add(new Obstacle(0.8, 0.5));
        obstacles.add(new Obstacle(0.85, 0.5));

        obstacles.add(new Obstacle(0.9, 0.5));
        obstacles.add(new Obstacle(0.95, 0.5));
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
                System.out.println("A");
                isAbove = true;
            // If below set below
            } else if(compare(p, q, c) == "Below"){
                System.out.println("B");
                isBelow = true;
            // If equal set above and below
            } else if(compare(p, q, c) == "Equal"){
                System.out.println("E");
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
            if(o.x - 0.025 <= n.x && n.x <= o.x + 0.025 && o.y - 0.025 <= n.y && n.y <= o.y + 0.025){
                return true;
            }
        }

        // If passes, return false for clear
        return false;
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
            if(!isOnObstacle(node)){
                planted = true;
            } else {
                node = new Node(Math.random(), Math.random());
            }
        }while(!planted);

        // Return new node
        return node;
    }

    Node plantAdjacent(Node n1){
        // Start with a node
        Node n2 = plantNode();

        // Make sure there are no
        boolean isValid = false;
        do{
           if(validateEdge(n1, n2) /* AND IS IN RADIUS */){
               isValid = true;
           } else {
               n2 = plantNode();
           }
        }while(!isValid);

        // Return the result
        return n2;
    }

}
