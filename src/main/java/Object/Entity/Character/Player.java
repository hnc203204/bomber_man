package Object.Entity.Character;

import Atributes.Point;
import Atributes.Sprite;
import Atributes.Velocity;
import DataConfig.Configure;
import DataConfig.Map;
import Enums.Items;
import Object.Entity.Item.Bomb;
import SystemManagement.EntitiesManagement;

public class Player extends Character implements Configure {

    private int numberOfBombs;
    private int currentBombs;
    private int timeResetCurrentBombs;

    public static int numberOfPlayers = 0;

    /**
     * constructor 1.
     * @param position position
     * @param width width
     * @param height height
     * @param orginal original sprite
     * @param velocity velocity
     * @param entitiesManagement entities management
     */
    public Player(Point position, int width, int height, Sprite orginal, Velocity velocity, Map map, EntitiesManagement entitiesManagement) {
        super(position, width, height, orginal, velocity, map, entitiesManagement);
        Left = entitiesManagement.getResourcesManagement().getMapResources().getLeft();
        Right = entitiesManagement.getResourcesManagement().getMapResources().getRight();
        Up = entitiesManagement.getResourcesManagement().getMapResources().getUp();
        Down = entitiesManagement.getResourcesManagement().getMapResources().getDown();
        numberOfPlayers++;
    }

    /**
     * constructor 2.
     * @param other other
     */
    public Player(Player other) {
        super(
                other.getPosition(),
                other.getWidth(),
                other.getHeight(),
                other.getSprite(),
                other.getVelocity(),
                other.getMap(),
                other.getEntitiesManagement()
        );
        Left = entitiesManagement.getResourcesManagement().getMapResources().getLeft();
        Right = entitiesManagement.getResourcesManagement().getMapResources().getRight();
        Up = entitiesManagement.getResourcesManagement().getMapResources().getUp();
        Down = entitiesManagement.getResourcesManagement().getMapResources().getDown();
        numberOfPlayers++;
    }

    /**
     * get number of bombs.
     * @return int
     */
    public int getNumberOfBombs() {
        return numberOfBombs;
    }

    /**
     * set number of bombs.
     * @param numberOfBombs number of bomb
     * @throws Exception invalid number
     */
    public void setNumberOfBombs(int numberOfBombs) throws Exception {
        if (numberOfBombs < 0) {
            throw new IllegalArgumentException("Invalid number of bombs!");
        }
        this.numberOfBombs = numberOfBombs;
    }

    /**
     * increase number of bombs.
     */
    public void increaseNumberOfBombs() {
        numberOfBombs++;
    }

    /**
     * set current bombs
     * @param currentBombs current bomb
     */
    public void setCurrentBombs(int currentBombs) throws Exception {
        if (currentBombs < 0) {
            throw new IllegalArgumentException("Invalid current bombs");
        }
        this.currentBombs = currentBombs;
    }

    public Bomb layingBomb(Map map) {
        Point grid = map.onGrid(getPosition().getX(), getPosition().getY(), this);
        return new Bomb(grid, TILE_SIZE, TILE_SIZE, new Sprite("/bomb0.png"), getEntitiesManagement(), map);
    }

    @Override
    public void setDead(boolean dead) {
        super.setDead(dead);
        if (dead == true) {
            numberOfPlayers--;
        }
    }

    /**
     * get current bombs.
     * @return int
     */
    public int getCurrentBombs() {
        return currentBombs;
    }

    /**
     * decrease current bombs.
     */
    public void decreaseCurrentBombs() {
        try {
            setCurrentBombs(getCurrentBombs() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update() {
        super.update();
        entitiesManagement.meetTheDoor();
    }

    /**
     * reset current bombs.
     */
    public void resetCurrentBombs() {
        try {
            setCurrentBombs(getNumberOfBombs());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
