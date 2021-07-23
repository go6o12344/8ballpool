package com.game;

import com.physics.Vector2D;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Socket {
    public ArrayList<Rectangle2D.Double> hitbox = new ArrayList<>();
    public Rectangle2D.Double inHitbox;
    public Rectangle2D.Double notActuallyInHitbox;

    public static ArrayList<Socket> initSocks(){
        Socket upperLeft = new Socket();
        upperLeft.hitbox.add(new Rectangle2D.Double(17, 22, 35, 35));
        upperLeft.hitbox.add(new Rectangle2D.Double(48, 48, 30, 35));
        upperLeft.hitbox.add(new Rectangle2D.Double(45, 51, 35, 29));
        upperLeft.inHitbox = new Rectangle2D.Double(17, 22, 24, 24);
        upperLeft.notActuallyInHitbox = new Rectangle2D.Double(26, 31, 15, 15);
        ArrayList<Socket> s = new ArrayList<>();
        s.add(upperLeft);
        return s;
    }

    public Socket() {

    }

    public boolean in(Ball b){
        for(Rectangle2D.Double r : hitbox) {
            if(b.position.in(r)) {
                return true;
            }
        }
        return false;
    }

    public boolean socketed(Ball b){
        return b.position.in(inHitbox) && !b.position.in(notActuallyInHitbox);
    }

}
