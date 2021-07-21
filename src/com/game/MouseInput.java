package com.game;

import com.physics.Vector2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    WhiteBall b;

    public MouseInput(WhiteBall whiteBall) {
        b = whiteBall;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getPoint());
        b.setVelocity(new Vector2D(e.getPoint()).subtract(b.position).normalize().multiply(3));
        System.out.println(b);
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
}
