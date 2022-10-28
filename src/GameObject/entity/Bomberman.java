package GameObject.entity;

import GameObject.Item.FlameItem;
import GameObject.Item.PortalItem;
import GameObject.Item.SpeedItem;
import GameObject.Tiles.TileManager;
import GameObject.mapObject.Grass;
import main.GamePanel;
import main.KeyHandler;
import GameObject.object.Bomb;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Bomberman extends Entity {
    public static KeyHandler keyH;
    public static final int intervalImageChange = 9;
    public String preDirection;
    public int ScreenX;

    //About bomb
    public int BombAmount;
    int bombx;
    int bomby;
    public int FlameBomb;
    public int count;


    public Bomberman(GamePanel gp, KeyHandler keyH) {
        super(gp);
        Bomberman.keyH = keyH;
        preDirection = "down";
        setDefaultValues();
        getPlayerImage();
    }


    public void setDefaultValues() {
        x = gp.tileSize;
        y = gp.tileSize;
        speed = 2;
        direction = "down";
        width = 32;
        height = 48;
        BombAmount = 1;
        count = 1;
        FlameBomb = 1;
        isDead = false;
    }

    public void getPlayerImage() {
        try {
            up[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_up.png")));
            up[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_up_1.png")));
            up[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_up_2.png")));
            down[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_down.png")));
            down[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_down_1.png")));
            down[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_down_2.png")));
            left[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_left.png")));
            left[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_left_1.png")));
            left[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_left_2.png")));
            right[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_right.png")));
            right[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_right_1.png")));
            right[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_right_2.png")));
            dead[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_dead1.png")));
            dead[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_dead2.png")));
            dead[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_dead3.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed
                || keyH.leftPressed || keyH.rightPressed || keyH.spacePressed) {
            if (keyH.upPressed) {
                if (!collisionUp()) {
                    y -= speed;
                }
                direction = "up";
                preDirection = "up";
            }
            if (keyH.downPressed) {
                if (!collisionDown()) {
                    y += speed;
                }
                direction = "down";
                preDirection = "down";
            }
            if (keyH.leftPressed) {
                if (!collisionLeft()) {
                    x -= speed;
                }
                direction = "left";
                preDirection = "left";
            }
            if (keyH.rightPressed) {
                if (!collisionRight()) {
                    x += speed;
                }
                direction = "right";
                preDirection = "right";
            }
            if (keyH.spacePressed) {
                if (BombAmount > 0) {
                    bombx = (x + gp.tileSize / 2) / gp.tileSize;
                    bomby = (y + gp.tileSize / 2) / gp.tileSize;
                    BombAmount--;
                    TileManager.obj[bomby][bombx] = new Bomb(bombx * 48, bomby * 48);
                    ((Bomb) TileManager.obj[bomby][bombx]).FlameDown = FlameBomb;
                    ((Bomb) TileManager.obj[bomby][bombx]).FlameUp = FlameBomb;
                    ((Bomb) TileManager.obj[bomby][bombx]).FlameRight = FlameBomb;
                    ((Bomb) TileManager.obj[bomby][bombx]).FlameLeft = FlameBomb;

                    ((Bomb) TileManager.obj[bomby][bombx]).RightFlame = FlameBomb;
                    ((Bomb) TileManager.obj[bomby][bombx]).LeftFlame = FlameBomb;
                    ((Bomb) TileManager.obj[bomby][bombx]).UpFlame = FlameBomb;
                    ((Bomb) TileManager.obj[bomby][bombx]).DownFlame = FlameBomb;

                    gp.playSE(2);
                }
            }

            spriteCounter++;
            if (spriteCounter > intervalImageChange) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 0;
                } else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        if (BombAmount < count) {
            if (TileManager.obj[bomby][bombx] instanceof Bomb) {
                ((Bomb) TileManager.obj[bomby][bombx]).Explosion(gp);
                if (((Bomb) TileManager.obj[bomby][bombx]).indexAniExplosion == 3) {
                    ((Bomb) TileManager.obj[bomby][bombx]).indexAniExplosion = 0;
                    gp.playSE(3);
                    TileManager.obj[bomby][bombx] = new Grass(bombx, bomby);
                    BombAmount++;
                }
            }
        }
        pickItemFlame();
        EnemiesPick();
    }

    public void draw(Graphics2D g2) {
        BufferedImage[] image = null;
        switch (direction) {
            case "up" -> image = up;
            case "down" -> image = down;
            case "left" -> image = left;
            case "right" -> image = right;
        }

        if (BombAmount < count) {
                if (TileManager.obj[bomby][bombx] instanceof Bomb) {
                    ((Bomb) TileManager.obj[bomby][bombx]).draw(g2, gp, x, y);
                }
        }
        if (x >= gp.screenWidth / 2 && x <= gp.worldWidth - gp.screenWidth / 2) {
            ScreenX = gp.screenWidth / 2;
            g2.drawImage(image[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
        } else if (x < gp.screenWidth / 2) {
            g2.drawImage(image[spriteNum], x, y, gp.tileSize, gp.tileSize, null);
        } else {
            ScreenX = x - gp.worldWidth + gp.screenWidth;
            g2.drawImage(image[spriteNum], ScreenX, y, gp.tileSize, gp.tileSize, null);
        }
    }

    public void drawDead(Graphics2D g2) {
        deadCount++;
        if (deadCount > 60) {
            if (deadNum == 0) {
                deadNum = 1;
            } else if (deadNum == 1) {
                deadNum = 2;
            } else {
                deadNum = 0;
            }
            deadCount = 0;
        }
        if (gp.bomberman.x <= gp.screenWidth / 2) {
            g2.drawImage(dead[deadNum], x, y, width, height, null);
        } else if (gp.bomberman.x < gp.worldWidth - gp.screenWidth / 2 && x >= gp.screenWidth / 2) {
            int ScreenX = x + gp.screenWidth / 2 - gp.bomberman.x;
            g2.drawImage(dead[deadNum], ScreenX, y, width, height, null);
        } else if (x < gp.screenWidth / 2) {
            int ScreenX = gp.screenWidth / 2 + this.x - gp.bomberman.x;
            g2.drawImage(dead[deadNum], ScreenX, y, width, height, null);
        } else {
            int ScreenX = x - gp.worldWidth + gp.screenWidth;
            g2.drawImage(dead[deadNum], ScreenX, y, width, height, null);
        }
    }

    public void pickItemFlame() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 31; j++) {
                if (TileManager.obj[i][j] instanceof FlameItem) {
                    if (this.getBound(x, y).intersects(TileManager.obj[i][j].getBound())) {
                        TileManager.obj[i][j] = new Grass(j * gp.tileSize, i * gp.tileSize);
                        FlameBomb++;
                    }
                }
                if (TileManager.obj[i][j] instanceof SpeedItem) {
                    if (this.getBound(x, y).intersects(TileManager.obj[i][j].getBound())) {
                        TileManager.obj[i][j] = new Grass(j * gp.tileSize, i * gp.tileSize);
                        speed++;
                    }
                }
                if (TileManager.obj[i][j] instanceof PortalItem) {
                    if (this.getBound(x, y).intersects(TileManager.obj[i][j].getBound())) {
//                        if ( == gp.lengthOneal + gp.lengthEnemies) {
//                            //TODO: QUA MAN
//                        }
                    }
                }
            }
        }
    }

    public void EnemiesPick() {
        for (int i = 0; i < gp.enemy.length; i++) {
            if (gp.enemy[i] != null) {
                if (this.getBound(x,y).intersects(gp.enemy[i].getBound(gp.enemy[i].x, gp.enemy[i].y))) {
                    isDead = true;
                }
            }
        }
        for (int i = 0; i < gp.oneals.length; i++) {
            if (gp.oneals[i] != null) {
                if (this.getBound(x,y).intersects(gp.oneals[i].getBound(gp.oneals[i].x, gp.oneals[i].y))) {
                    isDead = true;
                }
            }
        }
    }
}

