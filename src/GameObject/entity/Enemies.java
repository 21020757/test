package GameObject.entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;


public class Enemies extends Entity {


    public Enemies(GamePanel gp) {
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
            //Balloon
            left[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/balloom_left1.png")));
            left[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/balloom_left2.png")));
            left[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/balloom_left3.png")));

            right[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/balloom_right1.png")));
            right[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/balloom_right2.png")));
            right[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/balloom_right3.png")));

            dead[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/balloom_dead.png")));
            dead[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/enemy_dead_1.png")));
            dead[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/enemy_dead_2.png")));
            dead[3] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/enemy/enemy_dead_3.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 80) {
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
        super.updatePos();
    }

    @Override
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


