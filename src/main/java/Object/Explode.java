package Object;

import Atributes.Point;
import Atributes.Sprite;
import DataConfig.Configure;
import DataConfig.Map;
import Enums.Items;
import Object.Entity.Entity;
import Object.Entity.Item.Brick;
import SystemManagement.EntitiesManagement;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Explode implements Runnable, Configure {

    private final static int TIME_DEAD = 30;
    private final static Sprite centerExplode = new Sprite("/center.png");
    private final static Sprite leftExplode = new Sprite("/left.png");
    private final static Sprite rightExplode = new Sprite("/right.png");
    private final static Sprite upExplode = new Sprite("/up.png");
    private final static Sprite downExplode = new Sprite("/down.png");
    private final static Sprite leftHeadExplode = new Sprite("/leftTail.png");
    private final static Sprite rightHeadExplode = new Sprite("/rightTail.png");
    private final static Sprite upHeadExplode = new Sprite("/upTail.png");
    private final static Sprite downHeadExplode = new Sprite("/downTail.png");
    private int strength = 2;
    private Point position;
    private ArrayList<Entity> explodeGrid;
    private Map map;
    private EntitiesManagement entitiesManagement;
    private int timeDead;

    /**
     * constructor 1.
     * @param position position.
     * @param strength strength.
     */
    public Explode(Point position, int strength, Map map, EntitiesManagement entitiesManagement) {
        setPosition(position);
        setEntitiesManagement(entitiesManagement);
        setMap(map);
        try {
            setStrength(strength);
        } catch (Exception e) {
            e.printStackTrace();
        }
        explodeGrid = new ArrayList<>();
        explodeGrid.add(
                new Brick(
                        new Point(position.getX(), position.getY()),
                        TILE_SIZE,
                        TILE_SIZE,
                        centerExplode,
                        entitiesManagement,
                        map
                )
        );
        timeDead = TIME_DEAD;
    }

    public void setUp() {
        getGridToTheLeft();
        getGridToTheDownsize();
        getGridToTheRight();
        getGridToTheUpside();
    }

    public void getGridToTheLeft() {
        Point currentPosition = new Point(
                getPosition().getX() - TILE_SIZE,
                getPosition().getY()
        );
        int lengthExplode = strength;
        while (lengthExplode > 0) {
            char value = map.getGridAt(currentPosition.getY() / TILE_SIZE, currentPosition.getX() / TILE_SIZE);
            if (value == Items.BRICK.getCharacterOnMap()) {
                map.setGridAt(
                        currentPosition.getY() / TILE_SIZE,
                        currentPosition.getX() / TILE_SIZE,
                        Items.GRASS.getCharacterOnMap()
                );
                explodeGrid.add(new Brick(
                                        new Point(
                                                currentPosition.getX(),
                                                currentPosition.getY()
                                        ),
                                        TILE_SIZE,
                                        TILE_SIZE,
                                        leftHeadExplode,
                                        entitiesManagement,
                                        getMap()
                                )
                );
                break;
            }
            if (value == Items.GRASS.getCharacterOnMap()) {
                explodeGrid.add(
                        (lengthExplode == 1) ?
                                (new Brick(
                                        new Point(
                                                currentPosition.getX(),
                                                currentPosition.getY()
                                        ),
                                        TILE_SIZE,
                                        TILE_SIZE,
                                        leftHeadExplode,
                                        entitiesManagement,
                                        getMap()
                                )) :
                                new Brick(
                                        new Point(
                                                currentPosition.getX(),
                                                currentPosition.getY()
                                        ),
                                        TILE_SIZE,
                                        TILE_SIZE,
                                        leftExplode,
                                        entitiesManagement,
                                        getMap()
                                )
                );
            }
            if (value == Items.WALL.getCharacterOnMap()) {
                break;
            }
            currentPosition.setX(currentPosition.getX() - TILE_SIZE);
            lengthExplode--;
        }
    }

    public void setTimeDead(int timeDead) {
        if (timeDead == 0) {
            entitiesManagement.removeExplode();
        }
        this.timeDead = timeDead;
    }


    public int getTimeDead() {
        return timeDead;
    }

    public void getGridToTheRight() {
        Point currentPosition = new Point(
                getPosition().getX() + TILE_SIZE,
                getPosition().getY()
        );
        int lengthExplode = strength;
        while (lengthExplode > 0) {
            char value = map.getGridAt(currentPosition.getY() / TILE_SIZE, currentPosition.getX() / TILE_SIZE);
            if (value == Items.BRICK.getCharacterOnMap()) {
                map.setGridAt(
                        currentPosition.getY() / TILE_SIZE,
                        currentPosition.getX() / TILE_SIZE,
                        Items.GRASS.getCharacterOnMap()
                );
                explodeGrid.add(new Brick(
                                        new Point(
                                                currentPosition.getX(),
                                                currentPosition.getY()
                                        ),
                                        TILE_SIZE,
                                        TILE_SIZE,
                                        rightHeadExplode,
                                        entitiesManagement,
                                        getMap()
                                )
                );
                break;
            }
            if (value == Items.GRASS.getCharacterOnMap()) {
                explodeGrid.add(
                        (lengthExplode == 1) ?
                                (new Brick(
                                        new Point(
                                                currentPosition.getX(),
                                                currentPosition.getY()
                                        ),
                                        TILE_SIZE,
                                        TILE_SIZE,
                                        rightHeadExplode,
                                        entitiesManagement,
                                        getMap()
                                )) :
                                new Brick(
                                        new Point(
                                                currentPosition.getX(),
                                                currentPosition.getY()
                                        ),
                                        TILE_SIZE,
                                        TILE_SIZE,
                                        rightExplode,
                                        entitiesManagement,
                                        getMap()
                                )
                );
            }
            if (value == Items.WALL.getCharacterOnMap()) {
                break;
            }
            currentPosition.setX(currentPosition.getX() + TILE_SIZE);
            lengthExplode--;
        }
    }

    public void getGridToTheUpside() {
        Point currentPosition = new Point(
                getPosition().getX(),
                getPosition().getY() - TILE_SIZE
        );
        int lengthExplode = strength;
        while (lengthExplode > 0) {
            char value = map.getGridAt(currentPosition.getY() / TILE_SIZE, currentPosition.getX() / TILE_SIZE);
            if (value == Items.BRICK.getCharacterOnMap()) {
                map.setGridAt(
                        currentPosition.getY() / TILE_SIZE,
                        currentPosition.getX() / TILE_SIZE,
                        Items.GRASS.getCharacterOnMap()
                );
                explodeGrid.add(
                        new Brick(
                                        new Point(
                                                currentPosition.getX(),
                                                currentPosition.getY()
                                        ),
                                        TILE_SIZE,
                                        TILE_SIZE,
                                        upHeadExplode,
                                        entitiesManagement,
                                        getMap()
                                )
                );
                break;
            }
            if (value == Items.GRASS.getCharacterOnMap()) {
                explodeGrid.add(
                        (lengthExplode == 1) ?
                                (new Brick(
                                        new Point(
                                                currentPosition.getX(),
                                                currentPosition.getY()
                                        ),
                                        TILE_SIZE,
                                        TILE_SIZE,
                                        upHeadExplode,
                                        entitiesManagement,
                                        getMap()
                                )) :
                                new Brick(
                                        new Point(
                                                currentPosition.getX(),
                                                currentPosition.getY()
                                        ),
                                        TILE_SIZE,
                                        TILE_SIZE,
                                        upExplode,
                                        entitiesManagement,
                                        getMap()
                                )
                );
            }
            if (value == Items.WALL.getCharacterOnMap()) {
                break;
            }
            currentPosition.setY(currentPosition.getY() - TILE_SIZE);
            lengthExplode--;
        }
    }

    public void getGridToTheDownsize() {
        Point currentPosition = new Point(
                getPosition().getX(),
                getPosition().getY() + TILE_SIZE
        );
        int lengthExplode = strength;
        while (lengthExplode > 0) {
            char value = map.getGridAt(currentPosition.getY() / TILE_SIZE, currentPosition.getX() / TILE_SIZE);
            System.out.println(value);
            if (value == Items.BRICK.getCharacterOnMap()) {
                map.setGridAt(
                        currentPosition.getY() / TILE_SIZE,
                        currentPosition.getX() / TILE_SIZE,
                        Items.GRASS.getCharacterOnMap()
                );
                explodeGrid.add(
                        new Brick(
                                        new Point(
                                                currentPosition.getX(),
                                                currentPosition.getY()
                                        ),
                                        TILE_SIZE,
                                        TILE_SIZE,
                                        downHeadExplode,
                                        entitiesManagement,
                                        getMap()
                                )
                );
                break;
            }
            if (value == Items.GRASS.getCharacterOnMap()) {
                explodeGrid.add(
                        ((lengthExplode == 1) ?
                                (new Brick(
                                        new Point(
                                                currentPosition.getX(),
                                                currentPosition.getY()
                                        ),
                                        TILE_SIZE,
                                        TILE_SIZE,
                                        downHeadExplode,
                                        entitiesManagement,
                                        getMap()
                                )) :
                                (
                                        new Brick(
                                            new Point(
                                                    currentPosition.getX(),
                                                    currentPosition.getY()
                                            ),
                                            TILE_SIZE,
                                            TILE_SIZE,
                                            downExplode,
                                            entitiesManagement,
                                            getMap()
                                        )
                                )
                        )
                );
            }
            if (value == Items.WALL.getCharacterOnMap()) {
                break;
            }
            currentPosition.setY(currentPosition.getY() + TILE_SIZE);
            lengthExplode--;
        }
    }

    public void setEntitiesManagement(EntitiesManagement entitiesManagement) {
        this.entitiesManagement = entitiesManagement;
    }

    public EntitiesManagement getEntitiesManagement() {
        return entitiesManagement;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Map getMap() {
        return map;
    }

    /**
     * set position.
     * @param position position
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * get position.
     * @return Point
     */
    public Point getPosition() {
        return position;
    }

    /**
     * get strength.
     * @return int
     */
    public int getStrength() {
        return strength;
    }

    /**
     * set strength.
     * @param strength strength
     */
    public void setStrength(int strength) throws Exception {
        if (strength < 0) {
            throw new IllegalArgumentException("Invalid strength!");
        }
        this.strength = strength;
    }

    @Override
    public void run() {
        update();
    }

    public void decreaseTimeDead() {
        setTimeDead(getTimeDead() - 1);
    }

    private void update() {
        entitiesManagement.explodeCollision();
        decreaseTimeDead();
    }

    public void drawEntities(Graphics2D g) {
        for (int index = 0; index < explodeGrid.size(); ++index) {
            explodeGrid.get(index).draw(g);
        }
    }

    public boolean isCollision(Entity e) {
        for (int index = 0; index < explodeGrid.size(); ++index) {
            if (e.isCollision(explodeGrid.get(index))) {
                return true;
            }
        }
        return false;
    }
}
