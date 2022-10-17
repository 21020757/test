package GameObject.entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static GameObject.entity.Bomberman.intervalImageChange;

public class Enemies extends Entity {

    BufferedImage[] balloonLeft = new BufferedImage[3];
    BufferedImage[] balloonRight = new BufferedImage[3];

    public Enemies(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 2;
        getImage();
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

    @Override
    public void update() {
        spriteCounter++;
        if (spriteCounter > intervalImageChange) {
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
        g2.drawImage(balloonLeft[spriteNum], 96, 96, gp.tileSize, gp.tileSize, null);
    }
}

