package com.game;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;

public class Game extends Canvas implements Runnable{
    public static final double ticks = 90;
    BallHandler handler;
    private boolean isRunning = true;
    private Renderer r;

    public static final double upper = 58, lower = 421, left = 58, right = 812;

    public Game() throws IOException {
        r = new Renderer(this);
        new Window("8BallPool", r.board.getWidth(null), r.board.getHeight(null) + r.playerBar.getHeight(null) + 20, this);
        this.setIgnoreRepaint(true);
        this.createBufferStrategy(2);
        handler = new BallHandler();
        this.addMouseListener(new MouseInput(handler.getWhiteBall()));
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
