package assignment;

public class Node {
    double x;
    double y;

    public Node(double x, double y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "(" + this.x + ", " + this.y + ")";
    }
}
