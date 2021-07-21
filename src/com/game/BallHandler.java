package com.game;

import java.util.ArrayList;

public class BallHandler {
    private ArrayList<Ball> balls;

    public void tick(){
        for(Ball ball : balls) {
            ball.tick();
            for(Ball b : balls)
                if(ball.getNum() < b.getNum())
                    ball.resolveCollision(b);

        }

    }

}
