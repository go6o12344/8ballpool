package com.game;

import com.physics.Segment;
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
    private ArrayList<Segment> sides;
    private RuleManager ruleManager;
    private Player p1, p2;

    public BallHandler(ArrayList<Socket> socks, ArrayList<Segment> sides, RuleManager rm, Player p1, Player p2) throws IOException {
        balls.add(new WhiteBall(new Vector2D(720, 240), new Vector2D(0 ,0)));
        double r = balls.get(0).getRadius();
        double s = r * Math.sqrt(3);
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
        this.sides = sides;
        this.p1 = p1;
        this.p2 = p2;
        ruleManager = rm;
    }

    public void tick(boolean p1Turn){
        for(Ball ball : balls) {
            ball.tick(sides);
            for(Socket sock : socks){
                if(sock.socketed(ball)){
                    forRemoval.add(ball);
                    if(!(ball instanceof WhiteBall) && !(ball.getNum() == 8))
                        ruleManager.pocketed = true;
                    break;
                }
            }
        }
        for(Ball ball : forRemoval){
            if(!(ball instanceof WhiteBall)) {
                balls.remove(ball);
                if(!ruleManager.breakShot && !ruleManager.firstIn && ball.getNum() != 8){
                    //dont touch this, is actually necessary
                    ruleManager.correctFirstHit = true;
                    ruleManager.firstIn = true;

                    if(p1Turn){
                            for(Ball b: balls){
                                if(ball.sameSuit(b)) {
                                    p1.addBall(b);
                                }
                                else if(b.getNum() != 0 && b.getNum() != 8){
                                    p2.addBall(b);
                                }
                            }
                    }
                    else{
                        for(Ball b: balls){
                            if(ball.sameSuit(b)){
                                p2.addBall(b);
                            }
                            else if(b.getNum() != 0 && b.getNum() != 8){
                                p1.addBall(b);
                            }
                        }
                    }
                }
                else if(ball.getNum() != 0 && ball.getNum() != 8){
                    p1.removeBall(ball);
                    p2.removeBall(ball);
                    if(ball.getNum() == 8){
                        ruleManager.pocketed8 = true;
                    }
                    if(p1.getBalls().size() == 0){
                        for(Ball b : balls){
                            if(ball.getNum() == 8){
                                p1.addBall(b);
                            }
                        }
                    }
                    else if(p2.getBalls().size() == 0){
                        for(Ball b : balls){
                            if(ball.getNum() == 8){
                                p2.addBall(b);
                            }
                        }
                    }
                }
                if(ball.getNum() == 8 && !ruleManager.breakShot){
                    ruleManager.pocketed8 = true;
                }
            }
            else {
                ball.setPosition(-50, -50);
                ball.velocity = new Vector2D(0, 0);
                ruleManager.pocketedCue = true;
            }
        }

        forRemoval.removeIf((x) -> true);

        for(Ball ball : balls){
            for(Ball b : balls)
                if(ball.getNum() < b.getNum()) {
                    if (ball.resolveCollision(b)) {
                        if (ruleManager.firstIn) {
                            if (p1Turn) {
                                if (!ruleManager.hitAnother) {
                                    ruleManager.hitAnother = true;
                                    System.out.println(b);
                                    if (p1.getBalls().contains(b)) {
                                        ruleManager.correctFirstHit = true;
                                    }
                                }
                            } else {
                                if (!ruleManager.hitAnother) {
                                    ruleManager.hitAnother = true;
                                    System.out.println(b);
                                    if (p2.getBalls().contains(b)) {
                                        System.out.println(b);
                                        ruleManager.correctFirstHit = true;
                                    }
                                }
                            }
                        }
                        else{
                            ruleManager.hitAnother = true;
                        }
                    }
                }
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
