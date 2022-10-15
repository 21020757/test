package GameObject.entity;
import GameObject.Gameobject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;

public abstract class Entity extends Gameobject{
    public int x, y;
    public int contant;
    public int speed;

    //load image
    public BufferedImage up, up1, up2, down, down1, down2, left, left1, left2, right, right1, right2, dead1, dead2, dead3;
    public String direction;

    //animation
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public abstract void update();
    public abstract void draw(Graphics2D g2);
}
