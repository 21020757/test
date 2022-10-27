package GameObject.entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import static GameObject.entity.Bomberman.intervalImageChange;

public class Enemies extends Entity {
    public int actionLockCounter = 0;
    BufferedImage[] balloonLeft = new BufferedImage[3];
    BufferedImage[] balloonRight = new BufferedImage[3];

    public Enemies(GamePanel gp) {
        super(gp);
        direction = "down";
        preDirection = "";
        speed = 2;
        width = gp.tileSize;
        height = gp.tileSize;
        getImage();
        status = true;
    }

    public void setEnemies(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void getImage() {
        try {
            //Balloon
            balloonLeft[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/balloom_left1.png")));
            balloonLeft[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/balloom_left2.png")));
            balloonLeft[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/balloom_left3.png")));

            balloonRight[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/balloom_right1.png")));
            balloonRight[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/balloom_right2.png")));
            balloonRight[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/balloom_right3.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(40) + 1;
            if (i <= 10) {
                direction = "up";
                preDirection = "up";
            }
            if (i > 10 && i <= 20) {
                direction = "down";
                preDirection = "down";
            }
            if (i > 20 && i <= 30) {
                direction = "left";
            }
            if (i > 30) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    @Override
    public void update() {
        setAction();
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

    @Override
    public void draw(Graphics2D g2) {
        if (gp.bomberman.x <= gp.screenWidth / 2) {
            if (direction.equals("left")) {
                g2.drawImage(balloonLeft[spriteNum], x, y, gp.tileSize, gp.tileSize, null);
            } else if (direction.equals("right")) {
                g2.drawImage(balloonRight[spriteNum], x, y, gp.tileSize, gp.tileSize, null);
            } else {
                if (preDirection.equals("left")) {
                    g2.drawImage(balloonLeft[spriteNum], x, y, gp.tileSize, gp.tileSize, null);
                } else {
                    g2.drawImage(balloonRight[spriteNum], x, y, gp.tileSize, gp.tileSize, null);
                }
            }
        } else if (gp.screenWidth / 2 <= gp.bomberman.x && gp.bomberman.x < gp.worldWidth - gp.screenWidth / 2 && x >= gp.screenWidth / 2) {
            int ScreenX = x + gp.screenWidth / 2 - gp.bomberman.x;
            if (direction.equals("left")) {
                g2.drawImage(balloonLeft[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
            } else if (direction.equals("right")) {
                g2.drawImage(balloonRight[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
            } else {
                if (preDirection.equals("left")) {
                    g2.drawImage(balloonLeft[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
                } else {
                    g2.drawImage(balloonRight[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
                }
            }
        } else if (gp.screenWidth / 2 <= gp.bomberman.x && x < gp.screenWidth / 2) {
            int ScreenX = gp.screenWidth / 2 + this.x - gp.bomberman.x;
            if (direction.equals("left")) {
                g2.drawImage(balloonLeft[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
            } else if (direction.equals("right")) {
                g2.drawImage(balloonRight[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
            } else {
                if (preDirection.equals("left")) {
                    g2.drawImage(balloonLeft[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
                } else {
                    g2.drawImage(balloonRight[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
                }
            }
        } else if (gp.bomberman.x >= gp.worldWidth - gp.screenWidth / 2) {
            int ScreenX = x - gp.worldWidth + gp.screenWidth;
            if (direction.equals("left")) {
                g2.drawImage(balloonLeft[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
            } else if (direction.equals("right")) {
                g2.drawImage(balloonRight[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
            } else {
                if (preDirection.equals("left")) {
                    g2.drawImage(balloonLeft[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
                } else {
                    g2.drawImage(balloonRight[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }
}


