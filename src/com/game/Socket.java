package com.game;

import com.physics.Vector2D;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Socket {
    public ArrayList<Rectangle2D.Double> hitbox = new ArrayList<>();
    public Rectangle2D.Double inHitbox;

    public static ArrayList<Socket> initSocks(){
        Socket upperLeft = new Socket();
        upperLeft.hitbox.add(new Rectangle2D.Double(17, 22, 50, 50));
        upperLeft.hitbox.add(new Rectangle2D.Double(48, 48, 40, 45));
        upperLeft.hitbox.add(new Rectangle2D.Double(45, 51, 35, 29));
        upperLeft.inHitbox = new Rectangle2D.Double(17, 22, 29, 29);
        ArrayList<Socket> s = new ArrayList<>();
        s.add(upperLeft);
        return s;
    }

    public Socket() {

    }

    public boolean in(Ball b){
        for(Rectangle2D.Double r : hitbox) {
            if(b.position.in(r)) {
                if(b.getNum() == 0)
                    System.out.println("In");
                return true;
            }
        }
        if(b.getNum() == 0)
            System.out.println("Not in");
        return false;
    }

    public boolean socketed(Ball b){
        return b.position.in(inHitbox);
    }

}
