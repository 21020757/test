package GameObject.Item;

import GameObject.Gameobject;
import GameObject.object.Bomb;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class BombItem extends Gameobject {
    public BombItem () {
    };
    public BombItem (int x, int y) {
        this.x = x;
        this.y = y;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/items/powerup_bombs.png")));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Rectangle getBound() {
        return new Rectangle(x,y+height, width,height);
    }
}
