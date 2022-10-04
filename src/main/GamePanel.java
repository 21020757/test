package main;

import entity.Bomberman;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int orignalTileSize = 16; //16x16 tile
    final int scale = 3;

    public final int tileSize = orignalTileSize * scale; // Tile size --- 48x48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Bomberman bomberman = new Bomberman(this, keyH);
    //FPS
    int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                if(remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        bomberman.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        bomberman.draw(g2);
        g2.dispose();
    }
}
