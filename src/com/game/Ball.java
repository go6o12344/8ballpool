package com.game;

import com.physics.Particle;
import com.physics.Segment;
import com.physics.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Ball extends Particle {
    private final Image img;
    private final int numero;
    public Ball(Vector2D position, Vector2D velocity, int numero) throws IOException {
        super(0.16, Game.ballRadius, position, velocity);
        this.numero = numero;
        this.img = ImageIO.read(new File("resources/" + this.numero + ".png")).getScaledInstance((int) (2 * Game.ballRadius), (int) (2 * Game.ballRadius), 0);
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

    public double calculateDistAdjusted(Segment s){
        return s.perpendicularLength(position) + s.toVector2D().rotate(- Math.PI / 2).normalize().dot(velocity);
    }

    public void resolveBoardCollision(ArrayList<Segment> sides){
        ArrayList<Segment> coll = new ArrayList<>();
        for(Segment s : sides){
            if(detectCollision(s))
                coll.add(s);
        }
        if(!coll.isEmpty()) {
            if(coll.size() == 1 || calculateDistAdjusted(coll.get(1)) < calculateDistAdjusted(coll.get(0)))
                resolveCollision(coll.get(0));
            else{
                resolveCollision(coll.get(1));
            }
        }

    };

    public boolean detectCollision(Segment s){
        return hitbox.checkForIntersection(s);
    }

    public void resolveCollision(Segment s){
        if(hitbox.checkForIntersection(s)){
            s.toVector2D().reflect(velocity);
            //position.add(s.toVector2D().normalize().rotate(-Math.PI / 2).multiply(Math.abs(getRadius() - s.perpendicularLength(position))));

                while(hitbox.checkForIntersection(s))
                    position.add(velocity.unit().multiply(0.4));


        }
    }

    public boolean resolveCollision(Ball b){
       return super.resolveCollision(b);

    }

    public void render(Graphics g){
        g.drawImage(img, (int) position.x, (int) position.y, null);
    }

    public void render(Graphics g, int x, int y){
        g.drawImage(img, x, y,null);
    }

    public boolean sameSuit(Ball b){
        return (this.getNum() != 0 && this.getNum() != 8 && b.getNum() != 0 && b.getNum() != 8) && ((this.getNum() > 8 && b.getNum() > 8) || (this.getNum() < 8 && b.getNum() < 8));
    }

}
