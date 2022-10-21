package main;

import GameObject.entity.Bomberman;
import GameObject.object.Bomb;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, enterPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
                gp.commandNum--;
                if (gp.commandNum < 0) {
                    gp.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.commandNum++;
                if (gp.commandNum > 1) {
                    gp.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
                if (gp.commandNum == 0) {
                    gp.gameState = gp.loadLevel;
                }
                if (gp.commandNum == 1) {
                    System.exit(0);
                }
            }
        }

        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_SPACE) {
                spacePressed = true;
            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                System.exit(1);
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
    }
}
