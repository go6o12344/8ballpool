package com.game;

import com.physics.Particle;
import com.physics.Segment;
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

    public void tick(ArrayList<Segment> sides){
        resolveBoardCollision(sides);
        tick(9.81, 0.02, 1.168, 0.5, Game.ticks);
    }

    public void resolveBoardCollision(ArrayList<Segment> sides){
        for(Segment s : sides){
            if(resolveCollision(s))
                return;
        }

    };

    public boolean resolveCollision(Segment s){
        if(hitbox.checkForIntersection(s)){
            s.toVector2D().reflect(velocity);
            System.out.println(s + " " + hitbox);
            return true;
        }
        return false;
    }

    public void resolveCollision(Ball b){
       super.resolveCollision(b);
    }



    public void render(Graphics g){
        g.drawImage(img, (int) position.x, (int) position.y, null);
    }



}
