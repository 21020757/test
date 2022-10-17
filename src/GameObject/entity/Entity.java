package GameObject.entity;

import GameObject.Gameobject;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;

public class Entity extends Gameobject {
    GamePanel gp;
    public int x, y;
    public int speed;

    //Load image
    public BufferedImage up, up1, up2, down, down1, down2, left, left1, left2, right, right1, right2, dead1, dead2, dead3;
    public String direction;

    //Animation
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void update() {
    }


    public void draw(Graphics2D g2) {
    }


    @Override
    public Rectangle getBound() {
        return null;
    }
}
