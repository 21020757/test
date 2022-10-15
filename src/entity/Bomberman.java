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

    public BufferedImage[] bombing = new BufferedImage[3];
    public BufferedImage[] fontExplosion = new BufferedImage[3];
    public BufferedImage[] upExplosion = new BufferedImage[3];
    public BufferedImage[] downExplosion = new BufferedImage[3];
    public BufferedImage[] leftExplosion = new BufferedImage[3];
    public BufferedImage[] rightExplosion = new BufferedImage[3];

    public int frameBomb = 0, intervalBomb = 7, indexAniBomb = 0;
    public int frameExplosion = 0, intervalExplosion = 4, indexAniExplosion = 0;

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
            bombing[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/bomb.png")));
            bombing[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/bomb_1.png")));
            bombing[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/bomb_2.png")));


            fontExplosion[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/bomb_exploded.png")));
            fontExplosion[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/bomb_exploded1.png")));
            fontExplosion[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/bomb_exploded2.png")));

            upExplosion[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/explosion_vertical_top_last.png")));
            upExplosion[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/explosion_vertical_top_last1.png")));
            upExplosion[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/explosion_vertical_top_last2.png")));

            downExplosion[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/explosion_vertical_down_last.png")));
            downExplosion[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/explosion_vertical_down_last1.png")));
            downExplosion[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/explosion_vertical_down_last2.png")));

            leftExplosion[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/explosion_horizontal_left_last.png")));
            leftExplosion[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/explosion_horizontal_left_last1.png")));
            leftExplosion[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/explosion_horizontal_left_last2.png")));

            rightExplosion[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/explosion_horizontal_right_last.png")));
            rightExplosion[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/explosion_horizontal_right_last1.png")));
            rightExplosion[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/object/explosion_horizontal_right_last2.png")));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
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
            //bomb animation
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
            //bomb explode animation
            if (bomb.exploded) {
                frameExplosion++;
                if (frameExplosion == intervalExplosion) {
                    frameExplosion = 0;
                    indexAniExplosion++;
                    if (indexAniExplosion == 3) {
                        indexAniExplosion = 0;
                        bomb = null;
                    }
                }
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
            if (bomb.exploded) {
                g2.drawImage(fontExplosion[indexAniExplosion], bomb.x, bomb.y, gp.tileSize, gp.tileSize, null);
                g2.drawImage(upExplosion[indexAniExplosion], bomb.x, bomb.y - gp.tileSize, gp.tileSize, gp.tileSize, null);
                g2.drawImage(downExplosion[indexAniExplosion], bomb.x, bomb.y + gp.tileSize, gp.tileSize, gp.tileSize, null);
                g2.drawImage(leftExplosion[indexAniExplosion], bomb.x - gp.tileSize, bomb.y, gp.tileSize, gp.tileSize, null);
                g2.drawImage(rightExplosion[indexAniExplosion], bomb.x + gp.tileSize, bomb.y, gp.tileSize, gp.tileSize, null);

            } else {
                g2.drawImage(bombing[indexAniBomb], bomb.x, bomb.y, gp.tileSize, gp.tileSize, null);
            }
            if (x < gp.screenWidth / 2) {
                g2.drawImage(bombImg, bomb.x, bomb.y, gp.tileSize, gp.tileSize, null);
            } else if (bomb.x < gp.screenWidth / 2 && x >= gp.screenWidth / 2) {
                int bombScreenX = bomb.x - x + gp.screenWidth / 2;
                g2.drawImage(bombImg, bombScreenX, bomb.y, gp.tileSize, gp.tileSize, null);
            } else if (bomb.x >= gp.screenWidth / 2 && bomb.x <= gp.wordWidth - gp.screenWidth / 2 && x >= gp.screenWidth / 2 && x <= gp.wordWidth - gp.screenWidth / 2) {
                int bombScreenX = ScreenX + bomb.x - x;
                g2.drawImage(bombImg, bombScreenX, bomb.y, gp.tileSize, gp.tileSize, null);
            } else if (x > gp.wordWidth - gp.screenWidth / 2) {
                int bombScreenX = bomb.x - gp.wordWidth + gp.screenWidth;
                g2.drawImage(bombImg, bombScreenX, bomb.y, gp.tileSize, gp.tileSize, null);
            } else if (bomb.x > gp.wordWidth - gp.screenWidth / 2 && x <= gp.wordWidth - gp.screenWidth / 2) {
                int bombScreenX = bomb.x - x + gp.screenWidth / 2;
                g2.drawImage(bombImg, bombScreenX, bomb.y, gp.tileSize, gp.tileSize, null);
            }
        }

        if (x >= gp.screenWidth / 2 && x <= gp.wordWidth - gp.screenWidth / 2) {
            ScreenX = gp.screenWidth / 2;
            g2.drawImage(image, ScreenX, y, gp.tileSize, gp.tileSize, null);
        } else if (x < gp.screenWidth / 2) {
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        } else {
            ScreenX = x - gp.wordWidth + gp.screenWidth;
            g2.drawImage(image, ScreenX, y, gp.tileSize, gp.tileSize, null);
        }
    }
}
