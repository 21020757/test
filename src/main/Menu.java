package main;

import java.awt.*;

public class Menu {
    public int commandNum = 0;

    public void drawTitleScreen(Graphics2D g2, GamePanel gp) {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Bomberman";

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        int y = gp.tileSize * 3;

        g2.setColor(Color.GRAY);
        g2.drawString(text, x + 5, y + 5);

        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 42F));

        text = "NEW GAME";
        length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - length/2;
        y += gp.tileSize * 4;
        g2.drawString(text, x , y);
        if(commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - length/2;
        y += gp.tileSize;
        g2.drawString(text, x , y);
        if(commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }
}
