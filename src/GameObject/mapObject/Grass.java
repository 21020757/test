package GameObject.mapObject;

import GameObject.Gameobject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Grass extends Gameobject {
    public int x,y;
    public Grass() {};
    public Grass(int x, int y) {
        this.x = x;
        this.y = y;
        width = 48;
        heigth = 48;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/GameObject/sprites/map/grass.png"));
        } catch (IOException e) {

        }
    }

    @Override
    public Rectangle getBound() {
        return new Rectangle(x,y+48,width,heigth);
    }
}
