package GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Gameobject {
    public int x, y,contact;
    public BufferedImage image;

    public abstract Rectangle getBound();

}
