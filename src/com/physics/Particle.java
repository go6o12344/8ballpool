package com.physics;

public class Particle {
    double mass;
    double radius;
    Vector2D position;
    Vector2D velocity;

    public void move(){
        position.add(velocity);

    }
}
