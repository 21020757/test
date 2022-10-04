package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bomberman extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Bomberman(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bomberman/player/player_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bomberman/player/player_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bomberman/player/player_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bomberman/player/player_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bomberman/player/player_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bomberman/player/player_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bomberman/player/player_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bomberman/player/player_right_2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed) {
            y -= speed;
            direction = "up";
        } else if (keyH.downPressed) {
            y += speed;
            direction = "down";
        } else if (keyH.leftPressed) {
            x -= speed;
            direction = "left";
        } else if (keyH.rightPressed) {
            x += speed;
            direction = "right";
        }
    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        switch (direction) {
            case "up" -> image = up1;
            case "down" -> image = down1;
            case "left" -> image = left1;
            case "right" -> image = right1;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
