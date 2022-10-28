package GameObject.Item;

import GameObject.Gameobject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class SpeedItem extends Gameobject {
    public SpeedItem() {
    }

    public SpeedItem(int x, int y) {
        this.x = x;
        this.y = y;
        width = 48;
        height = 48;
        try {
            image =  ImageIO.read(getClass().getResourceAsStream("/res/sprites/items/powerup_speed.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Rectangle getBound() {
        return new Rectangle(x, y + height, width, height);
    }
}
