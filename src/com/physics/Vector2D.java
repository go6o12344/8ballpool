package com.physics;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Vector2D {
    public double x, y;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D v){
        this.x = v.x;
        this.y = v.y;
    }

    public Vector2D(Point p){
        this.x = p.x;
        this.y = p.y;
    }

    public Vector2D(Segment s){
        this.x = s.end.x - s.start.x;
        this.y = s.end.y - s.start.y;
    }

    public boolean equals(Vector2D v){
        return this.x == v.x && this.y == v.y;
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Vector2D add(Vector2D v){
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    public Vector2D sum(Vector2D v){
        return new Vector2D(this).add(v);
    }

    public Vector2D subtract(Vector2D v){
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    public Vector2D difference(Vector2D v){
        return new Vector2D(this).subtract(v);
    }

    public Vector2D multiply(double a){
        this.x *= a;
        this.y *= a;
        return this;
    }

    public Vector2D times(double a){
        return new Vector2D(this).multiply(a);
    }

    public double length(){
        return Math.sqrt(this.dotProduct(this));
    }

    public Vector2D normalize(){
        if(this.isNull())
            return this;
        double l = this.length();
        if(l != 0.0) {
            this.x /= l;
            this.y /= l;
        }
        return this;
    }

    public Vector2D unit(){
        return new Vector2D(this).normalize();
    }

    public double dotProduct(Vector2D v){
        return this.x * v.x + this.y * v.y;
    }

    public boolean isNull(){
        return this.x == 0 && this.y == 0;
    }

    public Vector2D opposite() {
        return new Vector2D(this).multiply(-1);
    }

    public double dot(Vector2D v) {
        return this.dotProduct(v);
    }

    public boolean in(Rectangle2D.Double r){
        return this.x > r.x && this.y > r.y && this.x < r.x + r.width && this.y < r.y + r.height;
    }

    public Vector2D rotate(double angle){
        double x1 = this.x, y1 = this.y;
        double s = Math.sin(angle), c = Math.cos(angle);
        this.x = x1 * c - y1 * s;
        this.y = x1 * s + y1 * c;
        return this;
    }

    public Vector2D rotated(double angle){
        return new Vector2D(this).rotate(angle);
    }

    public void reflect(Vector2D v){
        v.rotate(-2 * Math.acos(this.unit().dot(v.unit())));
    }


}
