package com.physics;

public class Circle {
    public double radius;
    public Vector2D center;

    public Circle(double radius, Vector2D center) {
        this.radius = radius;
        this.center = center;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                ", center=" + center +
                '}';
    }

    public static boolean between(double a, double b1, double b2){
        if( a == b1 && b1 == b2) return false;
        return (b1 <= a && b2 >= a) || (b1 >= a && b2 <= a);
    }

    public boolean checkForIntersection(Segment s){
        double ls = new Vector2D(s.start).subtract(center).length(), le = new Vector2D(s.end).subtract(center).length();
        if(ls < radius && le < radius) return false;
        if(ls <= radius) return true;
        if(le <= radius) return true;
        if(s.perpendicularLength(center) <= radius && (between(center.x, s.start.x, s.end.x) || between(center.y, s.start.y, s.end.y))) return true;
        return false;
    }

    public boolean in(Circle c){
        return new Vector2D(center).subtract(c.center).length() + radius <= c.radius;
    }


}
