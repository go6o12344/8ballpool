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
        System.out.println(e.getPoint());
        if(!shotAllowed)
            return;
        b.setVelocity(new Vector2D(e.getPoint()).subtract(b.position).normalize().multiply(16));
        shotAllowed = false;
        semiAllowCueMovement = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setCueMovementAllowed(boolean b){
        moveCueAllowed = b;
    }

    public void setShotAllowed(boolean b){
        shotAllowed = b;
    }

}
