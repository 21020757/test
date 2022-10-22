package GameObject.entity;

import GameObject.Gameobject;
import GameObject.Item.BrickBombItem;
import GameObject.Item.BrickFlameItem;
import GameObject.Item.BrickSpeedItem;
import GameObject.Tiles.TileManager;
import GameObject.mapObject.Brick;
import GameObject.mapObject.Wall;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;

public class Entity extends Gameobject {
    GamePanel gp;
    public int speed;

    //Load image
    public BufferedImage up, up1, up2, down, down1, down2, left, left1, left2, right, right1, right2, dead1, dead2, dead3;
    public String direction;
    public String preDirection;

    //Animation
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {
    }

    public void update() {
    }

    public void draw(Graphics2D g2) {
    }

    @Override
    public Rectangle getBound() {
        return null;
    }

    public Rectangle getBound(int x, int y) {
        return new Rectangle(x, y + height, width, height);
    }
    public boolean collisionUp() {
        int a = x % gp.tileSize;
        int b = y % gp.tileSize;
        int EntityX = x / gp.tileSize;
        int EntityY = y / gp.tileSize;
        if (this.getBound(x, y - speed).intersects(TileManager.obj[EntityY - 1][EntityX].getBound()) && (TileManager.obj[EntityY - 1][EntityX] instanceof Wall || TileManager.obj[EntityY - 1][EntityX] instanceof Brick || TileManager.obj[EntityY - 1][EntityX] instanceof BrickFlameItem || TileManager.obj[EntityY - 1][EntityX] instanceof BrickSpeedItem || TileManager.obj[EntityY - 1][EntityX] instanceof BrickBombItem)) {
            return true;
        } else if (this.getBound(x, y - speed).intersects(TileManager.obj[EntityY - 1][EntityX].getBound()) && (TileManager.obj[EntityY - 1][EntityX] instanceof Wall || TileManager.obj[EntityY - 1][EntityX] instanceof Brick || TileManager.obj[EntityY - 1][EntityX] instanceof BrickFlameItem || TileManager.obj[EntityY - 1][EntityX] instanceof BrickBombItem || TileManager.obj[EntityY - 1][EntityX] instanceof BrickSpeedItem ) && a > 16) {
            return true;
        } else if (this.getBound(x, y - speed).intersects(TileManager.obj[EntityY - 1][EntityX + 1].getBound()) && (TileManager.obj[EntityY - 1][EntityX + 1] instanceof Wall || TileManager.obj[EntityY - 1][EntityX + 1] instanceof Brick) && a > 16) {
            return true;
        } else {
            return false;
        }

    }

    public boolean collisionDown() {
        int a = x % gp.tileSize;
        int b = y % gp.tileSize;
        int EntityX = x / gp.tileSize;
        int EntityY = y / gp.tileSize;
        if (this.getBound(x, y + speed).intersects(TileManager.obj[EntityY + 1][EntityX].getBound()) && (TileManager.obj[EntityY + 1][EntityX] instanceof Wall || TileManager.obj[EntityY + 1][EntityX] instanceof Brick || TileManager.obj[EntityY + 1][EntityX] instanceof BrickFlameItem || TileManager.obj[EntityY + 1][EntityX] instanceof BrickSpeedItem || TileManager.obj[EntityY + 1][EntityX] instanceof BrickBombItem)) {
            return true;
        } else if (this.getBound(x, y + speed).intersects(TileManager.obj[EntityY + 1][EntityX].getBound()) && (TileManager.obj[EntityY + 1][EntityX] instanceof Wall || TileManager.obj[EntityY + 1][EntityX] instanceof Brick || TileManager.obj[EntityY + 1][EntityX] instanceof BrickBombItem || TileManager.obj[EntityY + 1][EntityX] instanceof BrickSpeedItem || TileManager.obj[EntityY + 1][EntityX] instanceof BrickFlameItem ) && a > 16) {
            return true;
        } else if (this.getBound(x, y + speed).intersects(TileManager.obj[EntityY + 1][EntityX + 1].getBound()) && (TileManager.obj[EntityY + 1][EntityX + 1] instanceof Wall || TileManager.obj[EntityY + 1][EntityX + 1] instanceof Brick || TileManager.obj[EntityY + 1][EntityX + 1] instanceof BrickSpeedItem || TileManager.obj[EntityY + 1][EntityX + 1] instanceof BrickFlameItem || TileManager.obj[EntityY + 1][EntityX + 1] instanceof BrickBombItem) && a > 16) {
            return true;
        } else {
            return false;
        }
    }

    public boolean collisionRight() {
        int EntityX = x / gp.tileSize;
        int EntityY = y / gp.tileSize;
        if (this.getBound(x + speed, y).intersects(TileManager.obj[EntityY][EntityX + 1].getBound()) && (TileManager.obj[EntityY][EntityX + 1] instanceof Wall || TileManager.obj[EntityY][EntityX + 1] instanceof Brick || TileManager.obj[EntityY][EntityX + 1] instanceof BrickSpeedItem ||TileManager.obj[EntityY][EntityX + 1] instanceof BrickFlameItem || TileManager.obj[EntityY][EntityX + 1] instanceof BrickBombItem)) {
            return true;
        } else if (this.getBound(x + speed, y).intersects(TileManager.obj[EntityY + 1][EntityX + 1].getBound()) && (TileManager.obj[EntityY + 1][EntityX + 1] instanceof Wall || TileManager.obj[EntityY + 1][EntityX + 1] instanceof Brick || TileManager.obj[EntityY + 1][EntityX + 1] instanceof BrickBombItem || TileManager.obj[EntityY + 1][EntityX + 1] instanceof BrickSpeedItem || TileManager.obj[EntityY + 1][EntityX + 1] instanceof BrickFlameItem)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean collisionLeft() {
        int EntityX = x / gp.tileSize;
        int EntityY = y / gp.tileSize;
        if (this.getBound(x - speed, y).intersects(TileManager.obj[EntityY][EntityX - 1].getBound()) && (TileManager.obj[EntityY][EntityX - 1] instanceof Wall || TileManager.obj[EntityY][EntityX - 1] instanceof Brick || TileManager.obj[EntityY][EntityX - 1] instanceof BrickSpeedItem || TileManager.obj[EntityY][EntityX - 1] instanceof BrickFlameItem || TileManager.obj[EntityY][EntityX - 1] instanceof BrickBombItem)) {
            return true;
        } else if (this.getBound(x - speed, y).intersects(TileManager.obj[EntityY + 1][EntityX - 1].getBound()) && (TileManager.obj[EntityY + 1][EntityX - 1] instanceof Wall || TileManager.obj[EntityY + 1][EntityX - 1] instanceof Brick || TileManager.obj[EntityY + 1][EntityX - 1] instanceof BrickBombItem || TileManager.obj[EntityY + 1][EntityX - 1] instanceof BrickFlameItem || TileManager.obj[EntityY + 1][EntityX - 1] instanceof BrickSpeedItem)) {
            return true;
        } else {
            return false;
        }
    }
}
