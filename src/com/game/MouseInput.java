package com.game;

import com.physics.Vector2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    WhiteBall b;
    private boolean moveCueAllowed;
    private boolean semiAllowCueMovement = true;
    private boolean shotAllowed = true;

    public MouseInput(WhiteBall whiteBall) {
        b = whiteBall;

    }

    public boolean isShotAllowed() {
        return shotAllowed;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    double startTime, endTime, holdTime;
    boolean flag = false;

    @Override
    public final void mousePressed(final MouseEvent e) {
        startTime = System.nanoTime();
        flag = true;
    }

    @Override
    public final void mouseReleased(final MouseEvent e) {
        if(flag) {
            endTime = System.nanoTime();
            flag = false;
        }
        holdTime = (endTime - startTime) / Math.pow(10,9);
        System.out.println(e.getPoint());
        if(!shotAllowed)
            return;
        b.setVelocity(new Vector2D(e.getPoint()).subtract(b.position).normalize().multiply(300/Game.ticks).multiply(Math.min(0.85 + 0.85 * holdTime, 3)));
        shotAllowed = false;
        semiAllowCueMovement = false;
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        shotAllowed = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        shotAllowed = false;
    }

    public void setCueMovementAllowed(boolean b){
        moveCueAllowed = b;
    }

    public void setShotAllowed(boolean b){
        shotAllowed = b;
    }

}
