package com.physics;

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

    public boolean equals(Vector2D v){
        return this.x == v.x && this.y == v.y;
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


}
