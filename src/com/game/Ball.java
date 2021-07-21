package com.game;

import com.physics.Particle;
import com.physics.Vector2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

public class Ball extends Particle {
    private final Image img;
    private final int numero;
    public Ball(Vector2D position, Vector2D velocity, int numero) throws IOException {
        super(0.16, 15, position, velocity);
        this.numero = numero;
        this.img = ImageIO.read(new File("../../../resources" + this.numero));
    }

    public Image getImg() {
        return img;
    }

    public int getNum() {
        return numero;
    }

    public void tick(){
        this.tick(9.81, 0.02, 1.168, 0.5);
    }

}
