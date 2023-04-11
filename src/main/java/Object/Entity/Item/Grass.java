package Object.Entity.Item;

import Atributes.Point;
import Atributes.Sprite;
import Object.Entity.Entity;

public class Grass extends Entity {

    /**
     * constructor 1.
     * @param ID id
     * @param position position.
     * @param width width.
     * @param height height.
     * @param original original.
     */
    public Grass(int ID, Point position, int width, int height, Sprite original) {
        super(ID, position, width, height, original);
    }

    /**
     * constructor 2.
     * @param other other
     */
    public Grass(Grass other) {
        super(
                other.getEntityID(),
                other.getPosition(),
                other.getWidth(),
                other.getHeight(),
                other.getOriginal()
        );
    }

    @Override
    public void run() {

    }
}
