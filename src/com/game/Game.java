package com.game;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;

public class Game extends Canvas implements Runnable{
    private static double ticks = 300;
    private BallHandler handler;
    private boolean isRunning = true;
    private final Image board = ImageIO.read(new File("resources/board.png"));
    public static final double upper = 41, lower = 439, left = 41, right = 829;

    public Game() throws IOException {
        new Window("8BallPool", board.getWidth(null), board.getHeight(null), this);
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
        BufferStrategy bs = this.getBufferStrategy();
        Graphics g = bs.getDrawGraphics();
        g.drawImage(board, 0, 0, null);
        handler.render(g);
        g.dispose();
        bs.show();

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
