package GameObject.object;

import GameObject.Gameobject;
import GameObject.Item.*;
import GameObject.Tiles.TileManager;
import GameObject.mapObject.Brick;
import GameObject.mapObject.Grass;
import GameObject.mapObject.Wall;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bomb extends Gameobject {
    public int FlameUp, FlameDown, FlameLeft, FlameRight;
    public int UpFlame, DownFlame, LeftFlame, RightFlame;
    public boolean exploded;
    public int countToExplode = 0, intervalToExplode = 5;
    public BufferedImage grass,portal;
    public BufferedImage[] bombing = new BufferedImage[3];
    public BufferedImage[] fontExplosion = new BufferedImage[3];
    public BufferedImage[] upExplosion = new BufferedImage[3];
    public BufferedImage[] downExplosion = new BufferedImage[3];
    public BufferedImage[] leftExplosion = new BufferedImage[3];
    public BufferedImage[] rightExplosion = new BufferedImage[3];
    public BufferedImage[] horizontalExplosion = new BufferedImage[3];
    public BufferedImage[] verticalExplosion = new BufferedImage[3];
    public int frameBomb = 0, intervalBomb = 7, indexAniBomb = 0;
    public int frameExplosion = 0, intervalExplosion = 4, indexAniExplosion = 0;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
        FlameUp = 1;
        FlameDown = 1;
        FlameLeft = 1;
        FlameRight = 1;
        UpFlame = 1;
        DownFlame = 1;
        RightFlame = 1;
        LeftFlame = 1;
        width = 48;
        height = 48;
        getBombImage();
        exploded = false;
    }

    @Override
    public Rectangle getBound() {
        return new Rectangle(x, y + height, width, height);
    }

    public void getBombImage() {
        try {
            grass = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/map/grass.png")));
            portal =  ImageIO.read(getClass().getResourceAsStream("/res/sprites/items/portal.png"));
            //Bomb
            bombing[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/bomb.png")));
            bombing[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/bomb_1.png")));
            bombing[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/bomb_2.png")));

            fontExplosion[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/bomb_exploded.png")));
            fontExplosion[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/bomb_exploded1.png")));
            fontExplosion[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/bomb_exploded2.png")));

            upExplosion[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/explosion_vertical_top_last.png")));
            upExplosion[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/explosion_vertical_top_last1.png")));
            upExplosion[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/explosion_vertical_top_last2.png")));

            downExplosion[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/explosion_vertical_down_last.png")));
            downExplosion[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/explosion_vertical_down_last1.png")));
            downExplosion[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/explosion_vertical_down_last2.png")));

            leftExplosion[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/explosion_horizontal_left_last.png")));
            leftExplosion[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/explosion_horizontal_left_last1.png")));
            leftExplosion[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/explosion_horizontal_left_last2.png")));

            rightExplosion[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/explosion_horizontal_right_last.png")));
            rightExplosion[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/explosion_horizontal_right_last1.png")));
            rightExplosion[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/explosion_horizontal_right_last2.png")));

            horizontalExplosion[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/explosion_horizontal.png")));
            horizontalExplosion[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/explosion_horizontal1.png")));
            horizontalExplosion[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/explosion_horizontal2.png")));

            verticalExplosion[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/explosion_vertical.png")));
            verticalExplosion[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/explosion_vertical1.png")));
            verticalExplosion[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/object/explosion_vertical2.png")));
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
            WallExploded(gp);
            BrickExploded(gp);
            EnemyExploded(gp);
            OnealExploded(gp);
            BombermanExploded(gp);
            FlameExploded(gp);
            SpeedItemExploded(gp);
            PortalItemExploded(gp);
        }
    }

    public void draw(Graphics2D g2, GamePanel gp, int x, int y) {
        int bombX = this.x;
        int bombY = this.y;
        if (x < gp.screenWidth / 2) {
            if (gp.tile.map[this.y/gp.tileSize][this.x/gp.tileSize] == 'x') {
                g2.drawImage(portal, this.x, this.y, gp.tileSize, gp.tileSize, null);
            } else {
                g2.drawImage(grass, this.x, this.y, gp.tileSize, gp.tileSize, null);
            }
            g2.drawImage(bombing[indexAniBomb], this.x, this.y, gp.tileSize, gp.tileSize, null);
        } else if (this.x < gp.screenWidth / 2 && x > gp.screenWidth / 2) {
            int bombScreenX = this.x - x + gp.screenWidth / 2;
            bombX = bombScreenX;
            g2.drawImage(grass, bombScreenX, this.y, gp.tileSize, gp.tileSize, null);
            g2.drawImage(bombing[indexAniBomb], bombScreenX, this.y, gp.tileSize, gp.tileSize, null);
        } else if (this.x >= gp.screenWidth / 2 && this.x <= gp.worldWidth - gp.screenWidth / 2 && x >= gp.screenWidth / 2 && x <= gp.worldWidth - gp.screenWidth / 2) {
            int bombScreenX = gp.screenWidth / 2 + this.x - x;
            bombX = bombScreenX;
            g2.drawImage(grass, bombScreenX, this.y, gp.tileSize, gp.tileSize, null);
            g2.drawImage(bombing[indexAniBomb], bombScreenX, this.y, gp.tileSize, gp.tileSize, null);
        } else if (x > gp.worldWidth - gp.screenWidth / 2) {
            int bombScreenX = this.x - gp.worldWidth + gp.screenWidth;
            bombX = bombScreenX;
            g2.drawImage(grass, bombScreenX, this.y, gp.tileSize, gp.tileSize, null);
            g2.drawImage(bombing[indexAniBomb], bombScreenX, this.y, gp.tileSize, gp.tileSize, null);
        } else if (this.x > gp.worldWidth - gp.screenWidth / 2 && x <= gp.worldWidth - gp.screenWidth / 2) {
            int bombScreenX = this.x - x + gp.screenWidth / 2;
            bombX = bombScreenX;
            if (gp.tile.map[this.y/gp.tileSize][this.x/gp.tileSize] == 'x') {
                g2.drawImage(portal, bombScreenX, this.y, gp.tileSize, gp.tileSize, null);
            } else {
                g2.drawImage(grass, bombScreenX, this.y, gp.tileSize, gp.tileSize, null);
            }
            g2.drawImage(bombing[indexAniBomb], bombScreenX, this.y, gp.tileSize, gp.tileSize, null);
        }
        if (this.exploded) {
            for (int i = 1; i < UpFlame; i++) {
                g2.drawImage(verticalExplosion[indexAniExplosion], bombX, bombY - gp.tileSize * i, gp.tileSize, gp.tileSize, null);
            }
            for (int i = 1; i < DownFlame; i++) {
                g2.drawImage(verticalExplosion[indexAniExplosion], bombX, bombY + gp.tileSize * i, gp.tileSize, gp.tileSize, null);
            }
            for (int i = 1; i < LeftFlame; i++) {
                g2.drawImage(horizontalExplosion[indexAniExplosion], bombX - gp.tileSize * i, bombY, gp.tileSize, gp.tileSize, null);
            }
            for (int i = 1; i < RightFlame; i++) {
                g2.drawImage(horizontalExplosion[indexAniExplosion], bombX + gp.tileSize * i, bombY, gp.tileSize, gp.tileSize, null);
            }
            g2.drawImage(fontExplosion[indexAniExplosion], bombX, bombY, gp.tileSize, gp.tileSize, null);
            if (UpFlame == FlameUp) {
                g2.drawImage(upExplosion[indexAniExplosion], bombX, bombY - gp.tileSize * UpFlame, gp.tileSize, gp.tileSize, null);
            }
            if (DownFlame == FlameDown) {
                g2.drawImage(downExplosion[indexAniExplosion], bombX, bombY + gp.tileSize * DownFlame, gp.tileSize, gp.tileSize, null);
            }
            if (LeftFlame == FlameLeft) {
                g2.drawImage(leftExplosion[indexAniExplosion], bombX - gp.tileSize * LeftFlame, bombY, gp.tileSize, gp.tileSize, null);
            }
            if (RightFlame == FlameRight) {
                g2.drawImage(rightExplosion[indexAniExplosion], bombX + gp.tileSize * RightFlame, bombY, gp.tileSize, gp.tileSize, null);
            }
        }
    }

    public void WallExploded(GamePanel gp) {
        int statusx = x / gp.tileSize;
        int statusy = y / gp.tileSize;
        for (int i = 1; i <= DownFlame; i++) {
            if (TileManager.obj[statusy + i][statusx] instanceof Wall) {
                if (DownFlame == 1) {
                    DownFlame = i - 1;
                } else {
                    DownFlame = i;
                    FlameDown = -1;
                }
            }
        }
        for (int i = 1; i <= UpFlame; i++) {
            if (TileManager.obj[statusy - i][statusx] instanceof Wall) {
                if (UpFlame == 1) {
                    UpFlame = i - 1;
                } else {
                    UpFlame = i;
                    FlameUp = -1;
                }
            }
        }
        for (int i = 1; i <= RightFlame; i++) {
            if (TileManager.obj[statusy][statusx + i] instanceof Wall) {
                if (RightFlame == 1) {
                    RightFlame = i - 1;
                } else {
                    RightFlame = i;
                    FlameRight = -1;
                }
            }
        }
        for (int i = 1; i <= LeftFlame; i++) {
            if (TileManager.obj[statusy][statusx - i] instanceof Wall) {
                if (LeftFlame == 1) {
                    LeftFlame = i - 1;
                } else {
                    LeftFlame = i;
                    FlameLeft = -1;
                }
            }
        }
    }

    public void BrickExploded(GamePanel gp) {
        int statusx = x / gp.tileSize;
        int statusy = y / gp.tileSize;
        for (int i = 1; i <= DownFlame; i++) {
            if (TileManager.obj[statusy + i][statusx] instanceof Brick) {
                TileManager.obj[statusy + i][statusx] = new Grass(statusx * gp.tileSize, (statusy + i) * gp.tileSize);
                DownFlame = i;
            }
        }
        for (int i = 1; i <= UpFlame; i++) {
            if (TileManager.obj[statusy - i][statusx] instanceof Brick) {
                TileManager.obj[statusy - i][statusx] = new Grass(statusx * gp.tileSize, (statusy - i) * gp.tileSize);
                UpFlame = i;
            }
        }
        for (int i = 1; i <= RightFlame; i++) {
            if (TileManager.obj[statusy][statusx + i] instanceof Brick) {
                TileManager.obj[statusy][statusx + i] = new Grass((statusx + i) * gp.tileSize, statusy * gp.tileSize);
                RightFlame = i;
            }
        }
        for (int i = 1; i <= LeftFlame; i++) {
            if (TileManager.obj[statusy][statusx - i] instanceof Brick) {
                TileManager.obj[statusy][statusx - i] = new Grass((statusx - i) * gp.tileSize, statusy * gp.tileSize);
                LeftFlame = i;
            }
        }
    }

    public void FlameExploded(GamePanel gp) {
        int statusx = x / gp.tileSize;
        int statusy = y / gp.tileSize;
        for (int i = 1; i <= DownFlame; i++) {
            if (TileManager.obj[statusy + i][statusx] instanceof BrickFlameItem) {
                TileManager.obj[statusy + i][statusx] = new FlameItem(statusx * gp.tileSize, (statusy + i) * gp.tileSize);
                DownFlame = i;
            }
        }
        for (int i = 1; i <= UpFlame; i++) {
            if (TileManager.obj[statusy - i][statusx] instanceof BrickFlameItem) {
                TileManager.obj[statusy - i][statusx] = new FlameItem(statusx * gp.tileSize, (statusy - i) * gp.tileSize);
                UpFlame = i;
            }
        }
        for (int i = 1; i <= RightFlame; i++) {
            if (TileManager.obj[statusy][statusx + i] instanceof BrickFlameItem) {
                TileManager.obj[statusy][statusx + i] = new FlameItem((statusx + i) * gp.tileSize, statusy * gp.tileSize);
                RightFlame = i;
            }
        }
        for (int i = 1; i <= LeftFlame; i++) {
            if (TileManager.obj[statusy][statusx - i] instanceof BrickFlameItem) {
                TileManager.obj[statusy][statusx - i] = new FlameItem((statusx - i) * gp.tileSize, statusy * gp.tileSize);
                LeftFlame = i;
            }
        }
    }

    public void SpeedItemExploded(GamePanel gp) {
        int statusx = x / gp.tileSize;
        int statusy = y / gp.tileSize;
        for (int i = 1; i <= DownFlame; i++) {
            if (TileManager.obj[statusy + i][statusx] instanceof BrickSpeedItem) {
                TileManager.obj[statusy + i][statusx] = new SpeedItem(statusx * gp.tileSize, (statusy + i) * gp.tileSize);
                DownFlame = i;
            }
        }
        for (int i = 1; i <= UpFlame; i++) {
            if (TileManager.obj[statusy - i][statusx] instanceof BrickSpeedItem) {
                TileManager.obj[statusy - i][statusx] = new SpeedItem(statusx * gp.tileSize, (statusy - i) * gp.tileSize);
                UpFlame = i;
            }
        }
        for (int i = 1; i <= RightFlame; i++) {
            if (TileManager.obj[statusy][statusx + i] instanceof BrickSpeedItem) {
                TileManager.obj[statusy][statusx + i] = new SpeedItem((statusx + i) * gp.tileSize, statusy * gp.tileSize);
                RightFlame = i;
            }
        }
        for (int i = 1; i <= LeftFlame; i++) {
            if (TileManager.obj[statusy][statusx - i] instanceof BrickSpeedItem) {
                TileManager.obj[statusy][statusx - i] = new SpeedItem((statusx - i) * gp.tileSize, statusy * gp.tileSize);
                LeftFlame = i;
            }
        }
    }

    public void PortalItemExploded(GamePanel gp) {
        int statusx = x / gp.tileSize;
        int statusy = y / gp.tileSize;
        for (int i = 1; i <= DownFlame; i++) {
            if (TileManager.obj[statusy + i][statusx] instanceof BrickPortalItem) {
                TileManager.obj[statusy + i][statusx] = new PortalItem(statusx * gp.tileSize, (statusy + i) * gp.tileSize);
                DownFlame = i;
            }
        }
        for (int i = 1; i <= UpFlame; i++) {
            if (TileManager.obj[statusy - i][statusx] instanceof BrickPortalItem) {
                TileManager.obj[statusy - i][statusx] = new PortalItem(statusx * gp.tileSize, (statusy - i) * gp.tileSize);
                UpFlame = i;
            }
        }
        for (int i = 1; i <= RightFlame; i++) {
            if (TileManager.obj[statusy][statusx + i] instanceof BrickPortalItem) {
                TileManager.obj[statusy][statusx + i] = new PortalItem((statusx + i) * gp.tileSize, statusy * gp.tileSize);
                RightFlame = i;
            }
        }
        for (int i = 1; i <= LeftFlame; i++) {
            if (TileManager.obj[statusy][statusx - i] instanceof BrickPortalItem) {
                TileManager.obj[statusy][statusx - i] = new PortalItem((statusx - i) * gp.tileSize, statusy * gp.tileSize);
                LeftFlame = i;
            }
        }
    }

    public void EnemyExploded(GamePanel gp) {
        int statusx = x / gp.tileSize;
        int statusy = y / gp.tileSize;
        for (int i = 0; i < gp.enemy.length; i++) {
            if (gp.enemy[i] != null) {
                for (int j = 1; j <= DownFlame; j++) {
                    if (TileManager.obj[statusy + j][statusx].getBound().intersects(gp.enemy[i].getBound(gp.enemy[i].x, gp.enemy[i].y))) {
                        gp.enemy[i].isDead = true;
                        break;
                    }
                }
            }
            if (gp.enemy[i] != null) {
                for (int j = 1; j <= UpFlame; j++) {
                    if (TileManager.obj[statusy - j][statusx].getBound().intersects(gp.enemy[i].getBound(gp.enemy[i].x, gp.enemy[i].y))) {
                        gp.enemy[i].isDead = true;
                        break;
                    }
                }
            }
            if (gp.enemy[i] != null) {
                for (int j = 1; j <= RightFlame; j++) {
                    if (TileManager.obj[statusy][statusx + j].getBound().intersects(gp.enemy[i].getBound(gp.enemy[i].x, gp.enemy[i].y))) {
                        gp.enemy[i].isDead = true;
                        break;
                    }
                }
            }
            if (gp.enemy[i] != null) {
                for (int j = 1; j <= LeftFlame; j++) {
                    if (TileManager.obj[statusy][statusx - j].getBound().intersects(gp.enemy[i].getBound(gp.enemy[i].x, gp.enemy[i].y))) {
                        gp.enemy[i].isDead = true;
                        break;
                    }
                }
            }
        }
    }

    public void OnealExploded(GamePanel gp) {
        int statusx = x / gp.tileSize;
        int statusy = y / gp.tileSize;
        for (int i = 0; i < gp.oneals.length; i++) {
            if (gp.oneals[i] != null) {
                for (int j = 1; j <= DownFlame; j++) {
                    if (TileManager.obj[statusy + j][statusx].getBound().intersects(gp.oneals[i].getBound(gp.oneals[i].x, gp.oneals[i].y))) {
                        gp.oneals[i].isDead = true;
                        break;
                    }
                }
            }
            if (gp.oneals[i] != null) {
                for (int j = 1; j <= UpFlame; j++) {
                    if (TileManager.obj[statusy - j][statusx].getBound().intersects(gp.oneals[i].getBound(gp.oneals[i].x, gp.oneals[i].y))) {
                        gp.oneals[i].isDead = true;
                        break;
                    }
                }
            }
            if (gp.oneals[i] != null) {
                for (int j = 1; j <= RightFlame; j++) {
                    if (TileManager.obj[statusy][statusx + j].getBound().intersects(gp.oneals[i].getBound(gp.oneals[i].x, gp.oneals[i].y))) {
                        gp.oneals[i].isDead = true;
                        break;
                    }
                }
            }
            if (gp.oneals[i] != null) {
                for (int j = 1; j <= LeftFlame; j++) {
                    if (TileManager.obj[statusy][statusx - j].getBound().intersects(gp.oneals[i].getBound(gp.oneals[i].x, gp.oneals[i].y))) {
                        gp.oneals[i].isDead = true;
                        break;
                    }
                }
            }
        }
    }

    public void BombermanExploded(GamePanel gp) {
        int statusx = x / gp.tileSize;
        int statusy = y / gp.tileSize;
        for (int j = 1; j <= DownFlame; j++) {
            if (TileManager.obj[statusy + j][statusx].getBound().intersects(gp.bomberman.getBound(gp.bomberman.x, gp.bomberman.y))) {
                gp.bomberman.isDead = true;
                break;
            }
        }
        for (int j = 1; j <= UpFlame; j++) {
            if (TileManager.obj[statusy - j][statusx].getBound().intersects(gp.bomberman.getBound(gp.bomberman.x, gp.bomberman.y))) {
                gp.bomberman.isDead = true;
                break;
            }
        }
        for (int j = 1; j <= RightFlame; j++) {
            if (TileManager.obj[statusy][statusx + j].getBound().intersects(gp.bomberman.getBound(gp.bomberman.x, gp.bomberman.y))) {
                gp.bomberman.isDead = true;
                break;
            }
        }
        for (int j = 1; j <= LeftFlame; j++) {
            if (TileManager.obj[statusy][statusx - j].getBound().intersects(gp.bomberman.getBound(gp.bomberman.x, gp.bomberman.y))) {
                gp.bomberman.isDead = true;
                break;
            }
        }
    }
}
