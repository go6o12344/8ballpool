package com.game;

import com.physics.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BallHandler {
    private ArrayList<Ball> balls = new ArrayList<>();
    private ArrayList<Socket> socks;
    private ArrayList<Ball> forRemoval = new ArrayList<>();
    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public BallHandler(ArrayList<Socket> socks) throws IOException {
        balls.add(new WhiteBall(new Vector2D(720, 240), new Vector2D(0 ,0)));
        double r = balls.get(0).getRadius() + 0.05;
        double s = r * Math.sqrt(3) + 0.05;
        balls.add(new Ball(new Vector2D(150 + 2 * s, 240 + 2 * r), new Vector2D(0, 0),1));
        balls.add(new Ball(new Vector2D(150, 240 - 2 * r), new Vector2D(0,0), 2));
        balls.add(new Ball(new Vector2D(150 + s, 240 + r), new Vector2D(0, 0),3));
        balls.add(new Ball(new Vector2D(150, 240 + 2 * r), new Vector2D(0, 0),4));
        balls.add(new Ball(new Vector2D(150, 240 + 4 * r), new Vector2D(0, 0),5));
        balls.add(new Ball(new Vector2D(150 + s, 240 - 3 * r), new Vector2D(0, 0),6));
        balls.add(new Ball(new Vector2D(150 + 3 * s, 240 - r), new Vector2D(0, 0),7));
        balls.add(new Ball(new Vector2D(150 + 2 * s, 240), new Vector2D(0, 0),8));
        balls.add(new Ball(new Vector2D(150 + 4 * s, 240), new Vector2D(0, 0),9));
        balls.add(new Ball(new Vector2D(150 + s, 240 - r), new Vector2D(0, 0),10));
        balls.add(new Ball(new Vector2D(150, 240 - 4 * r), new Vector2D(0,0), 11));
        balls.add(new Ball(new Vector2D(150 + 3 * s, 240 + r), new Vector2D(0, 0),12));
        balls.add(new Ball(new Vector2D(150, 240), new Vector2D(0, 0),13));
        balls.add(new Ball(new Vector2D(150 + s, 240 + 3 * r), new Vector2D(0, 0),14));
        balls.add(new Ball(new Vector2D(150 + 2 * s, 240 - 2 * r), new Vector2D(0, 0),15));
        this.socks = socks;
    }

    public void tick(){
        for(Ball ball : balls) {
            boolean kekek = true;
            for(Socket sock : socks){
                if(sock.in(ball)){
                   kekek = false;
                    ball.tick(sock);
                   break;
                }
            }
            if(kekek)
                ball.tick(null);
            for(Socket sock : socks){
                if(sock.socketed(ball)){
                    forRemoval.add(ball);
                    break;
                }
            }
        }
        for(Ball ball : forRemoval){
            balls.remove(ball);
        }

        forRemoval.removeIf((x) -> true);

        for(Ball ball : balls){
            for(Ball b : balls)
                if(ball.getNum() < b.getNum())
                    ball.resolveCollision(b);
        }

    }

    public void render(Graphics g){
        for(Ball ball : balls) {
            ball.render(g);
        }
    }

    public boolean checkForMovement(){
        for(Ball ball : balls)
            if(!ball.velocity.isNull())
                return true;
        return false;
    }

    public WhiteBall getWhiteBall(){
        return (WhiteBall) this.balls.get(0);
    }

}
