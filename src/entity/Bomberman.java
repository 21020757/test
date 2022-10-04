package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Handler;

public class Bomberman extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    public static final int intervalImageChange = 9;
    public boolean notMoving;
    public String preDirection;
    public Bomberman(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        preDirection = "down";
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
            up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bomberman/player/player_up.png")));
            down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bomberman/player/player_down.png")));
            left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bomberman/player/player_left.png")));
            right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/bomberman/player/player_right.png")));
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
        if (keyH.upPressed || keyH.downPressed
                || keyH.leftPressed || keyH.rightPressed) {
            notMoving = false;
            if (keyH.upPressed) {
                y -= speed;
                direction = "up";
                preDirection = "up";
            } else if (keyH.downPressed) {
                y += speed;
                direction = "down";
                preDirection = "down";
            } else if (keyH.leftPressed) {
                x -= speed;
                direction = "left";
                preDirection = "left";
            } else if (keyH.rightPressed) {
                x += speed;
                direction = "right";
                preDirection = "right";
            }
            spriteCounter++;
            if (spriteCounter > intervalImageChange) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            notMoving = true;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        if (notMoving) {
            switch (preDirection) {
                case "up" -> image = up;
                case "down" -> image = down;
                case "left" -> image = left;
                case "right" -> image = right;
            }
        } else {
            switch (direction) {
                case "up" -> {
                    if (spriteNum == 1) {
                        image = up1;
                    } else if (spriteNum == 2) {
                        image = up2;
                    }
                }
                case "down" -> {
                    if (spriteNum == 1) {
                        image = down1;
                    } else if (spriteNum == 2) {
                        image = down2;
                    }
                }
                case "left" -> {
                    if (spriteNum == 1) {
                        image = left1;
                    } else if (spriteNum == 2) {
                        image = left2;
                    }
                }
                case "right" -> {
                    if (spriteNum == 1) {
                        image = right1;
                    } else if (spriteNum == 2) {
                        image = right2;
                    }
                }
            }
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
