package com.physics;

public class Particle {
    double m;
    double radius;
    Vector2D position;
    Vector2D velocity;
    /*
        This method simulates particle movement.
        Arguments:
            g -> gravity
            fc -> dry friction coefficient
            fld -> density of the enclosing fluid (either vacuum or air in our case)
            dc -> drag coefficient (assume particle shape is a perfect sphere)
    */

    public Particle(double m, double radius, Vector2D position, Vector2D velocity) {
        this.m = m;
        this.radius = radius;
        this.position = position;
        this.velocity = velocity;
    }

    public void move(double g, double fc, double fld, double dc){
        position.add(velocity);

        //initial kinetic energy
        double Ek = m * velocity.dotProduct(velocity) / 2;
        //final kinetic energy
        double Ekf = Ek;
        //displacement
        double l = velocity.length();

        /*
            Account for energy loss due to dry friction
            Just subtract the work done by the friction force
         */

        Ekf -= m * g * fc * l;

        //Account for fluid (air) resistance

        Ekf -= l * dc * fld * l * l * radius * radius;

        //now reduce velocity according to energy losses
        velocity.multiply(Math.sqrt(Ekf/Ek));
    }
}
