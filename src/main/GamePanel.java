package main;

import Tiles.TileManager;
import entity.Bomberman;
import object.SuperObject;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    public final int orignalTileSize = 16; //16x16 tile
    public final int scale = 3;

    public final int tileSize = orignalTileSize * scale; // Tile size --- 48x48
    final int maxScreenCol = 16;
    final int maxScreenRow = 13;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    KeyHandler keyH = new KeyHandler();
    //Create object
    Thread gameThread;
    public AssetSetter aSetter = new AssetSetter(this);
    public Bomberman bomberman = new Bomberman(this, keyH);
    public SuperObject[] obj = new SuperObject[10];
    //World Settings
    public final int maxWorldCol = 31;
    public final int maxWorldRow = 13;
    public final int wordWidth = tileSize * maxWorldCol;
    public final int wordHeight = tileSize * maxWorldRow;
    //FPS
    int FPS = 60;
    TileManager tile = new TileManager(this);
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
        tile.draw(g2);
        bomberman.draw(g2);

        g2.dispose();
    }
}
