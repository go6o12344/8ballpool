package com.physics;

import com.game.Ball;

public class Particle{
    private final double m;
    private final double radius;
    public Vector2D position;
    public Vector2D velocity;


    public Particle(double m, double radius, Vector2D position, Vector2D velocity) {
        this.m = m;
        this.radius = radius;
        this.position = position;
        this.velocity = velocity;
    }

    @Override
    public String toString() {
        return "Particle{" +
                "m=" + m +
                ", radius=" + radius +
                ", position=" + position +
                ", velocity=" + velocity +
                '}';
    }

    public void tick(double g, double fc, double fld, double dc, double ticks){
        /*
        This method simulates particle movement.
        Arguments:
            g -> gravity
            fc -> dry friction coefficient
            fld -> density of the enclosing fluid (either vacuum or air in our case)
            dc -> drag coefficient (assume particle shape is a perfect sphere)
        */
        ticks = 900;
        if(velocity.isNull())
            return;

        position.add(velocity);

        //initial kinetic energy
        double Ek = m * velocity.dotProduct(velocity) / 2 / 10000 * ticks * ticks;
        //final kinetic energy
        double Ekf = Ek;
        //displacement
        double l = velocity.length() / 100 * ticks;

        /*
            Account for energy loss due to dry friction
            Just subtract the work done by the friction force
         */

        Ekf -= m * g * fc * l / ticks;

        //Account for fluid (air) resistance

        Ekf -= l / ticks * dc * fld * l * l * radius * radius * Math.PI / 10000;

        //now reduce velocity according to energy losses
        Ekf = Ekf < 0 ? 0 : Ekf;
        velocity.multiply(Math.sqrt(Ekf/Ek));

    }

    public void resolveCollision(Particle b){
        if(!checkForCollision(b)) {
            return;
        }
        Vector2D v1 = this.getVelocity();
        Vector2D v2 = b.getVelocity();

        Vector2D n = this.position.difference(b.position);
        double d = n.length();
        Vector2D mtd = n.times(((getRadius() + b.getRadius())-d)/d);
        n.normalize();
        position.add(mtd.times(0.5));
        b.position.subtract(mtd.times(0.5));
        if(n.dot(v1.difference(v2)) > 0)
            return;
        Vector2D nt = new Vector2D(-n.y, n.x);
        this.velocity = nt.times(v1.dot(nt)).add(n.times(v2.dot(n)));
        b.velocity = nt.times(v2.dot(nt)).add(n.times(v1.dot(n)));

    /*
        Vector2D X = this.getPosition().sum(b.getPosition().opposite());
        Vector2D V = v1.sum(v2.opposite());
        this.velocity.add(X.times(X.dot(V) / X.dot(X)).opposite());
        b.velocity.add(X.times(X.dot(V) / X.dot(X)));
     */
    }

    public boolean checkForCollision(Particle b){
        Vector2D v = new Vector2D(this.position.difference(b.position));
        return v.dot(v) <= (radius + b.radius) * (radius + b.radius);
    }

    public double getM() {
        return m;
    }

    public double getRadius() {
        return radius;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }
}
