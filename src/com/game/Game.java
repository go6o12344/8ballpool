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
    public static double ballRadius = 12.5;
    BallHandler handler;
    private boolean isRunning = true;
    private Renderer r;
    private int clock = 0;
    boolean p1Turn = true;
    public Player p1, p2;
    private ArrayList<Segment> sides;
    private RuleManager ruleManager;
    private MouseInput mouseInput;

    public static Segment initSide(Vector2D a, Vector2D b){
        Vector2D ad = new Vector2D(ballRadius, ballRadius);
        return new Segment(a.subtract(ad), b.subtract(ad));
    }
    public static Segment initSide(double x, double y, double a, double b){
        return initSide(new Vector2D(x, y), new Vector2D(a, b));
    }

    public void initSides(){
        sides.add(initSide(42, 72, 58, 88));
        sides.add(initSide(61, 88, 60, 416));
        sides.add(initSide(68, 419, 41, 437));
        sides.add(initSide(66, 467, 83, 450));
        sides.add(initSide(86, 450, 413, 450));
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
        sides.add(initSide(423, 42, 416, 57));
        sides.add(initSide(413, 57, 85, 58));
        sides.add(initSide(82, 58, 66, 42));

    }


    public Game() throws IOException {

        this.r = new Renderer(this);
        this.ruleManager = new RuleManager();
        p1 = new Player();
        p2 = new Player();
        new Window("8BallPool", r.board.getWidth(null), r.board.getHeight(null) + r.playerBar.getHeight(null) + 42 + 2 * (int) Game.ballRadius, this);
        this.setIgnoreRepaint(true);
        this.createBufferStrategy(2);
        this.sides = new ArrayList<>();
        this.initSides();
        this.handler = new BallHandler(Socket.initSocks(), sides, ruleManager, p1, p2);
        this.mouseInput = new MouseInput(handler.getWhiteBall());
        this.addMouseListener(this.mouseInput);
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
            clock = (clock + 1) % (int) (ticks / 2 + 1);
            if(clock == 0 && !handler.checkForMovement() && !mouseInput.isShotAllowed()) {
                try {
                    handleShotOver();
                    if(win()) break;
                    if(lose()) break;
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
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
        handler.tick(p1Turn);
    }

    public static void main(String args[]){
        try {
            new Game();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleShotOver() throws IOException {
        System.out.println(ruleManager);
        repositionWhiteBall();
        if(ruleManager.changeTurn())
            p1Turn = !p1Turn;
        if(ruleManager.rebreak()) {
            p1 = new Player();
            p2 = new Player();
            handler = new BallHandler(Socket.initSocks(), sides, ruleManager, p1, p2);
            mouseInput.b = handler.getWhiteBall();
            mouseInput.setShotAllowed(true);
            return;
        }
        if(ruleManager.checkForFoul()){
            mouseInput.setCueMovementAllowed(true);
        }
        if(p1.getBalls().size() > 0 || p2.getBalls().size() > 0){
            ruleManager.firstIn = true;
        }
        mouseInput.setShotAllowed(true);
        ruleManager.resetValues();

    }

    public void repositionWhiteBall(){
        if(ruleManager.pocketedCue)
            handler.getWhiteBall().setPosition(720, 240);
    }
    public boolean win() throws InterruptedException {
        if(ruleManager.win(p1Turn, p1.getBalls().size(), p2.getBalls().size())){
            r.renderWin(p1Turn);
            Thread.sleep(5000);
            return true;
        }
        return false;
    }
    public boolean lose() throws InterruptedException {
        if(ruleManager.lose(p1Turn, p1.getBalls().size(), p2.getBalls().size())){
            r.renderWin(!p1Turn);
            Thread.sleep(5000);
            return true;
        }
        return false;
    }

}
