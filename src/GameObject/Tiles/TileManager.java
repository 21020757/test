package GameObject.Tiles;

import GameObject.Gameobject;
import GameObject.mapObject.Brick;
import GameObject.mapObject.Grass;
import GameObject.mapObject.Wall;
import main.GamePanel;

import javax.imageio.ImageIO;
import javax.management.DescriptorRead;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public static Gameobject obj[][];

    char map[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        obj = new Gameobject[gp.maxWorldRow][gp.maxWorldCol];
        map = new char[gp.maxWorldRow][gp.maxWorldCol];
        loadMap();
        createObject();
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/GameObject/Tiles/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            for (int i = 0; i < gp.maxWorldRow; i++) {
                String line = br.readLine();
                for (int j = 0; j < gp.maxWorldCol; j++) {
                    map[i][j] = line.charAt(j);
                }
            }
            br.close();
        } catch (Exception e) {

        }
    }

    public void createObject() {
        for (int i = 0; i < gp.maxWorldRow; i++) {
            for (int j = 0; j < gp.maxWorldCol; j++) {
                char s = map[i][j];
                if (s == ' ') {
                    obj[i][j] = new Grass(j * 48, i * 48);
                } else if (s == '#') {
                    obj[i][j] = new Wall(j * 48, i * 48);
                } else {
                    obj[i][j] = new Brick(j * 48, i * 48);
                }
            }
        }
    }
    public void draw(Graphics2D g2) {
        for (int i = 0; i < gp.maxWorldRow; i++) {
            for (int j = 0; j < gp.maxWorldCol; j++) {
                char s = map[i][j];
                if (gp.bomberman.x >= gp.screenWidth / 2 && gp.bomberman.x <= gp.wordWidth - gp.screenWidth / 2) {
                    int ScreenX = j * gp.tileSize - gp.bomberman.x + gp.screenWidth / 2;
                    if (s == ' ') {
                        g2.drawImage(obj[i][j].image, ScreenX, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
                    } else if (s == '#') {
                        g2.drawImage(obj[i][j].image, ScreenX, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
                    } else {
                        g2.drawImage(obj[i][j].image, ScreenX, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
                    }
                } else if (gp.bomberman.x < gp.screenWidth / 2) {
                    if (s == ' ') {
                        g2.drawImage(obj[i][j].image, j * gp.tileSize, i * gp.tileSize, gp.tileSize, gp.tileSize, null);

                    } else if (s == '#') {
                        g2.drawImage(obj[i][j].image, j * gp.tileSize, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
                    } else {
                        g2.drawImage(obj[i][j].image, j * gp.tileSize, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
                    }
                } else {
                    int ScreenX = j * gp.tileSize - gp.wordWidth + gp.screenWidth;
                    if (s == ' ') {
                        g2.drawImage(obj[i][j].image, ScreenX, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
                    } else if (s == '#') {
                        g2.drawImage(obj[i][j].image, ScreenX, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
                    } else {
                        g2.drawImage(obj[i][j].image, ScreenX, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
                    }
                }
            }
        }
    }
}