package com.physics;

public class Segment {
    public Vector2D start;
    public Vector2D end;
    public Segment(Vector2D start, Vector2D end){
        this.start = start;
        this.end = end;
    }

    public double perpendicularLength(Vector2D v){
        double a, b;
        //find line eq.
        if(start.x != end.x){
            a = (end.y - start.y) / (end.x - start.x);
            b = start.y - a * start.x;
        }
        else{
            return v.x - start.x;
        }
        return Math.abs(a * v.x - v.y + b) / Math.sqrt(a * a + 1);
    }
}
