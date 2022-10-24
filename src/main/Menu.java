package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Menu {
    BufferedImage menuImg;
    GamePanel gp;

    public Menu(GamePanel gp) {
        this.gp = gp;
        try {
            menuImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/background/bombermanipod-ign-1648079279289.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        if (gp.gameState == gp.titleState) {
            g2.drawImage(menuImg, 0, 0, gp.screenWidth, gp.screenHeight, null);
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

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
        String text = "NEW GAME";
        int x = gp.getTextCenterX(text, g2);
        int y = gp.tileSize * 8;
        g2.setColor(Color.RED);
        g2.drawString(text, x, y);
        if (gp.commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = gp.getTextCenterX(text, g2);
        y += gp.tileSize;
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
