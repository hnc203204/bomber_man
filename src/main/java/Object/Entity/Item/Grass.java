package Object.Entity.Item;

import Atributes.Point;
import Atributes.Sprite;
import DataConfig.Map;
import Object.Entity.Entity;
import SystemManagement.EntitiesManagement;

public class Grass extends Entity {

    /**
     * constructor 1.
     * @param position position.
     * @param width width.
     * @param height height.
     * @param original original.
     * @param entitiesManagement entities management
     */
    public Grass(Point position, int width, int height, Sprite original, EntitiesManagement entitiesManagement, Map map) {
        super(position, width, height, original, entitiesManagement, map);
    }

    /**
     * constructor 2.
     * @param other other
     */
    public Grass(Grass other) {
        super(
                other.getPosition(),
                other.getWidth(),
                other.getHeight(),
                other.getOriginal(),
                other.getEntitiesManagement(),
                other.getMap()
        );
    }

    @Override
    public void run() {

    }
}
