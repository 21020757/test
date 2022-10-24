package main;

import java.awt.*;

public class Menu {
    GamePanel gp;

    public Menu(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        if (gp.gameState == gp.titleState) {
            drawScreenTitle(g2);
        }
        if (gp.gameState == gp.loadLevel) {
            if (gp.loadGameInterval == 0) {
                gp.stopMusic();
                gp.playSE(4);
            }
            loadLevel(g2);
            gp.loadGameInterval++;
            if (gp.loadGameInterval == 180) {
                gp.gameState = gp.playState;
                gp.playMusic(1);
            }
        }
    }


    /*
//MENU
 */
    public void drawScreenTitle(Graphics2D g2) {

        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 44F));

        String text = "NEW GAME";
        int x = gp.getTextCenterX(text, g2);
        int y = gp.tileSize * 9;
        g2.setColor(Color.blue);
        g2.drawString(text, x, y);
        if (gp.commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = gp.getTextCenterX(text, g2);
        y += gp.tileSize;
        g2.setColor(Color.blue);
        g2.drawString(text, x, y);
        if (gp.commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void loadLevel(Graphics2D g2) {
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 44F));
        String text = "LEVEL START!";
        int x = gp.getTextCenterX(text, g2);
        int y = gp.tileSize * 4;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
    }
}
