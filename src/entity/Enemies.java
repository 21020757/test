package entity;

import GameObject.entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Enemies extends Entity {
    public Enemies(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 2;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {

    }

    @Override
    public Rectangle getBound() {
        return null;
    }
}
