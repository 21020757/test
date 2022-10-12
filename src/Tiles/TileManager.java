package Tiles;

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
    Tile[] tile;

    char map[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[5];
        map = new char[gp.maxWorldRow][gp.maxWorldCol];
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/grass.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/wall.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/brick.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/Tiles/map.txt");
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

    public void draw(Graphics2D g2) {
        for (int i = 0; i < gp.maxWorldRow; i++) {
            for (int j = 0; j < gp.maxWorldCol; j++) {
                char s = map[i][j];
                if (gp.bomberman.x >= gp.screenWidth/2 && gp.bomberman.x <= gp.wordWidth - gp.screenWidth/2) {
                    int ScreenX = j * gp.tileSize - gp.bomberman.x + gp.screenWidth/2;
                    if (s == ' ') {
                        g2.drawImage(tile[0].image, ScreenX, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
                    } else if (s == '#') {
                        g2.drawImage(tile[1].image, ScreenX, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
                    } else {
                        g2.drawImage(tile[2].image, ScreenX, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
                    }
                } else if (gp.bomberman.x < gp.screenWidth/2){
                    if (s == ' ') {
                        g2.drawImage(tile[0].image, j * gp.tileSize, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
                    } else if (s == '#') {
                        g2.drawImage(tile[1].image, j * gp.tileSize, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
                    } else {
                        g2.drawImage(tile[2].image, j * gp.tileSize, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
                    }
                } else {
                    int ScreenX = j * gp.tileSize - gp.wordWidth + gp.screenWidth;
                    if (s == ' ') {
                        g2.drawImage(tile[0].image, ScreenX, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
                    } else if (s == '#') {
                        g2.drawImage(tile[1].image, ScreenX, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
                    } else {
                        g2.drawImage(tile[2].image, ScreenX, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
                    }
                }
            }
        }
    }
}
