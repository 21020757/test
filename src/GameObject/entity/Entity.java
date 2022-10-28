package GameObject.entity;

import GameObject.Gameobject;
import GameObject.Item.BrickBombItem;
import GameObject.Item.BrickFlameItem;
import GameObject.Item.BrickPortalItem;
import GameObject.Item.BrickSpeedItem;
import GameObject.Tiles.TileManager;
import GameObject.mapObject.Brick;
import GameObject.mapObject.Wall;
import GameObject.object.Bomb;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;

public class Entity extends Gameobject {
    public GamePanel gp;
    public int actionLockCounter = 0;
    public int speed;
    public boolean isDead;
    public BufferedImage[] up = new BufferedImage[3];
    public BufferedImage[] down = new BufferedImage[3];
    public BufferedImage[] left = new BufferedImage[3];
    public BufferedImage[] right = new BufferedImage[3];
    public BufferedImage[] dead = new BufferedImage[4];
    public String direction;
    public String preDirection;
    public int spriteCounter = 0;
    public int spriteNum = 0;
    public int deadCount = 0, deadNum = 0;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {
    }

    public void update() {
    }

    public void draw(Graphics2D g2) {
    }

    public void drawDead(Graphics2D g2) {
        deadCount++;
        if (deadCount > 30) {
            if (deadNum == 0) {
                deadNum = 1;
            } else if (deadNum == 1) {
                deadNum = 2;
            } else if (deadNum == 2) {
                deadNum = 3;
            } else {
                deadNum = 0;
            }
            deadCount = 0;
        }
        if (gp.bomberman.x <= gp.screenWidth / 2) {
            g2.drawImage(dead[deadNum], x, y, width, height, null);
        } else if (gp.screenWidth / 2 <= gp.bomberman.x && gp.bomberman.x < gp.worldWidth - gp.screenWidth / 2 && x >= gp.screenWidth / 2) {
            int ScreenX = x + gp.screenWidth / 2 - gp.bomberman.x;
            g2.drawImage(dead[deadNum], ScreenX, y, width, height, null);
        } else if (gp.screenWidth / 2 <= gp.bomberman.x && x < gp.screenWidth / 2) {
            int ScreenX = gp.screenWidth / 2 + this.x - gp.bomberman.x;
            g2.drawImage(dead[deadNum], ScreenX, y, width, height, null);
        } else if (gp.bomberman.x >= gp.worldWidth - gp.screenWidth / 2) {
            int ScreenX = x - gp.worldWidth + gp.screenWidth;
            g2.drawImage(dead[deadNum], ScreenX, y, width, height, null);
        }
    }

    public void updatePos() {
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
            if (spriteNum == 0) {
                spriteNum = 1;
            } else if (spriteNum == 1) {
                spriteNum = 2;
            } else {
                spriteNum = 0;
            }
            spriteCounter = 0;
        }
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
        if (this.getBound(x, y - 1).intersects(TileManager.obj[EntityY - 1][EntityX].getBound()) && (TileManager.obj[EntityY - 1][EntityX] instanceof Wall || TileManager.obj[EntityY - 1][EntityX] instanceof Brick || TileManager.obj[EntityY - 1][EntityX] instanceof BrickFlameItem || TileManager.obj[EntityY - 1][EntityX] instanceof BrickSpeedItem || TileManager.obj[EntityY - 1][EntityX] instanceof BrickBombItem || TileManager.obj[EntityY - 1][EntityX] instanceof Bomb || TileManager.obj[EntityY - 1][EntityX] instanceof BrickPortalItem)) {
            return true;
        } else if (this.getBound(x, y - 1).intersects(TileManager.obj[EntityY - 1][EntityX].getBound()) && (TileManager.obj[EntityY - 1][EntityX] instanceof Wall || TileManager.obj[EntityY - 1][EntityX] instanceof Brick || TileManager.obj[EntityY - 1][EntityX] instanceof BrickFlameItem || TileManager.obj[EntityY - 1][EntityX] instanceof BrickBombItem || TileManager.obj[EntityY - 1][EntityX] instanceof BrickSpeedItem || TileManager.obj[EntityY - 1][EntityX] instanceof Bomb || TileManager.obj[EntityY - 1][EntityX] instanceof BrickPortalItem) && a > 16) {
            return true;
        } else
            return this.getBound(x, y - 1).intersects(TileManager.obj[EntityY - 1][EntityX + 1].getBound()) && (TileManager.obj[EntityY - 1][EntityX + 1] instanceof Wall || TileManager.obj[EntityY - 1][EntityX + 1] instanceof Brick || TileManager.obj[EntityY - 1][EntityX + 1] instanceof Bomb || TileManager.obj[EntityY - 1][EntityX + 1] instanceof BrickFlameItem || TileManager.obj[EntityY - 1][EntityX + 1] instanceof BrickBombItem || TileManager.obj[EntityY - 1][EntityX + 1] instanceof BrickSpeedItem || TileManager.obj[EntityY - 1][EntityX + 1] instanceof BrickPortalItem) && a > 16;

    }

    public boolean collisionDown() {
        int a = x % gp.tileSize;
        int b = y % gp.tileSize;
        int EntityX = x / gp.tileSize;
        int EntityY = y / gp.tileSize;
        if (this.getBound(x, y + 1).intersects(TileManager.obj[EntityY + 1][EntityX].getBound()) && (TileManager.obj[EntityY + 1][EntityX] instanceof Wall || TileManager.obj[EntityY + 1][EntityX] instanceof Brick || TileManager.obj[EntityY + 1][EntityX] instanceof BrickFlameItem || TileManager.obj[EntityY + 1][EntityX] instanceof BrickSpeedItem || TileManager.obj[EntityY + 1][EntityX] instanceof BrickBombItem || TileManager.obj[EntityY + 1][EntityX] instanceof Bomb || TileManager.obj[EntityY + 1][EntityX] instanceof BrickPortalItem)) {
            return true;
        } else if (this.getBound(x, y + 1).intersects(TileManager.obj[EntityY + 1][EntityX].getBound()) && (TileManager.obj[EntityY + 1][EntityX] instanceof Wall || TileManager.obj[EntityY + 1][EntityX] instanceof Brick || TileManager.obj[EntityY + 1][EntityX] instanceof BrickBombItem || TileManager.obj[EntityY + 1][EntityX] instanceof BrickSpeedItem || TileManager.obj[EntityY + 1][EntityX] instanceof BrickFlameItem || TileManager.obj[EntityY + 1][EntityX] instanceof BrickPortalItem || TileManager.obj[EntityY + 1][EntityX] instanceof Bomb) && a > 16) {
            return true;
        } else
            return this.getBound(x, y + 1).intersects(TileManager.obj[EntityY + 1][EntityX + 1].getBound()) && (TileManager.obj[EntityY + 1][EntityX + 1] instanceof Wall || TileManager.obj[EntityY + 1][EntityX + 1] instanceof Brick || TileManager.obj[EntityY + 1][EntityX + 1] instanceof BrickSpeedItem || TileManager.obj[EntityY + 1][EntityX + 1] instanceof BrickFlameItem || TileManager.obj[EntityY + 1][EntityX + 1] instanceof BrickBombItem || TileManager.obj[EntityY + 1][EntityX + 1] instanceof BrickPortalItem || TileManager.obj[EntityY + 1][EntityX + 1] instanceof Bomb) && a > 16;
    }

    public boolean collisionRight() {
        int EntityX = x / gp.tileSize;
        int EntityY = y / gp.tileSize;
        if (this.getBound(x + 1, y).intersects(TileManager.obj[EntityY][EntityX + 1].getBound()) && (TileManager.obj[EntityY][EntityX + 1] instanceof Wall || TileManager.obj[EntityY][EntityX + 1] instanceof Brick || TileManager.obj[EntityY][EntityX + 1] instanceof BrickSpeedItem || TileManager.obj[EntityY][EntityX + 1] instanceof BrickFlameItem || TileManager.obj[EntityY][EntityX + 1] instanceof BrickBombItem || TileManager.obj[EntityY][EntityX + 1] instanceof Bomb || TileManager.obj[EntityY][EntityX + 1] instanceof BrickPortalItem)) {
            return true;
        } else
            return this.getBound(x + 1, y).intersects(TileManager.obj[EntityY + 1][EntityX + 1].getBound()) && (TileManager.obj[EntityY + 1][EntityX + 1] instanceof Wall || TileManager.obj[EntityY + 1][EntityX + 1] instanceof Brick || TileManager.obj[EntityY + 1][EntityX + 1] instanceof BrickBombItem || TileManager.obj[EntityY + 1][EntityX + 1] instanceof BrickSpeedItem || TileManager.obj[EntityY + 1][EntityX + 1] instanceof BrickFlameItem || TileManager.obj[EntityY + 1][EntityX + 1] instanceof Bomb || TileManager.obj[EntityY + 1][EntityX + 1] instanceof BrickPortalItem);
    }

    public boolean collisionLeft() {
        int EntityX = x / gp.tileSize;
        int EntityY = y / gp.tileSize;
        if (this.getBound(x - 1, y).intersects(TileManager.obj[EntityY][EntityX - 1].getBound()) && (TileManager.obj[EntityY][EntityX - 1] instanceof Wall || TileManager.obj[EntityY][EntityX - 1] instanceof Brick || TileManager.obj[EntityY][EntityX - 1] instanceof BrickSpeedItem || TileManager.obj[EntityY][EntityX - 1] instanceof BrickFlameItem || TileManager.obj[EntityY][EntityX - 1] instanceof BrickBombItem || TileManager.obj[EntityY][EntityX - 1] instanceof Bomb || TileManager.obj[EntityY][EntityX - 1] instanceof BrickPortalItem)) {
            return true;
        } else
            return this.getBound(x - 1, y).intersects(TileManager.obj[EntityY + 1][EntityX - 1].getBound()) && (TileManager.obj[EntityY + 1][EntityX - 1] instanceof Wall || TileManager.obj[EntityY + 1][EntityX - 1] instanceof Brick || TileManager.obj[EntityY + 1][EntityX - 1] instanceof BrickBombItem || TileManager.obj[EntityY + 1][EntityX - 1] instanceof BrickFlameItem || TileManager.obj[EntityY + 1][EntityX - 1] instanceof BrickSpeedItem || TileManager.obj[EntityY + 1][EntityX - 1] instanceof BrickPortalItem || TileManager.obj[EntityY + 1][EntityX - 1] instanceof Bomb);
    }

}
