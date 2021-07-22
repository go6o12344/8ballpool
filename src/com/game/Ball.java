package com.game;

import com.physics.Particle;
import com.physics.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Ball extends Particle {
    private final Image img;
    private final int numero;
    public Ball(Vector2D position, Vector2D velocity, int numero) throws IOException {
        super(0.16, 12.5, position, velocity);
        this.numero = numero;
        this.img = ImageIO.read(new File("resources/" + this.numero + ".png")).getScaledInstance(25, 25, 0);
    }

    @Override
    public String toString() {
        return "Ball{" +
                "img=" + img +
                ", numero=" + numero +
                ", position=" + position +
                ", velocity=" + velocity +
                '}';
    }

    public Image getImg() {
        return img;
    }

    public int getNum() {
        return numero;
    }

    public void tick(Socket sock){
        Vector2D np = this.position.sum(this.velocity);
        if(sock == null && (np.x < Game.left || np.x > Game.right))
            this.velocity.x *= -1;

        if(sock == null &&(np.y < Game.upper || np.y > Game.lower))
            this.velocity.y *= -1;
        if(sock != null){
            for(Rectangle2D.Double r : sock.hitbox);
                //resolveCollision(r);
        }
        this.tick(9.81, 0.02, 1.168, 0.5, Game.ticks);
    }

    private void resolveCollision(Rectangle2D.Double r){
        Vector2D np = this.position.sum(this.velocity);
        if((np.x < r.x || np.x > r.x + r.width))
            this.velocity.x *= -1;

        if((np.y < r.y || np.y > r.y + r.height))
            this.velocity.y *= -1;
    }

    public void resolveCollision(Ball b){
       super.resolveCollision(b);
    }



    public void render(Graphics g){
        g.drawImage(img, (int) position.x, (int) position.y, null);
    }



}
