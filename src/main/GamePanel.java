package main;

import GameObject.Tiles.TileManager;
import GameObject.entity.Bomberman;
import GameObject.entity.Enemies;
import GameObject.entity.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    public final int orignalTileSize = 16; //16x16 tile
    public final int scale = 3;
    public final int tileSize = orignalTileSize * scale; // Tile size --- 48x48
    final int maxScreenCol = 16;
    final int maxScreenRow = 13;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    //Create object
    Thread gameThread;
    KeyHandler keyH = new KeyHandler(this);

    public AssetSetter aSetter = new AssetSetter(this);
    public Bomberman bomberman = new Bomberman(this, keyH);
    public Entity enemy = new Enemies(this);
    Sound sound = new Sound();

    //World Settings
    public final int maxWorldCol = 31;
    public final int maxWorldRow = 13;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    //FPS
    int FPS = 60;
    TileManager tile = new TileManager(this);

    //MENU
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int loadLevel = 2;
    public int wait = -1;
    public int commandNum = 0;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }


    public void setUpGame() {
        gameState = titleState;
        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {

            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        bomberman.update();
        enemy.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //TITLE SCREEN
        if (gameState == titleState) {
            drawScreenTitle(g2);
        }
        if (gameState == loadLevel) {
            if (wait == -1) {
                stopMusic();
            }
            if (wait == 0) {
                playSE(4);
            }
            loadLevel(g2);
            wait++;
            if (wait == 180) {
                gameState = playState;
                playMusic(1);
            }
        }
        if (gameState == playState) {
            //OTHERS
            tile.draw(g2);
            bomberman.draw(g2);
            enemy.draw(g2);
        }
        g2.dispose();
    }

    public void loadLevel(Graphics2D g2) {
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 44F));
        String text = "LEVEL START!";
        int x = getTextCenterX(text, g2);
        int y = tileSize * 4;
        g2.setColor(Color.blue);
        g2.drawString(text, x, y);
    }

    /*
    //MENU
     */
    public void drawScreenTitle(Graphics2D g2) {

        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 44F));

        String text = "NEW GAME";
        int x = getTextCenterX(text, g2);
        int y = tileSize * 9;
        g2.setColor(Color.blue);
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - tileSize, y);
        }

        text = "QUIT";
        x = getTextCenterX(text, g2);
        y += tileSize;
        g2.setColor(Color.blue);
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - tileSize, y);
        }
    }

    public int getTextCenterX(String text, Graphics2D g2) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return screenWidth / 2 - length / 2;
    }

    /*/
    _______________________________________
    |                                     |
    |            MUSIC SETTINGS           |
    |_____________________________________|
     */
    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }
}
