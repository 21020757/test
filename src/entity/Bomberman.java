package entity;

import main.GamePanel;
import main.KeyHandler;
import object.Bomb;

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
    public int ScreenX;

    public Bomb bomb;

    public BufferedImage bomb0, bomb1, bomb2, exploded, exploded1, exploded2;
    public int frameBomb = 0, intervalBomb = 7, indexAniBomb = 0;

    public Bomberman(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        preDirection = "down";
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = gp.tileSize;
        y = gp.tileSize;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            //Move
            up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/player_up.png")));
            down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/player_down.png")));
            left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/player_left.png")));
            right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/player_right.png")));
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/player_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/player_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/player_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/player_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/player_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/player_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/player_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/player_right_2.png")));

            //Bomb
            bomb0 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/bomb.png")));
            bomb1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/bomb_1.png")));
            bomb2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/bomb_2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        System.out.println(x + " " + y);
        if (keyH.upPressed || keyH.downPressed
                || keyH.leftPressed || keyH.rightPressed || keyH.spacePressed) {
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
            } else if (keyH.spacePressed) {
                if (bomb == null) {
                    bomb = new Bomb();
                    bomb.x = x;
                    bomb.y = y;
                }
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

        if (bomb != null) {
            frameBomb++;
            if (frameBomb == intervalBomb) {
                frameBomb = 0;
                indexAniBomb++;
                if (indexAniBomb > 2) {
                    indexAniBomb = 0;
                    bomb.countToExplode++;
                }
                if (bomb.countToExplode >= bomb.intervalToExplode) {
                    bomb.exploded = true;
                }
            }
            if (bomb.exploded) {
                bomb = null;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        BufferedImage bombImg = null;
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

        if (bomb != null) {
            if (indexAniBomb == 0) {
                bombImg = bomb0;
            } else if (indexAniBomb == 1) {
                bombImg = bomb1;
            } else if (indexAniBomb == 2) {
                bombImg = bomb2;
            }
            if (x < 8 * 48) {
                g2.drawImage(bombImg, bomb.x, bomb.y, gp.tileSize, gp.tileSize, null);
            } else if (bomb.x < 8 * 48 && x >= 8 * 48) {
                int bombScreenX = bomb.x - x + 8 * 48;
                g2.drawImage(bombImg, bombScreenX, bomb.y, gp.tileSize, gp.tileSize, null);
            } else if (bomb.x >= 8 * 48 && bomb.x <= 23 * 48 && x >= 8 * 48 && x <= 23 * 48) {
                int bombScreenX = ScreenX + bomb.x - x;
                g2.drawImage(bombImg, bombScreenX, bomb.y, gp.tileSize, gp.tileSize, null);
            } else if (x > 23 * 48) {
                int bombScreenX = bomb.x - gp.wordWidth + gp.screenWidth;
                g2.drawImage(bombImg, bombScreenX, bomb.y, gp.tileSize, gp.tileSize, null);
            } else if (bomb.x > 23 * 48 && x <= 23 * 48) {
                int bombScreenX = bomb.x - x + gp.screenWidth / 2;
                g2.drawImage(bombImg, bombScreenX, bomb.y, gp.tileSize, gp.tileSize, null);
            }
        }

        if (x >= 8 * 48 && x <= 23 * 48) {
            ScreenX = gp.screenWidth / 2;
            g2.drawImage(image, ScreenX, y, gp.tileSize, gp.tileSize, null);
        } else if (x < 8 * 48) {
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        } else {
            ScreenX = x - gp.wordWidth + gp.screenWidth;
            g2.drawImage(image, ScreenX, y, gp.tileSize, gp.tileSize, null);
        }
    }
}
