package assignment;

public class Obstacle {
    double x;
    double y;

    Obstacle(double x, double y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "(" + this.x + ", " + this.y + ")";
    }
}
