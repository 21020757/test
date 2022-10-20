package GameObject.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class SPEED extends SuperObject {
    public SPEED() {
        name = "speed";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/GameObject/sprites/items/powerup_speed.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
