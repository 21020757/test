package main;


import GameObject.object.SPEED;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.items[0] = new SPEED();
        gp.items[0].x = 3* gp.tileSize;
        gp.items[0].y = 3* gp.tileSize;

        gp.items[1] = new SPEED();
        gp.items[1].x = 6* gp.tileSize;
        gp.items[1].y = 6* gp.tileSize;
    }
}
