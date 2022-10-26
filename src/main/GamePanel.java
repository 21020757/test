package main;


import GameObject.Tiles.TileManager;
import GameObject.entity.Bomberman;
import GameObject.entity.Enemies;
import GameObject.entity.Oneal;

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
    //Create object
    Thread gameThread;
    KeyHandler keyH = new KeyHandler(this);

    public AssetSetter aSetter = new AssetSetter(this);
    public Bomberman bomberman = new Bomberman(this, keyH);
    public int lengthEnemies = 0, lengthOneal = 0;
    public Enemies[] enemy;

    public Oneal[] oneals;

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
    public final int pauseState = 3;
    public int loadGameInterval = -1;
    public int commandNum = 0;
    Menu menu = new Menu(this);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.setUpGame();
    }


    public void setUpGame() {
        gameState = titleState;
        playMusic(0);
        for (int i = 0; i < maxWorldRow; i++) {
            for (int j = 0; j < maxWorldCol; j++) {
                char s = tile.map[i][j];
                if (s == '1') {
                    lengthEnemies++;
                }
                if (s == '2') {
                    lengthOneal++;
                }
            }
        }
        enemy = new Enemies[lengthEnemies];
        oneals = new Oneal[lengthOneal];
        int a = 0, b=0;
        for (int i = 0; i < maxWorldRow; i++) {
            for (int j = 0; j < maxWorldCol; j++) {
                char s = tile.map[i][j];
                if (s == '1') {
                    enemy[a] = new Enemies(this);
                    enemy[a].setEnemies(j * tileSize,i * tileSize);
                    a++;
                }
                if (s ==  '2') {
                    oneals[b] = new Oneal(this);
                    oneals[b].setEnemies(j * tileSize, i*tileSize);
                    b++;
                }
            }
        }
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
        for (Enemies enemies : enemy) {
            if (enemies != null) {
                enemies.update();
            }
        }
        for (Oneal oneal : oneals) {
            if (oneal != null) {
                oneal.update();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        menu.draw(g2);
        if (gameState == playState) {
            //OTHERS
            tile.draw(g2);
            bomberman.draw(g2);
            for (Enemies enemies : enemy) {
                if (enemies != null) {
                    enemies.draw(g2);
                }
            }
            for (Oneal oneal : oneals) {
                if (oneal != null) {
                    oneal.draw(g2);
                }
            }
        }
        g2.dispose();
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
