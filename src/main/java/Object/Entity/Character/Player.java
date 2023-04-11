package Object.Entity.Character;

import Atributes.Point;
import Atributes.Sprite;
import Atributes.Velocity;
import DataConfig.Configure;

public class Player extends Character implements Configure {

    private int numberOfBombs;
    private int currentBombs;
    private int timeResetCurrentBombs;

    /**
     * constructor 1.
     * @param PlayerID  player ID
     * @param position position
     * @param width width
     * @param height height
     * @param orginal original sprite
     * @param velocity velocity
     */
    public Player(int PlayerID, Point position, int width, int height, Sprite orginal, Velocity velocity) {
        super(PlayerID, position, width, height, orginal, velocity);
    }

    /**
     * constructor 2.
     * @param other other
     */
    public Player(Player other) {
        super(
                other.getEntityID(),
                other.getPosition(),
                other.getWidth(),
                other.getHeight(),
                other.getSprite(),
                other.getVelocity()
        );
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
