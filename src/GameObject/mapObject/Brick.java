package GameObject.mapObject;

import GameObject.Gameobject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Brick extends Gameobject {
    int width = 48;
    int height = 48;
    int status = 0;
    public Brick() {};
    public Brick(int x,int y) {
        this.x = x;
        this.y = y;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/GameObject/sprites/map/brick.png"));
        } catch (IOException e) {

        }
    }
    public int getContact() {
        return 1;
    }
    @Override
    public Rectangle getBound() {
        return new Rectangle(x,y+48,width,height);
    }

}
