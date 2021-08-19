package com.game;

import com.physics.Circle;
import com.physics.Segment;
import com.physics.Vector2D;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Socket {
    public Circle inHitbox;

    public static ArrayList<Socket> initSocks(){
        Vector2D accountForPositionDisplacement = new Vector2D(Game.ballRadius, Game.ballRadius);
        Socket upperLeft = new Socket();
        upperLeft.inHitbox = new Circle(30, new Vector2D(42, 47).subtract(accountForPositionDisplacement));
        Socket midUpper = new Socket();
        midUpper.inHitbox = new Circle(30, new Vector2D(444, 36).subtract(accountForPositionDisplacement));
        Socket upperRight = new Socket();
        upperRight.inHitbox = new Circle(30, new Vector2D(851,46).subtract(accountForPositionDisplacement));
        Socket lowerLeft = new Socket();
        lowerLeft.inHitbox = new Circle(30, new Vector2D(42, 463).subtract(accountForPositionDisplacement));
        Socket midLower = new Socket();
        midLower.inHitbox = new Circle(30, new Vector2D(444, 472).subtract(accountForPositionDisplacement));
        Socket lowerRight = new Socket();
        lowerRight.inHitbox = new Circle(30, new Vector2D(851, 462).subtract(accountForPositionDisplacement));

        ArrayList<Socket> s = new ArrayList<>();
        s.add(upperLeft);
        s.add(midUpper);
        s.add(upperRight);
        s.add(lowerLeft);
        s.add(midLower);
        s.add(lowerRight);
        return s;
    }

    public Socket() {

    }

    public boolean socketed(Ball b){
        return b.hitbox.center.difference(inHitbox.center).dot(b.hitbox.center.difference(inHitbox.center)) < 750;
    }

}
