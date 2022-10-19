package GameObject.object;

import GameObject.Gameobject;
import main.GamePanel;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bomb extends Gameobject {
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
        getBombImage();
        exploded = false;
    }
    @Override
    public Rectangle getBound() {
        return new Rectangle(x,y,width, heigth);
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
    public void Explosion() {
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
        }
    }
    public void draw(Graphics2D g2,GamePanel gp,int x, int y) {
        int bombX = this.x / gp.tileSize;
        int bombY = this.y / gp.tileSize;
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
}