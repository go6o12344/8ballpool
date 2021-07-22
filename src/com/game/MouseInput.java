package com.game;

import com.physics.Vector2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    WhiteBall b;
    Game game;

    public MouseInput(WhiteBall whiteBall, Game g) {
        b = whiteBall;
        game = g;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!game.shotAllowed)
            return;
        System.out.println(e.getPoint());
        b.setVelocity(new Vector2D(e.getPoint()).subtract(b.position).normalize().multiply(2));
        game.shotAllowed = false;
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
