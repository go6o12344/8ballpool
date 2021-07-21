package com.game;

import com.physics.Particle;
import com.physics.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

public class Ball extends Particle {
    private final Image img;
    private final int numero;
    public Ball(Vector2D position, Vector2D velocity, int numero) throws IOException {
        super(0.16, 15, position, velocity);
        this.numero = numero;
        this.img = ImageIO.read(new File("resources/" + this.numero + ".png")).getScaledInstance(30, 30, 0);
    }

    public Image getImg() {
        return img;
    }

    public int getNum() {
        return numero;
    }

    public void tick(){
        this.tick(9.81, 0.02, 1.168, 0.5);
    }

    public void resolveCollision(Ball b){
        if(!checkForCollision(b)) {
            return;
        }
        Vector2D v1 = this.getVelocity();
        Vector2D v2 = b.getVelocity();

        Vector2D n = this.position.add(b.position.opposite()).normalize();
        Vector2D nt = new Vector2D(-n.y, n.x);
        this.velocity = nt.times(v1.dot(nt)).add(n.times(v2.dot(n)));
        b.velocity = nt.times(v2.dot(nt)).add(n.times(v1.dot(n)));

        /*
        Vector2D X = this.getPosition().sum(b.getPosition().opposite());
        Vector2D V = v1.sum(v2.opposite());
        this.getVelocity().add(X.times(X.dot(V) / X.dot(X)).opposite());
        b.velocity.add(X.times(X.dot(V) / X.dot(X)));
         */
    }

    public boolean checkForCollision(Ball b){
        Vector2D v = new Vector2D(this.position.difference(b.position));
        return v.dot(v) <= 900;
    }

    public void render(Graphics g){
        g.drawImage(img, (int) position.x, (int) position.y, null);
    }

}
