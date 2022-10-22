package GameObject.Item;

import GameObject.Gameobject;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.PublicKey;

public class FlameItem extends Gameobject {
    public FlameItem() {
    }

    public FlameItem(int x, int y) {
        this.x = x;
        this.y = y;
        width = 48;
        height = 48;
        try {
            image =  ImageIO.read(getClass().getResourceAsStream("/GameObject/sprites/items/powerup_flames.png"));
        }catch (IOException e) {
        }
    }
    @Override
    public Rectangle getBound() {
        return new Rectangle(x, y + height, width, height);
    }
}
