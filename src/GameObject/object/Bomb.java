package GameObject.object;

import GameObject.Gameobject;
import GameObject.Item.BrickFlameItem;
import GameObject.Item.BrickSpeedItem;
import GameObject.Item.FlameItem;
import GameObject.Item.SpeedItem;
import GameObject.Tiles.TileManager;
import GameObject.mapObject.Brick;
import GameObject.mapObject.Grass;
import main.GamePanel;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bomb extends Gameobject {
    public int Flame;
    public boolean exploded;
    public int countToExplode = 0, intervalToExplode = 5;
    public BufferedImage[] bombing = new BufferedImage[3];
    public BufferedImage[] fontExplosion = new BufferedImage[3];
    public BufferedImage[] upExplosion = new BufferedImage[3];
    public BufferedImage[] downExplosion = new BufferedImage[3];
    public BufferedImage[] leftExplosion = new BufferedImage[3];
    public BufferedImage[] rightExplosion = new BufferedImage[3];
    public int frameBomb = 0, intervalBomb = 7, indexAniBomb = 0;
    public int frameExplosion = 0, intervalExplosion = 4, indexAniExplosion = 0;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
        Flame = 1;
        update();
        getBombImage();
        exploded = false;
    }

    public void update() {
        x = (x / 48) * 48;
        y = (y / 48) * 48;
    }

    @Override
    public Rectangle getBound() {
        return new Rectangle(x, y, width, height);
    }

    public void getBombImage() {
        try {
            //Bomb
            bombing[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/object/bomb.png")));
            bombing[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/object/bomb_1.png")));
            bombing[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/object/bomb_2.png")));

            fontExplosion[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/object/bomb_exploded.png")));
            fontExplosion[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/object/bomb_exploded1.png")));
            fontExplosion[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/object/bomb_exploded2.png")));

            upExplosion[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/object/explosion_vertical_top_last.png")));
            upExplosion[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/object/explosion_vertical_top_last1.png")));
            upExplosion[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/object/explosion_vertical_top_last2.png")));

            downExplosion[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/object/explosion_vertical_down_last.png")));
            downExplosion[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/object/explosion_vertical_down_last1.png")));
            downExplosion[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/object/explosion_vertical_down_last2.png")));

            leftExplosion[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/object/explosion_horizontal_left_last.png")));
            leftExplosion[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/object/explosion_horizontal_left_last1.png")));
            leftExplosion[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/object/explosion_horizontal_left_last2.png")));

            rightExplosion[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/object/explosion_horizontal_right_last.png")));
            rightExplosion[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/object/explosion_horizontal_right_last1.png")));
            rightExplosion[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/object/explosion_horizontal_right_last2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Explosion(GamePanel gp) {
        frameBomb++;
        if (frameBomb == intervalBomb) {
            frameBomb = 0;
            indexAniBomb++;
            if (indexAniBomb > 2) {
                indexAniBomb = 0;
                countToExplode++;
            }
            if (countToExplode >= intervalToExplode) {
                exploded = true;
            }
        }
        if (exploded) {
            frameExplosion++;
            if (frameExplosion == intervalExplosion) {
                frameExplosion = 0;
                indexAniExplosion++;
            }
            BrickExploded(gp);
            EnemyExploded(gp);
            FlameExploded(gp);
            SpeedItemExploded(gp);
        }
    }

    public void draw(Graphics2D g2, GamePanel gp, int x, int y) {
        int bombX = this.x;
        int bombY = this.y;
        if (x < gp.screenWidth / 2) {
            g2.drawImage(bombing[indexAniBomb], this.x, this.y, gp.tileSize, gp.tileSize, null);
        } else if (this.x < gp.screenWidth / 2 && x > gp.screenWidth / 2) {
            int bombScreenX = this.x - x + gp.screenWidth / 2;
            bombX = bombScreenX;
            g2.drawImage(bombing[indexAniBomb], bombScreenX, this.y, gp.tileSize, gp.tileSize, null);
        } else if (this.x >= gp.screenWidth / 2 && this.x <= gp.worldWidth - gp.screenWidth / 2 && x >= gp.screenWidth / 2 && x <= gp.worldWidth - gp.screenWidth / 2) {
            int bombScreenX = gp.screenWidth / 2 + this.x - x;
            bombX = bombScreenX;
            g2.drawImage(bombing[indexAniBomb], bombScreenX, this.y, gp.tileSize, gp.tileSize, null);
        } else if (x > gp.worldWidth - gp.screenWidth / 2) {
            int bombScreenX = this.x - gp.worldWidth + gp.screenWidth;
            bombX = bombScreenX;
            g2.drawImage(bombing[indexAniBomb], bombScreenX, this.y, gp.tileSize, gp.tileSize, null);
        } else if (this.x > gp.worldWidth - gp.screenWidth / 2 && x <= gp.worldWidth - gp.screenWidth / 2) {
            int bombScreenX = this.x - x + gp.screenWidth / 2;
            bombX = bombScreenX;
            g2.drawImage(bombing[indexAniBomb], bombScreenX, this.y, gp.tileSize, gp.tileSize, null);
        }
        if (this.exploded) {
            g2.drawImage(fontExplosion[indexAniExplosion], bombX, bombY, gp.tileSize, gp.tileSize, null);
            g2.drawImage(upExplosion[indexAniExplosion], bombX, bombY - gp.tileSize, gp.tileSize, gp.tileSize, null);
            g2.drawImage(downExplosion[indexAniExplosion], bombX, bombY + gp.tileSize, gp.tileSize, gp.tileSize, null);
            g2.drawImage(leftExplosion[indexAniExplosion], bombX - gp.tileSize, bombY, gp.tileSize, gp.tileSize, null);
            g2.drawImage(rightExplosion[indexAniExplosion], bombX + gp.tileSize, bombY, gp.tileSize, gp.tileSize, null);
        }
    }

    public void BrickExploded(GamePanel gp) {
        int statusx = x / gp.tileSize;
        int statusy = y / gp.tileSize;
        for (int i = 1; i <= Flame; i++) {
            if (TileManager.obj[statusy + i][statusx] instanceof Brick) {
                TileManager.obj[statusy + i][statusx] = new Grass(statusx * gp.tileSize, (statusy + i) * gp.tileSize);
            }
            if (TileManager.obj[statusy - i][statusx] instanceof Brick) {
                TileManager.obj[statusy - i][statusx] = new Grass(statusx * gp.tileSize, (statusy - i) * gp.tileSize);
            }
            if (TileManager.obj[statusy][statusx + i] instanceof Brick) {
                TileManager.obj[statusy][statusx + i] = new Grass((statusx + i) * gp.tileSize, statusy * gp.tileSize);
            }
            if (TileManager.obj[statusy][statusx - i] instanceof Brick) {
                TileManager.obj[statusy][statusx - i] = new Grass((statusx - i) * gp.tileSize, (statusy) * gp.tileSize);
            }
        }
    }

    public void EnemyExploded(GamePanel gp) {
        int statusx = x / gp.tileSize;
        int statusy = y / gp.tileSize;
        if (gp.enemy != null) {
            if (TileManager.obj[statusy][statusx - 1].getBound().intersects(gp.enemy.getBound(gp.enemy.x, gp.enemy.y))) {
                gp.enemy = null;
            } else if (TileManager.obj[statusy][statusx + 1].getBound().intersects(gp.enemy.getBound(gp.enemy.x, gp.enemy.y))) {
                gp.enemy = null;
            } else if (TileManager.obj[statusy - 1][statusx].getBound().intersects(gp.enemy.getBound(gp.enemy.x, gp.enemy.y))) {
                gp.enemy = null;
            } else if (TileManager.obj[statusy + 1][statusx - 1].getBound().intersects(gp.enemy.getBound(gp.enemy.x, gp.enemy.y))) {
                gp.enemy = null;
            }
        }
    }

    public void FlameExploded(GamePanel gp) {
        int statusx = x / gp.tileSize;
        int statusy = y / gp.tileSize;
        for (int i = 1; i <= Flame; i++) {
            if (TileManager.obj[statusy + i][statusx] instanceof BrickFlameItem) {
                TileManager.obj[statusy + i][statusx] = new FlameItem(statusx * gp.tileSize, (statusy + i) * gp.tileSize);
            }
            if (TileManager.obj[statusy - i][statusx] instanceof BrickFlameItem) {
                TileManager.obj[statusy - i][statusx] = new FlameItem(statusx * gp.tileSize, (statusy - i) * gp.tileSize);
            }
            if (TileManager.obj[statusy][statusx + i] instanceof BrickFlameItem) {
                TileManager.obj[statusy][statusx + i] = new FlameItem((statusx + i) * gp.tileSize, statusy * gp.tileSize);
            }
            if (TileManager.obj[statusy][statusx - i] instanceof BrickFlameItem) {
                TileManager.obj[statusy][statusx - i] = new FlameItem((statusx - i) * gp.tileSize, statusy * gp.tileSize);
            }
        }
    }
    public void SpeedItemExploded(GamePanel gp) {
        int statusx = x / gp.tileSize;
        int statusy = y / gp.tileSize;
        for (int i = 1; i <= Flame; i++) {
            if (TileManager.obj[statusy + i][statusx] instanceof BrickSpeedItem) {
                TileManager.obj[statusy + i][statusx] = new SpeedItem(statusx * gp.tileSize, (statusy + i) * gp.tileSize);
            }
            if (TileManager.obj[statusy - i][statusx] instanceof BrickSpeedItem) {
                TileManager.obj[statusy - i][statusx] = new SpeedItem(statusx * gp.tileSize, (statusy - i) * gp.tileSize);
            }
            if (TileManager.obj[statusy][statusx + i] instanceof BrickSpeedItem) {
                TileManager.obj[statusy][statusx + i] = new SpeedItem((statusx + i) * gp.tileSize, statusy * gp.tileSize);
            }
            if (TileManager.obj[statusy][statusx - i] instanceof BrickSpeedItem) {
                TileManager.obj[statusy][statusx - i] = new SpeedItem((statusx - i) * gp.tileSize, statusy * gp.tileSize);
            }
        }
    }
}