package com.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;

public class Renderer {
    Game game;
    final Image board = ImageIO.read(new File("resources/board.png"));
    final Image playerBar = ImageIO.read(new File("resources/playerbar.png"));
    final Image todd = ImageIO.read(new File("resources/todd.png")).getScaledInstance(30, 30, 0);

    public Renderer(Game game) throws IOException {
        this.game = game;
    }
    public void render(){
        BufferStrategy bs = this.game.getBufferStrategy();
        Graphics g = bs.getDrawGraphics();
        g.drawImage(board, 0, 0, null);
        g.drawImage(playerBar, (board.getWidth(null) - playerBar.getWidth(null)) / 2, board.getHeight(null), null);
        g.drawImage(todd, (board.getWidth(null) - playerBar.getWidth(null)) / 2 + 150, board.getHeight(null) + 25, null);
        this.game.handler.render(g);
        g.dispose();
        bs.show();

    }

    public void renderTodd(){
        return;
    }
}
