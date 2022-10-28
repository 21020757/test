package GameObject.entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Oneal extends Entity {
    public int intervalCount = 0;
    public int speedUpInterval = 90;
    public int bomber_x;
    public int bomber_y;
    public int oneal_x;
    public int oneal_y;
    public int smartX;
    public int smartY;

    public Oneal(GamePanel gp) {
        super(gp);
        direction = "down";
        preDirection = "";
        speed = 2;
        width = gp.tileSize;
        height = gp.tileSize;
        getImage();
        isDead = false;
    }

    public void setEnemies(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void getImage() {
        try {
            left[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/oneal_left1.png")));
            left[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/oneal_left2.png")));
            left[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/oneal_left3.png")));

            right[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/oneal_right1.png")));
            right[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/oneal_right2.png")));
            right[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/oneal_right3.png")));

            dead[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/oneal_dead.png")));
            dead[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/enemy_dead_1.png")));
            dead[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/enemy_dead_2.png")));
            dead[3] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/enemy_dead_3.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction() {
        bomber_x = gp.bomberman.x;
        bomber_y = gp.bomberman.y;
        oneal_x = x;
        oneal_y = y;
        smartX = oneal_x - bomber_x;
        smartY = oneal_y - bomber_y;
        actionLockCounter++;
        if (actionLockCounter == 30) {
            randomSpeed();
            if (smartX * smartX - smartY * smartY >= 0) {
                if (smartX < 0) {
                    direction = "right";
                    if (smartY < 0) {
                        direction = "down";
                    }
                    if (smartY > 0) {
                        direction = "up";
                    }
                }
                if (smartX > 0) {
                    direction = "left";
                    if (smartY < 0) {
                        direction = "down";
                    }
                    if (smartY > 0) {
                        direction = "up";
                    }
                }
            }
            if (smartX * smartX - smartY * smartY <= 0) {
                if (smartY > 0) {
                    direction = "up";
                    preDirection = "up";
                    if (smartX > 0) {
                        direction = "left";
                    }
                    if (smartX < 0) {
                        direction = "right";
                    }
                }
                if (smartY < 0) {
                    direction = "down";
                    preDirection = "down";
                    if (smartX > 0) {
                        direction = "left";
                    }
                    if (smartX < 0) {
                        direction = "right";
                    }
                }
            }
            actionLockCounter = 0;
        }
    }

    public void randomSpeed() {
        intervalCount++;
        if (intervalCount == speedUpInterval / 6) {
            speed = 2;
        }
        if (intervalCount == speedUpInterval) {
            speed = 3;
            intervalCount = 0;
        }
    }

    @Override
    public void update() {
        setAction();
        super.updatePos();
    }


    public void draw(Graphics2D g2) {
        if (gp.bomberman.x <= gp.screenWidth / 2) {
            if (direction.equals("left")) {
                g2.drawImage(left[spriteNum], x, y, width, height, null);
            } else if (direction.equals("right")) {
                g2.drawImage(right[spriteNum], x, y, width, height, null);
            } else {
                if (preDirection.equals("left")) {
                    g2.drawImage(left[spriteNum], x, y, width, height, null);
                } else {
                    g2.drawImage(right[spriteNum], x, y, width, height, null);
                }
            }
        } else if (gp.screenWidth / 2 <= gp.bomberman.x && gp.bomberman.x < gp.worldWidth - gp.screenWidth / 2 && x >= gp.screenWidth / 2) {
            int ScreenX = x + gp.screenWidth / 2 - gp.bomberman.x;
            if (direction.equals("left")) {
                g2.drawImage(left[spriteNum], ScreenX, y, width, height, null);
            } else if (direction.equals("right")) {
                g2.drawImage(right[spriteNum], ScreenX, y, width, height, null);
            } else {
                if (preDirection.equals("left")) {
                    g2.drawImage(left[spriteNum], ScreenX, y, width, height, null);
                } else {
                    g2.drawImage(right[spriteNum], ScreenX, y, width, height, null);
                }
            }
        } else if (gp.screenWidth / 2 <= gp.bomberman.x && x < gp.screenWidth / 2) {
            int ScreenX = gp.screenWidth / 2 + this.x - gp.bomberman.x;
            if (direction.equals("left")) {
                g2.drawImage(left[spriteNum], ScreenX, y, width, height, null);
            } else if (direction.equals("right")) {
                g2.drawImage(right[spriteNum], ScreenX, y, width, height, null);
            } else {
                if (preDirection.equals("left")) {
                    g2.drawImage(left[spriteNum], ScreenX, y, width, height, null);
                } else {
                    g2.drawImage(right[spriteNum], ScreenX, y, width, height, null);
                }
            }
        } else if (gp.bomberman.x >= gp.worldWidth - gp.screenWidth / 2) {
            int ScreenX = x - gp.worldWidth + gp.screenWidth;
            if (direction.equals("left")) {
                g2.drawImage(left[spriteNum], ScreenX, y, width, height, null);
            } else if (direction.equals("right")) {
                g2.drawImage(right[spriteNum], ScreenX, y, width, height, null);
            } else {
                if (preDirection.equals("left")) {
                    g2.drawImage(left[spriteNum], ScreenX, y, width, height, null);
                } else {
                    g2.drawImage(right[spriteNum], ScreenX, y, width, height, null);
                }
            }
        }
    }
}
