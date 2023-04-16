package Object.Entity.Character;

import Atributes.Point;
import Atributes.Sprite;
import Atributes.Velocity;
import DataConfig.Configure;
import DataConfig.Map;
import Enums.Move;
import Object.Entity.Entity;
import SystemManagement.EntitiesManagement;

public class Character extends Entity implements Configure {


    private Velocity velocity;
    private boolean goLeft = false;
    private boolean goDown = false;
    private boolean goUp = false;
    private boolean goRight = false;

    /**
     * TODO: dead animation.
     */

    protected int currentSprite;

    protected Sprite[] charImage = null;

    protected Sprite[] Left;

    protected Sprite[] Right;

    protected Sprite[] Up;

    protected Sprite[] Down;
    protected int timeImmortal;
    protected int numberOfLives;


    /**
     * constructor 1.
     * @param position position
     * @param width width
     * @param height height
     * @param original original sprite
     * @param velocity velocity
     * @param entitiesManagement entities management
     */
    public Character(Point position, int width, int height, Sprite original, Velocity velocity, Map map, EntitiesManagement entitiesManagement) {
        super(position, width, height, original, entitiesManagement, map);
        setVelocity(velocity);
        currentSprite = -1;
    }

    /**
     * constructor 2.
     * @param oth other
     */
    public Character(Character oth) {
        super(oth.getPosition(), oth.getWidth(), oth.getHeight(), oth.getSprite(), oth.getEntitiesManagement(), oth.getMap());
        setVelocity(new Velocity(oth.getVelocity().getX(), oth.getVelocity().getY()));
        currentSprite = -1;
    }



    /**
     * setter velocity.
     * @param velocity Entity's velocity
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    /**
     * getter velocity.
     * @return velocity
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * make movement per second.
     */
    public boolean makeMovementPerSecond(Velocity velocity, Map map) {
        if (map.validMove(
                getPosition().getX() + getVelocity().getX() * velocity.getX(),
                getPosition().getY() + getVelocity().getY() * velocity.getY(),
                this)
        ) {
            setPosition(new Point(
                            getPosition().getX() + getVelocity().getX() * velocity.getX(),
                            getPosition().getY() + getVelocity().getY() * velocity.getY()
                    )
            );
            return true;
        }
        return false;
    }

    /**
     * get map.
     * @return map
     */
    public Map getMap() {
        return map;
    }

    public boolean _makeMovementPerSecond(Velocity velocity, Map map) {
        if (map.validMove(
                getPosition().getX() + getVelocity().getX() * velocity.getX(),
                getPosition().getY() + getVelocity().getY() * velocity.getY(),
                this)
        ) {

            return true;
        }
        return false;
    }

    public void setGoDown(boolean goDown) {
        this.goDown = goDown;
    }

    public void setGoLeft(boolean goLeft) {
        this.goLeft = goLeft;
    }

    public void setGoRight(boolean goRight) {
        this.goRight = goRight;
    }

    public void setGoUp(boolean goUp) {
        this.goUp = goUp;
    }

    public void setTimeImmortal(int timeImmortal) {
        this.timeImmortal = timeImmortal;
    }

    public int getTimeImmortal() {
        return timeImmortal;
    }

    public void setNumberOfLives(int numberOfLives) {
        this.numberOfLives = numberOfLives;
    }

    public int getNumberOfLives() {
        return numberOfLives;
    }

    public void increaseNumberOfLives() {
        numberOfLives++;
    }

    public void decreaseNumberOfLives() {
        numberOfLives--;
        if (numberOfLives == 0) {
            setDead(true);
        }
    }

    @Override
    public Sprite getSprite() {
        if (charImage == null) {
            return getOriginal();
        }
        return charImage[currentSprite / 8];
    }

    @Override
    public void run() {
        update();
    }

    public void update() {
        if (goLeft) {
            if (charImage != Left) {
                currentSprite = 0;
            } else {
                currentSprite = (currentSprite + 1) % 24;
            }
            charImage = Left;
            makeMovementPerSecond(Move.LEFT.getDirection(), map);
        } else if (goRight) {
            if (charImage != Right) {
                currentSprite = 0;
            } else {
                currentSprite = (currentSprite + 1) % 24;
            }
            charImage = Right;
            makeMovementPerSecond(Move.RIGHT.getDirection(), map);
        } else if (goDown) {
            if (charImage != Down) {
                currentSprite = 0;
            } else {
                currentSprite = (currentSprite + 1) % 24;
            }
            charImage = Down;
            makeMovementPerSecond(Move.DOWN.getDirection(), map);
        } else if (goUp) {
            if (charImage != Up) {
                currentSprite = 0;
            } else {
                currentSprite = (currentSprite + 1) % 24;
            }
            charImage = Up;
            makeMovementPerSecond(Move.UP.getDirection(), map);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "%s[%s, %s]",
                getClass().getName(),
                super.toString(),
                getVelocity().toString()
        );
    }


}
