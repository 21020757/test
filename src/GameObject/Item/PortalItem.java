package GameObject.Item;

import GameObject.Gameobject;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.PublicKey;

public class PortalItem extends Gameobject {
    public PortalItem() {
    }

    public PortalItem(int x, int y) {
        this.x = x;
        this.y = y;
        width = 48;
        height = 48;
        try {
            image =  ImageIO.read(getClass().getResourceAsStream("/res/sprites/items/portal.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Rectangle getBound() {
        return new Rectangle(x, y + height, width, height);
    }
}
