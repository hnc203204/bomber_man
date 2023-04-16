package Object.Entity;

import Atributes.Point;
import Atributes.Sprite;
import DataConfig.Map;
import Object.Rectangle;
import SystemManagement.EntitiesManagement;

import java.awt.*;

public abstract class Entity extends Rectangle implements Runnable {

    public static int numberOfEntities = 0;
    protected Sprite original;
    protected boolean dead;
    protected int EntityID;
    protected EntitiesManagement entitiesManagement;
    protected Map map;

    /**
     * constructor 1.
     * @param position position
     * @param width width
     * @param height height
     * @param original original
     * @param entitiesManagement entities mangement
     */
    public Entity(Point position, int width, int height, Sprite original, EntitiesManagement entitiesManagement, Map map) {
        super(position, width, height);
        setOriginal(original);
        numberOfEntities++;
        setEntityID(numberOfEntities);
        this.entitiesManagement = entitiesManagement;
        this.map = map;
    }

    public Entity(Entity other) {
        super(other.getPosition(), other.getWidth(), other.getHeight());
        setOriginal(other.getOriginal());
        setEntityID(other.getEntityID());
        setEntitiesManagement(other.getEntitiesManagement());
        setMap(other.getMap());

    }

    /**
     * set map.
     * @param map map
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * get map.
     * @return map
     */
    public Map getMap() {
        return map;
    }

    /**
     * set entities management.
     * @param entitiesManagement entities management
     */
    public void setEntitiesManagement(EntitiesManagement entitiesManagement) {
        this.entitiesManagement = entitiesManagement;
    }

    /**
     * get entities management.
     * @return entitiesManagement
     */
    public EntitiesManagement getEntitiesManagement() {
        return entitiesManagement;
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

    public void draw(Graphics2D g) {
        g.drawImage(
                getSprite().getImag(),
                getPosition().getX(),
                getPosition().getY(),
                getWidth(),
                getHeight(),
                null);
    }
}
