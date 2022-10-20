package GameObject.mapObject;

import GameObject.Gameobject;
import GameObject.entity.Bomberman;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Wall extends Gameobject {
    public Wall() {};
    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
        width = 48;
        heigth = 48;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/GameObject/sprites/map/wall.png"));
        } catch (IOException e) {

        }
    }
    public int getContact() {
        return 1;
    }

    @Override
    public Rectangle getBound() {
        return new Rectangle(x,y+48,width,heigth);
    }
}
