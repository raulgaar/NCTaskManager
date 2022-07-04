package mx.tc.j2se.evaluation;

public class Evaluation1 {
    public static void main(String[] args) {
        int index = 0;
        int radius = 0;
        Circle circle = new Circle();
        Circle circle1 = new Circle(3);
        Circle circle2 = new Circle(7);
        Circle circleBad = new Circle(-1);
        Circle[] circles = {circle1, circle, circle2};
        index = biggestCircle(circles);
        radius = circles[index].getRadius();
        System.out.println("The biggest circle has: " + radius + " as it's radius");
    }


    public static int biggestCircle(Circle[] circles) {
        int index = 0;
        double max = 0.0;
        for (int i = 0; i < circles.length; i++) {
            if (circles[i] == null) {
                continue;
            }
            if (circles[i].getArea() > max){
                index = i;
                max = circles[i].getArea();
            }
        }
        return index;
    }
}
