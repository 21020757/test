package main;


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

    public int delayTime = 0;
    //Create object
    Thread gameThread;
    KeyHandler keyH = new KeyHandler(this);

    public Bomberman bomberman;
    public int lengthEnemies = 0, lengthOneal = 0;
    public int checkEnemies = 0, checkOneal = 0;
    public final int totalTypeOfEnemies = 2;
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
    public TileManager tile = new TileManager(this);

    //MENU
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int loadLevel = 2;
    public final int replayState = 3;
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
        playMusic(0);
        gameState = titleState;
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
        bomberman = new Bomberman(this, keyH);
        enemy = new Enemies[lengthEnemies];
        oneals = new Oneal[lengthOneal];
        int a = 0, b = 0;
        for (int i = 0; i < maxWorldRow; i++) {
            for (int j = 0; j < maxWorldCol; j++) {
                char s = tile.map[i][j];
                if (s == '1') {
                    enemy[a] = new Enemies(this);
                    enemy[a].setEnemies(j * tileSize, i * tileSize);
                    a++;
                }
                if (s == '2') {
                    oneals[b] = new Oneal(this);
                    oneals[b].setEnemies(j * tileSize, i * tileSize);
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
        if (!bomberman.isDead) {
            bomberman.update();
        }
        for (Enemies enemies : enemy) {
            if (enemies != null) {
                checkEnemies = 0;
                if (!enemies.isDead) {
                    enemies.update();
                }
            } else {
                checkEnemies = 1;
            }
        }

        for (Oneal oneal : oneals) {
            if (oneal != null) {
                checkOneal = 0;
                if (!oneal.isDead) {
                    oneal.update();
                }
            } else {
                checkOneal = 1;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (gameState == titleState || gameState == loadLevel) {
            menu.draw(g2);
        }
        if (gameState == playState) {
            tile.draw(g2);
            if (!bomberman.isDead) {
                bomberman.draw(g2);
            } else {
                delayTime++;
                bomberman.drawDead(g2);
                if (delayTime == 90) {
                    stopMusic();
                    playSE(5);
                }
                if (delayTime == 240) {
                    gameState = replayState;
                    delayTime = 0;
                }
            }
            drawEnemies(g2);
            drawOneals(g2);

            if (checkOneal + checkEnemies == totalTypeOfEnemies) {
                if (bomberman.win) {
                    delayTime++;
                    if (delayTime == 90) {
                        stopMusic();
                        playSE(6);
                    }
                    if (delayTime == 240) {
                        gameState = replayState;
                        delayTime = 0;
                    }
                }
            }
        }
        if (gameState == replayState) {
            menu.draw(g2);
        }
        g2.dispose();
    }


    /*/
    _______________________________________
    |                                     |
    |            MUSIC SETTINGS           |
    |_____________________________________|
     */
    public void playMusic(int i) {
        sound.setFile(i);
        sound.play(i);
        sound.loop(i);
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play(i);
    }

    public void drawEnemies(Graphics2D g2) {
        for (int i = 0; i < enemy.length; i++) {
            if (enemy[i] != null) {
                if (!enemy[i].isDead) {
                    enemy[i].draw(g2);
                } else {
                    delayTime++;
                    enemy[i].drawDead(g2);
                    if (delayTime == 60) {
                        enemy[i] = null;
                        delayTime = 0;
                    }
                }
            }
        }
    }

    public void drawOneals(Graphics2D g2) {
        for (int i = 0; i < oneals.length; i++) {
            if (oneals[i] != null) {
                if (!oneals[i].isDead) {
                    oneals[i].draw(g2);
                } else {
                    delayTime++;
                    oneals[i].drawDead(g2);
                    if (delayTime == 60) {
                        oneals[i] = null;
                        delayTime = 0;
                    }
                }
            }
        }
    }

    public void resetGame() {
        bomberman = new Bomberman(this, keyH);
        tile.loadMap();
        tile.createObject();
        lengthEnemies = 0;
        lengthOneal = 0;
        setUpGame();
    }
}
