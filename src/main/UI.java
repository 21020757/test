package main;

import java.awt.*;
//For displaying play time
public class UI {
    GamePanel gp;
    Font arial_40;
    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void draw (Graphics2D g2) {
        g2.setFont(arial_40);
        g2.setColor(Color.white);
    }
}
