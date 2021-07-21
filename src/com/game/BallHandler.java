package com.game;

import com.physics.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BallHandler {
    private ArrayList<Ball> balls = new ArrayList<>();

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public BallHandler() throws IOException {
        balls.add(new WhiteBall(new Vector2D(720, 240), new Vector2D(0 ,0)));
        balls.add(new Ball(new Vector2D(210, 270), new Vector2D(0, 0),1));
        balls.add(new Ball(new Vector2D(150, 210), new Vector2D(0,0), 2));
        balls.add(new Ball(new Vector2D(180, 255), new Vector2D(0, 0),3));
        balls.add(new Ball(new Vector2D(150, 270), new Vector2D(0, 0),4));
        balls.add(new Ball(new Vector2D(150, 300), new Vector2D(0, 0),5));
        balls.add(new Ball(new Vector2D(180, 195), new Vector2D(0, 0),6));
        balls.add(new Ball(new Vector2D(240, 225), new Vector2D(0, 0),7));
        balls.add(new Ball(new Vector2D(210, 240), new Vector2D(0, 0),8));
        balls.add(new Ball(new Vector2D(270, 240), new Vector2D(0, 0),9));
        balls.add(new Ball(new Vector2D(180, 225), new Vector2D(0, 0),10));
        balls.add(new Ball(new Vector2D(150, 180), new Vector2D(0,0), 11));
        balls.add(new Ball(new Vector2D(240, 255), new Vector2D(0, 0),12));
        balls.add(new Ball(new Vector2D(150, 240), new Vector2D(0, 0),13));
        balls.add(new Ball(new Vector2D(180, 285), new Vector2D(0, 0),14));
        balls.add(new Ball(new Vector2D(210, 210), new Vector2D(0, 0),15));
    }

    public void tick(){
        for(Ball ball : balls) {
            ball.tick();
        }
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

    public WhiteBall getWhiteBall(){
        return (WhiteBall) this.balls.get(0);
    }

}
