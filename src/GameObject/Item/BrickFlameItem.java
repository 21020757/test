package GameObject.Item;

import GameObject.Gameobject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class BrickFlameItem extends Gameobject {
    public BrickFlameItem() {
    }

    public BrickFlameItem(int x, int y) {
        this.x = x;
        this.y = y;
        width = 48;
        height = 48;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/GameObject/sprites/map/Brick.png"));
        }catch (IOException e) {
        }
    }
    @Override
    public Rectangle getBound() {
        return new Rectangle(x, y + height, width, height);
    }
}
