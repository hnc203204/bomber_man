package Object.Entity;

import Atributes.Point;
import Atributes.Sprite;
import Object.Rectangle;

public abstract class Entity extends Rectangle implements Runnable {

    protected Sprite original;
    protected boolean dead;
    protected int EntityID;

    /**
     * constructor 1.
     * @param EntityID entity ID.
     * @param position position
     * @param width width
     * @param height height
     * @param original original
     */
    public Entity(int EntityID, Point position, int width, int height, Sprite original) {
        super(position, width, height);
        setOriginal(original);
        setEntityID(EntityID);
    }

    public Entity(Entity other) {
        super(other.getPosition(), other.getWidth(), other.getHeight());
        setOriginal(other.getOriginal());
        setEntityID(other.getEntityID());
    }

    /**
     * get original.
     * @return Sprite
     */
    public Sprite getOriginal() {
        return original;
    }

    /**
     * get sprite.
     * @return sprite
     */
    public Sprite getSprite() {
        return getOriginal();
    }

    /**
     * set original.
     * @param original Sprite
     */
    public void setOriginal(Sprite original) {
        this.original = new Sprite(original.getPathImage());
    }

    /**
     * set dead.
     * @param dead dead
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    /**
     * is dead?
     * @return boolean
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * set entity id.
     * @param entityID enity id
     */
    public void setEntityID(int entityID) {
        EntityID = entityID;
    }

    /**
     * get entity id.
     * @return id
     */
    public int getEntityID() {
        return EntityID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Entity) {
            if (((Entity) obj).getEntityID() == getEntityID()) {
                return true;
            }
        }
        return false;
    }
}
