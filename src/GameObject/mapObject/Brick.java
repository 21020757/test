package GameObject.mapObject;

import GameObject.Gameobject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Brick extends Gameobject {
    public Brick() {};
    public Brick(int x,int y) {
        this.x = x;
        this.y = y;
        width = 48;
        height = 48;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/GameObject/sprites/map/brick.png"));
        } catch (IOException e) {

        }
    }

    @Override
    public Rectangle getBound() {
        return new Rectangle(x,y+height,width,height);
    }

}
