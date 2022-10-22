package GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Gameobject {
    public int x, y;
    public int width,height;
    public BufferedImage image;

    public abstract Rectangle getBound();

}
