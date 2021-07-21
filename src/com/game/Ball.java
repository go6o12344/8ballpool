package com.game;

import com.physics.Particle;
import com.physics.Vector2D;

public class Ball extends Particle {
    public Ball(double m, double radius, Vector2D position, Vector2D velocity) {
        super(m, radius, position, velocity);
    }
}
