package com.game;

import com.physics.Segment;
import com.physics.Vector2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

public class WhiteBall extends Ball {
    public WhiteBall(Vector2D position, Vector2D velocity) throws IOException {
        super(position, velocity, 0);
    }

}
