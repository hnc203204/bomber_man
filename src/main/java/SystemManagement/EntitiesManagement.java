package SystemManagement;

import Atributes.Point;
import Atributes.Sprite;
import DataConfig.Configure;
import DataConfig.Map;
import Object.Entity.Character.Player;
import Object.Entity.Entity;
import Object.Entity.Item.Grass;
import Object.Entity.Item.Wall;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class EntitiesManagement implements Configure {

    private ArrayList<Entity> entities;
    private Map map;
    private InputStream fileInputStream;

    /**
     * constructor 1.
     */
    public EntitiesManagement() {
        entities = new ArrayList<>();
        try {
            fileInputStream = new FileInputStream(new File("src/main/resources/Map.txt"));
            map = new Map();
            map.readMap(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateMap() {
        for (int indexI = 0; indexI < ROW; ++indexI) {
            for (int indexJ = 0; indexJ < COLUMN; ++indexJ) {
                if (map.getGridAt(indexI, indexJ) == '#') {
                    addEntity(
                            new Wall(
                                    indexI * COLUMN + indexJ,
                                    new Point(indexJ * TILE_SIZE, indexI * TILE_SIZE),
                                    TILE_SIZE,
                                    TILE_SIZE,
                                    new Sprite("wall.png")
                            )
                    );
                } else if (map.getGridAt(indexI, indexJ) == '.') {
                    addEntity(
                            new Grass(
                                    indexI * COLUMN + indexJ,
                                    new Point(indexJ * TILE_SIZE, indexI * TILE_SIZE),
                                    TILE_SIZE,
                                    TILE_SIZE,
                                    new Sprite("Grass.png")
                            )
                    );
                }
            }
        }
    }

    /**
     * add entity.
     * @param entity entity
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * delete entity.
     * @param entity entity
     */
    public void deleteEntity(Entity entity) {
        for (int index = 0; index < entities.size(); ++index) {
            if (entity.equals(entities.get(index))) {
                entities.remove(index);
                break;
            }
        }
    }
}
