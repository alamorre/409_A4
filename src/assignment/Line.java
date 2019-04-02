package assignment;

class Line {
    private Node p;
    private Node q;

    private double m;
    private double b;

    private int acc = 1000000;

    Line(Node p, Node q){
        this.p = p;
        this.q = q;

        // Assign slope m
        // If the run is zero, max the slope
        if(p.x - q.x == 0.0){
            this.m = (double)Integer.MAX_VALUE;
        } else {
            this.m = (p.y - q.y)/(p.x - q.x);
        }

        // Assign y intersect b
        this.b = p.y - this.m * p.x;

    }

    /**
     * 1. If the line is vertical, do horizontal comparison
     * 2. Else do vertical comparison
     * */
    String isNodeAbove(Node c){
        // 1. If the line it vertical, check if on LHS
        if(this.m == (double)Integer.MAX_VALUE){
            if(Math.round(c.x * acc) < Math.round(p.x * acc)){
                return "Above";
            } else if ( Math.round(c.x * acc) >  Math.round(p.x * acc)) {
                return "Below";
            } else {
                return "Equal";
            }

        // 2. Else, check if c.y is above the line at c.x
        } else {
            if(Math.round(c.y * acc) > Math.round((this.m * c.x + this.b) * acc)){
                return "Above";
            } else if (Math.round(c.y * acc) < Math.round((this.m * c.x + this.b) * acc)) {
                return "Below";
            } else {
                return "Equal";
            }
        }
    }
}
