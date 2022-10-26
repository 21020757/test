package GameObject.entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Oneal extends Entity {
    public int actionLockCounter = 0;
    BufferedImage[] onealLeft = new BufferedImage[3];
    BufferedImage[] onealRight = new BufferedImage[3];

    public Oneal(GamePanel gp) {
        super(gp);
        direction = "down";
        preDirection = "";
        speed = 2;
        width = gp.tileSize;
        height = gp.tileSize;
        getImage();
    }

    public void setEnemies(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void getImage() {
        try {
            //Balloon
            onealLeft[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/oneal_left1.png")));
            onealLeft[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/oneal_left2.png")));
            onealLeft[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/oneal_left3.png")));

            onealRight[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/oneal_right1.png")));
            onealRight[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/oneal_right2.png")));
            onealRight[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/oneal_right3.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            if (gp.bomberman.x < x && gp.bomberman.y < y) {
                Random random = new Random();
                int i = random.nextInt(2) + 1;
                if (i == 1) {
                    direction = "up";
                    preDirection = "up";
                }
                if (i == 2) {
                    direction = "left";
                }
            }
            if (gp.bomberman.x <= x && gp.bomberman.y >= y) {
                Random random = new Random();
                int i = random.nextInt(2) + 1;
                if (i == 1) {
                    direction = "down";
                    preDirection = "down";
                }
                if (i == 2) {
                    direction = "left";
                }
            }
            if (gp.bomberman.x > x && gp.bomberman.y > y) {
                Random random = new Random();
                int i = random.nextInt(2) + 1;
                if (i == 1) {
                    direction = "down";
                    preDirection = "down";
                }
                if (i == 2) {
                    direction = "right";
                }
            }
            if (gp.bomberman.x >= x && gp.bomberman.y <= y) {
                Random random = new Random();
                int i = random.nextInt(2) + 1;
                if (i == 1) {
                    direction = "up";
                    preDirection = "up";
                }
                if (i == 2) {
                    direction = "right";
                }
            }
            actionLockCounter = 0;
        }
    }

    @Override
    public void update() {
        setAction();
        Random random = new Random();
        speed = random.nextInt(4) + 1;
        switch (direction) {
            case "up" -> {
                if (!collisionUp()) {
                    y -= speed;
                }
            }
            case "down" -> {
                if (!collisionDown()) {
                    y += speed;
                }
            }
            case "left" -> {
                if (!collisionLeft()) {
                    x -= speed;
                }
            }
            case "right" -> {
                if (!collisionRight()) {
                    x += speed;
                }
            }
        }
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }


    public void draw(Graphics2D g2){
        if (gp.bomberman.x <= gp.screenWidth / 2) {
            if (direction.equals("left")) {
                g2.drawImage(onealLeft[spriteNum], x, y, gp.tileSize, gp.tileSize, null);
            } else if (direction.equals("right")) {
                g2.drawImage(onealRight[spriteNum], x, y, gp.tileSize, gp.tileSize, null);
            } else {
                if (preDirection.equals("left")) {
                    g2.drawImage(onealLeft[spriteNum], x, y, gp.tileSize, gp.tileSize, null);
                } else {
                    g2.drawImage(onealRight[spriteNum], x, y, gp.tileSize, gp.tileSize, null);
                }
            }
        } else if (gp.screenWidth / 2 <= gp.bomberman.x && gp.bomberman.x < gp.worldWidth - gp.screenWidth / 2 && x >= gp.screenWidth / 2) {
            int ScreenX = x + gp.screenWidth / 2 - gp.bomberman.x;
            if (direction.equals("left")) {
                g2.drawImage(onealLeft[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
            } else if (direction.equals("right")) {
                g2.drawImage(onealRight[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
            } else {
                if (preDirection.equals("left")) {
                    g2.drawImage(onealLeft[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
                } else {
                    g2.drawImage(onealRight[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
                }
            }
        } else if (gp.screenWidth / 2 <= gp.bomberman.x && x < gp.screenWidth / 2) {
            int ScreenX = gp.screenWidth / 2 + this.x - gp.bomberman.x;
            if (direction.equals("left")) {
                g2.drawImage(onealLeft[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
            } else if (direction.equals("right")) {
                g2.drawImage(onealRight[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
            } else {
                if (preDirection.equals("left")) {
                    g2.drawImage(onealLeft[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
                } else {
                    g2.drawImage(onealRight[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
                }
            }
        } else if (gp.bomberman.x > gp.worldWidth - gp.screenWidth / 2) {
            int ScreenX = x - gp.worldWidth + gp.screenWidth;
            if (direction.equals("left")) {
                g2.drawImage(onealLeft[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
            } else if (direction.equals("right")) {
                g2.drawImage(onealRight[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
            } else {
                if (preDirection.equals("left")) {
                    g2.drawImage(onealLeft[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
                } else {
                    g2.drawImage(onealRight[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }
}
