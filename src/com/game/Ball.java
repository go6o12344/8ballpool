package com.game;

import com.physics.Particle;
import com.physics.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Ball extends Particle {
    private final Image img;
    private final int numero;
    private ArrayList<Boolean> collided = new ArrayList<>(16);
    public Ball(Vector2D position, Vector2D velocity, int numero) throws IOException {
        super(0.16, 15, position, velocity);
        this.numero = numero;
        this.img = ImageIO.read(new File("resources/" + this.numero + ".png")).getScaledInstance(30, 30, 0);
        for(int i = 0; i < 16; i++){
            collided.add(false);
        }
        collided.set(this.numero, true);
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

    public void tick(){
        Vector2D np = this.position.sum(this.velocity);
        if(np.x < Game.left || np.x > Game.right)
            this.velocity.x *= -1;

        if(np.y < Game.upper || np.y > Game.lower)
            this.velocity.y *= -1;

        this.tick(9.81, 0.02, 1.168, 0.5);
    }

    public void resolveCollision(Ball b){
        if(this.checkForCollision(b) && !collided.get(b.numero))
            collided.set(b.numero, true);
        else if(collided.get(b.numero)) {
            if(new Vector2D(this.position).subtract(b.position).length() > 30.1)
                collided.set(b.numero, false);
            return;
        }
        super.resolveCollision(b);

    }



    public void render(Graphics g){
        g.drawImage(img, (int) position.x, (int) position.y, null);
    }

}
