package com.physics;

public class Segment {
    public Vector2D start;
    public Vector2D end;
    public Segment(Vector2D start, Vector2D end){
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    public double perpendicularLength(Vector2D v){
        double a, b;
        //find line eq.
        if(start.x != end.x){
            a = (end.y - start.y) / (end.x - start.x);
            b = start.y - a * start.x;
        }
        else{
            return Math.abs(v.x - start.x);
        }
        return Math.abs(a * v.x - v.y + b) / Math.sqrt(a * a + 1);
    }

    public Vector2D toVector2D(){
        return end.difference(start);
    }
}
