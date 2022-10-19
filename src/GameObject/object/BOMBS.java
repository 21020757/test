package GameObject.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class BOMBS extends SuperObject{
    public BOMBS() {
        name = "BOMBS";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/items/powerup_bombs.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
