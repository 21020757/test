package GameObject.mapObject;

import GameObject.Gameobject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Grass extends Gameobject {
    public Grass() {};
    public Grass(int x, int y) {
        this.x = x;
        this.y = y;
        width = 48;
        height = 48;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/map/grass.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Rectangle getBound() {
        return new Rectangle(x,y+height,width,height);
    }
}
