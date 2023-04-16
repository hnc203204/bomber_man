package Object.Entity.Item;

import Atributes.Point;
import Atributes.Sprite;
import DataConfig.Map;
import Object.Entity.Entity;
import Object.Explode;
import SystemManagement.EntitiesManagement;

import javax.swing.text.Position;

public class Bomb extends Entity {

    private static final int TIME_TO_DEAD = 48 * 4;

    private int timeDead;
    private Sprite[] bombAnimation;
    private int currentSprite;


    public Bomb(Point position, int width, int height, Sprite original, EntitiesManagement entitiesManagement, Map map) {
        super(position, width, height, original, entitiesManagement, map);
        timeDead = TIME_TO_DEAD;
        bombAnimation = new Sprite[] {
            new Sprite("/bomb0.png"),
            new Sprite("/bomb1.png"),
            new Sprite("/bomb2.png")
        };
        currentSprite = 0;
    }

    /**
     * set time dead.
     * @param timeDead int
     */
    public void setTimeDead(int timeDead) {
        if (timeDead == 0) {
            setDead(true);
            getEntitiesManagement().removeBomb(this);
            getEntitiesManagement().addExplode(explode());
        }
        this.timeDead = timeDead;
    }

    /**
     * get time dead.
     * @return int
     */
    public int getTimeDead() {
        return timeDead;
    }

    /**
     * decrease time dead.
     */
    public void decreaseTimeDead() {
        setTimeDead(getTimeDead() - 1);
    }

    @Override
    public Sprite getSprite() {
        if (currentSprite == -1) {
            return getOriginal();
        }
        return bombAnimation[currentSprite / 8];
    }

    public Explode explode() {
        Explode explode = new Explode(
                new Point(getPosition().getX(), getPosition().getY()),
                2,
                map,
                entitiesManagement
        );
        explode.setUp();
        return explode;
    }

    @Override
    public void run() {
        update();
    }

    public void update() {
        decreaseTimeDead();
        currentSprite = (currentSprite + 1) % 24;
    }
}
