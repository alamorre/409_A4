package assignment;

public class Node {
    double x;
    double y;
    int b;

    Node(double x, double y){
        this.x = x;
        this.y = y;
        this.b = 0;
    }

    public void incB(){
        this.b++;
    }

    public int getB(){
        return this.b;
    }

    public String toString(){
        return "(" + this.x + ", " + this.y + ")";
    }
}
