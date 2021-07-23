package com.game;
import com.physics.Segment;
import com.physics.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable{
    public static final double ticks = 120;
    BallHandler handler;
    private boolean isRunning = true;
    private Renderer r;
    private int clock = 0;
    boolean p1Turn = true;
    boolean shotAllowed = true;
    public static final double upper = 58, lower = 426, left = 58, right = 817;
    private ArrayList<Segment> sides;

    public static Segment initSide(Vector2D a, Vector2D b){
        Vector2D ad = new Vector2D(12.5, 12.5);
        return new Segment(a.subtract(ad), b.subtract(ad));
    }
    public static Segment initSide(double x, double y, double a, double b){
        return initSide(new Vector2D(x, y), new Vector2D(a, b));
    }

    public void initSides(){
        sides.add(initSide(42, 72, 58, 88));
        sides.add(initSide(58, 91, 58, 416));
        sides.add(initSide(68, 419, 41, 437));
        sides.add(initSide(66, 467, 83, 450));
        sides.add(initSide(88, 450, 413, 450));
        sides.add(initSide(416, 450, 423, 466));
        sides.add(initSide(466, 466, 473, 450));
        sides.add(initSide(476, 450, 807, 450));
        sides.add(initSide(810, 450, 826, 465));
        sides.add(initSide(858, 437, 841, 420));
        sides.add(initSide(841, 417, 841, 91));
        sides.add(initSide(841, 88, 857, 72));
        sides.add(initSide(827, 42, 810, 58));
        sides.add(initSide(807, 58, 476, 58));
        sides.add(initSide(473, 58, 466, 42));
        sides.add(initSide(423, 42, 415, 58));
        sides.add(initSide(412, 58, 85, 58));
        sides.add(initSide(82, 58, 66, 42));

    }

    public Game() throws IOException {

        this.r = new Renderer(this);
        new Window("8BallPool", r.board.getWidth(null), r.board.getHeight(null) + r.playerBar.getHeight(null) + 20, this);
        this.setIgnoreRepaint(true);
        this.createBufferStrategy(2);
        this.sides = new ArrayList<>();
        this.initSides();
        this.handler = new BallHandler(Socket.initSocks(), sides);
        this.addMouseListener(new MouseInput(handler.getWhiteBall(), this));
        new Thread(this).run();
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = Game.ticks;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        //gameLoop
        while(isRunning) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
            clock = (clock + 1) % (int) (ticks / 10 + 1);
            if(clock == 0 && !handler.checkForMovement() && !shotAllowed) {
                p1Turn = !p1Turn;
                shotAllowed = true;
            }
        }

        try {
            stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void render(){
        r.render();

    }

    private void stop(){

    }

    private void tick(){
        handler.tick();
    }

    public static void main(String args[]){
        try {
            new Game();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
