package GameObject.entity;

import GameObject.Item.BombItem;
import GameObject.Item.FlameItem;
import GameObject.Item.PortalItem;
import GameObject.Item.SpeedItem;
import GameObject.Tiles.TileManager;
import GameObject.mapObject.Brick;
import GameObject.mapObject.Grass;
import GameObject.mapObject.Wall;
import main.GamePanel;
import main.KeyHandler;
import GameObject.object.Bomb;
import main.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static java.awt.SystemColor.menu;

public class Bomberman extends Entity {
    Sound sound = new Sound();
    public static KeyHandler keyH;
    public static final int intervalImageChange = 9;
    public boolean notMoving;
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
        status = true;
    }

    public void getPlayerImage() {
        try {
            //Move
            up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_up.png")));
            down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_down.png")));
            left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_left.png")));
            right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_right.png")));
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_right_2.png")));
            dead1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_dead1.png")));
            dead2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_dead2.png")));
            dead3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/player/player_dead3.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed
                || keyH.leftPressed || keyH.rightPressed || keyH.spacePressed) {
            notMoving = false;
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
                    bomby = (y + gp.tileSize / 2 )/ gp.tileSize;
                    BombAmount--;
                    TileManager.obj[bomby][bombx] = new Bomb(bombx * 48, bomby*48);
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
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            notMoving = true;
        }
        pickItemFlame();
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
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        if (notMoving) {
            switch (preDirection) {
                case "up" -> image = up;
                case "down" -> image = down;
                case "left" -> image = left;
                case "right" -> image = right;
            }
        } else {
            switch (direction) {
                case "up" -> {
                    if (spriteNum == 1) {
                        image = up1;
                    } else if (spriteNum == 2) {
                        image = up2;
                    }
                }
                case "down" -> {
                    if (spriteNum == 1) {
                        image = down1;
                    } else if (spriteNum == 2) {
                        image = down2;
                    }
                }
                case "left" -> {
                    if (spriteNum == 1) {
                        image = left1;
                    } else if (spriteNum == 2) {
                        image = left2;
                    }
                }
                case "right" -> {
                    if (spriteNum == 1) {
                        image = right1;
                    } else if (spriteNum == 2) {
                        image = right2;
                    }
                }
            }
        }
        if (BombAmount < count) {
            if (TileManager.obj[bomby][bombx] instanceof Bomb) {
                ((Bomb) TileManager.obj[bomby][bombx]).draw(g2, gp, x, y);
            }
        }
        if (x >= gp.screenWidth / 2 && x <= gp.worldWidth - gp.screenWidth / 2) {
            ScreenX = gp.screenWidth / 2;
            g2.drawImage(image, ScreenX, y, gp.tileSize, gp.tileSize, null);
        } else if (x < gp.screenWidth / 2) {
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        } else {
            ScreenX = x - gp.worldWidth + gp.screenWidth;
            g2.drawImage(image, ScreenX, y, gp.tileSize, gp.tileSize, null);
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
                        if (gp.EntityDead == gp.lengthOneal + gp.lengthEnemies) {

                        }
                    }
                }
            }
        }
    }

    @Override
    public void drawDead(Graphics2D g2) {
        if (x >= gp.screenWidth / 2 && x <= gp.worldWidth - gp.screenWidth / 2) {
            ScreenX = gp.screenWidth / 2;
            g2.drawImage(dead1, ScreenX, y, gp.tileSize, gp.tileSize, null);
            g2.drawImage(dead2, ScreenX, y, gp.tileSize, gp.tileSize, null);
            g2.drawImage(dead3, ScreenX, y, gp.tileSize, gp.tileSize, null);
        } else if (x < gp.screenWidth / 2) {
            g2.drawImage(dead1, x, y, gp.tileSize, gp.tileSize, null);
            g2.drawImage(dead2, x, y, gp.tileSize, gp.tileSize, null);
            g2.drawImage(dead3, x, y, gp.tileSize, gp.tileSize, null);
        } else {
            ScreenX = x - gp.worldWidth + gp.screenWidth;
            g2.drawImage(dead1, ScreenX, y, gp.tileSize, gp.tileSize, null);
            g2.drawImage(dead2, ScreenX, y, gp.tileSize, gp.tileSize, null);
            g2.drawImage(dead3, ScreenX, y, gp.tileSize, gp.tileSize, null);
        }
    }
}
