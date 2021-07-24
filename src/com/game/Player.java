package com.game;

import java.util.ArrayList;
import java.awt.*;

public class Player {
    private ArrayList<Ball> myBalls = new ArrayList<>();
    public void addBall(Ball b){
        myBalls.add(b);
    }
    public boolean removeBall(Ball b){
        return myBalls.remove(b);
    }
    public ArrayList<Ball> getBalls(){
        return myBalls;
    }

    public void render(Graphics g, int x, int y){
        g.setColor(new Color(0, 0, 93));
        for(int i = 0; i < 7; i++){
            g.fillOval(x + (6 + 2 * (int) Game.ballRadius) * i, y, 2 * (int) Game.ballRadius + 6, 2 * (int) Game.ballRadius + 6);
        }
        for(Ball ball : myBalls){
            ball.render(g, x+3, y+3);
            x += 5 + 2 * Game.ballRadius;
        }
    }
}
