package GameObject.entity;

import GameObject.Tiles.TileManager;
import GameObject.mapObject.Brick;
import GameObject.mapObject.Wall;
import main.GamePanel;
import main.KeyHandler;
import GameObject.object.Bomb;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bomberman extends Entity {
    public static KeyHandler keyH;
    public static final int intervalImageChange = 9;
    public boolean notMoving;
    public String preDirection;
    public int ScreenX;

    //About bomb
    public int BombAmount;
    int count;
    public Bomb bomb[];

    public Bomberman(GamePanel gp, KeyHandler keyH) {
        super(gp);
        Bomberman.keyH = keyH;
        preDirection = "down";
        setDefaultValues();
        getPlayerImage();
    }


    public void setDefaultValues() {
        x = gp.tileSize;
        y = gp.tileSize;
        speed = 4;
        direction = "down";
        width = 32;
        heigth = 48;
        BombAmount = 1;
        bomb = new Bomb[BombAmount];
    }

    public void getPlayerImage() {
        try {
            //Move
            up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_up.png")));
            down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_down.png")));
            left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_left.png")));
            right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_right.png")));
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_right_2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed
                || keyH.leftPressed || keyH.rightPressed || keyH.spacePressed) {
            notMoving = false;
            if (keyH.upPressed) {
                if (!collisionUp()) {
                    y -= speed;
                }
                direction = "up";
                preDirection = "up";
            }  if (keyH.downPressed) {
                if (!collisionDown()) {
                    y += speed;
                }
                direction = "down";
                preDirection = "down";
            }  if (keyH.leftPressed) {
                if (!collisionLeft()) {
                    x -= speed;
                }
                direction = "left";
                preDirection = "left";
            }  if (keyH.rightPressed) {
                if (!collisionRight()) {
                    x += speed;
                }
                direction = "right";
                preDirection = "right";
            }  if (keyH.spacePressed) {
                if (BombAmount > 0) {
                    BombAmount--;
                    bomb[BombAmount] = new Bomb(x,y);
                    gp.playSE(2);
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
        if (BombAmount < 1) {
            bomb[BombAmount].Explosion();
            if (bomb[BombAmount].indexAniExplosion == 3) {
                bomb[BombAmount].indexAniExplosion =0;
                gp.playSE(3);
                bomb[BombAmount] = null;
                BombAmount ++;
            }
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
        if (BombAmount < 1){
            bomb[BombAmount].draw(g2,gp,x,y);
        }
        if (x >= gp.screenWidth / 2 && x <= gp.worldWidth - gp.screenWidth / 2) {
            ScreenX = gp.screenWidth / 2;
            g2.drawImage(image, ScreenX, y, gp.tileSize, gp.tileSize, null);
        } else if (x < gp.screenWidth / 2) {
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        } else {
            ScreenX = x - gp.worldWidth + gp.screenWidth;
            g2.drawImage(image, ScreenX, y, gp.tileSize, gp.tileSize, null);
        }
    }


}
