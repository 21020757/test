package GameObject.mapObject;

import GameObject.Gameobject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Brick extends Gameobject {
    public Brick() {
    }

    ;

    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
        width = 48;
        height = 48;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sprites/map/brick.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Rectangle getBound() {
        return new Rectangle(x, y + height, width, height);
    }

}
