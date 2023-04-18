package SystemManagement;

import Atributes.Point;
import Atributes.Sprite;
import Atributes.Velocity;
import Bot.EnemyBot;
import DataConfig.Configure;
import DataConfig.Map;
import DataConfig.MapResources;
import Enums.GameState;
import Enums.Items;
import Enums.TypeMap;
import Object.Entity.Character.Enemy;
import Object.Entity.Character.Player;
import Object.Entity.Entity;
import Object.Entity.Item.Bomb;
import Object.Entity.Item.Brick;
import Object.Entity.Item.Grass;
import Object.Entity.Item.Wall;
import Object.Explode;
import Panel.GamePlayPanel;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EntitiesManagement implements Configure {

    private static EntitiesManagement entitiesManagement;
    private ArrayList<Entity> entities;
    private Map map;
    private InputStream fileInputStream;
    private Player player;
    private LinkedList<Bomb> bombs;
    private LinkedList<Explode> explodes;
    private ArrayList<EnemyBot> enemyBots;
    private GamePlayPanel gamePlayPanel;
    private ResourcesManagement resourcesManagement;

    private Sprite gate = new Sprite("/gate0.png");

    /**
     * constructor 1.
     */
    private EntitiesManagement(GamePlayPanel gamePlayPanel) {
        resourcesManagement = new ResourcesManagement();
        MapResources mapResources = resourcesManagement.getMapResources();
        entities = new ArrayList<>();
        try {
            fileInputStream = new FileInputStream(new File(mapResources.getMapFile()));
            map = new Map();
            map.readMap(fileInputStream, resourcesManagement.getMapResources().getMapSprite());
        } catch (IOException e) {
            e.printStackTrace();
        }
        player = new Player(
                new Point(2 * TILE_SIZE, TILE_SIZE),
                CHARACTER_WIDTH,
                CHARACTER_HEIGHT,
                new Sprite(mapResources.getRight()[0]),
                DEFAULT_VELOCITY,
                map,
                this
        );
        entities.add(new Enemy(
                new Point(TILE_SIZE * (COLUMN - 3), TILE_SIZE),
                ENEMY_WIDTH,
                ENEMY_HEIGHT,
                new Sprite("/enemy0.png"),
                DEFAULT_VELOCITY,
                map,
                this
        ));
        entities.add(new Enemy(
                new Point(TILE_SIZE * (COLUMN - 3), TILE_SIZE * (ROW - 2)),
                ENEMY_WIDTH,
                ENEMY_HEIGHT,
                new Sprite("/enemy0.png"),
                DEFAULT_VELOCITY,
                map,
                this
        ));
        entities.add(new Enemy(
                new Point(2 * TILE_SIZE, TILE_SIZE * (ROW - 2)),
                ENEMY_WIDTH,
                ENEMY_HEIGHT,
                new Sprite("/enemy0.png"),
                DEFAULT_VELOCITY,
                map,
                this
        ));
        enemyBots = new ArrayList<>();
        bombs = new LinkedList<>();
        explodes = new LinkedList<>();
        this.gamePlayPanel = gamePlayPanel;
    }

    public static EntitiesManagement getInstance(GamePlayPanel gamePlayPanel) {
        if (entitiesManagement == null) {
            entitiesManagement = new EntitiesManagement(gamePlayPanel);
        }
        return entitiesManagement;
    }

    public ResourcesManagement getResourcesManagement() {
        return resourcesManagement;
    }

    public void setResourcesManagement(ResourcesManagement resourcesManagement) {
        this.resourcesManagement = resourcesManagement;
    }

    public void reset() {
        entities = new ArrayList<>();
        try {
            fileInputStream = new FileInputStream(new File(resourcesManagement.getMapResources().getMapFile()));
            map = new Map();
            map.readMap(fileInputStream, resourcesManagement.getMapResources().getMapSprite());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (resourcesManagement.getMapResources().getNameMap().equals(TypeMap.CLASSICAL.getTypeMap())) {
            map.generateDoor();
        }
        Player.numberOfPlayers = 0;
        Enemy.numberOfEnemies = 0;
        player = new Player(
                new Point(2 * TILE_SIZE, TILE_SIZE),
                CHARACTER_WIDTH,
                CHARACTER_HEIGHT,
                new Sprite(resourcesManagement.getMapResources().getRight()[0]),
                DEFAULT_VELOCITY,
                map,
                this
        );
        entities.add(new Enemy(
                new Point(TILE_SIZE * (COLUMN - 3), TILE_SIZE),
                ENEMY_WIDTH,
                ENEMY_HEIGHT,
                new Sprite("/enemy0.png"),
                DEFAULT_VELOCITY,
                map,
                this
        ));
        entities.add(new Enemy(
                new Point(TILE_SIZE * (COLUMN - 3), TILE_SIZE * (ROW - 2)),
                ENEMY_WIDTH,
                ENEMY_HEIGHT,
                new Sprite("/enemy0.png"),
                DEFAULT_VELOCITY,
                map,
                this
        ));
        entities.add(new Enemy(
                new Point(2 * TILE_SIZE, TILE_SIZE * (ROW - 2)),
                ENEMY_WIDTH,
                ENEMY_HEIGHT,
                new Sprite("/enemy0.png"),
                DEFAULT_VELOCITY,
                map,
                this
        ));
        enemyBots = new ArrayList<>();
        bombs = new LinkedList<>();
        explodes = new LinkedList<>();
        updateEnemyBot();
    }

    public void updateEnemyBot() {
        for (int index = 0; index < entities.size(); ++index) {
            enemyBots.add(
                    new EnemyBot(
                            player,
                            (Enemy) entities.get(index),
                            map,
                            map.getAllPairsShortestPath()
                    )
            );
        }
    }

    public void addExplode(Explode explode) {
        explodes.add(explode);
    }

    public void removeExplode() {
        explodes.remove(0);
    }

    public void addBomb(Bomb bomb) {
        bombs.add(bomb);
    }

    public void removeBomb(Bomb bomb) {
        for (int index = 0; index < bombs.size(); ++index) {
            if (bomb.equals(bombs.get(index))) {
                bombs.remove(index);
                break;
            }
        }
    }

    public void playerLayingBomb() {
        addBomb(player.layingBomb(map));
    }

    public void execute() {
        ExecutorService executors = Executors.newCachedThreadPool();
        if (gamePlayPanel.getGameState() == GameState.PLAY) {
            if (!player.isDead()) executors.execute(player);
            for (int index = 0; index < entities.size(); ++index) {
                if (!entities.get(index).isDead()) {
                    executors.execute(entities.get(index));
                }
            }

            for (int index = 0; index < bombs.size(); ++index) {
                executors.execute(bombs.get(index));
            }

            for (int index = 0; index < explodes.size(); ++index) {
                executors.execute(explodes.get(index));
            }

            for (int index = 0; index < enemyBots.size(); ++index) {
                executors.execute(enemyBots.get(index));
            }
        }
        executors.shutdown();
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

    public void drawEntities(Graphics2D g) {
        map.draw(g);
        for (int X = 0; X < ROW; ++X) {
            for (int Y = 0; Y < COLUMN; ++Y) {
                if (map.getGridAt(X, Y) == Items.BRICK.getCharacterOnMap()) {
                    g.drawImage(
                            resourcesManagement.getMapResources().getBrick().getImag(),
                            Y * TILE_SIZE,
                            X * TILE_SIZE,
                            TILE_SIZE,
                            TILE_SIZE,
                            null
                    );
                } else if (map.getGridAt(X, Y) == 'C') {
                    g.drawImage(
                            gate.getImag(),
                            Y * TILE_SIZE,
                            X * TILE_SIZE,
                            TILE_SIZE,
                            TILE_SIZE,
                            null
                    );
                }
            }
        }
        for (int index = 0; index < explodes.size(); ++index) {
            explodes.get(index).drawEntities(g);
        }
        for (int index = 0; index < entities.size(); ++index) {
            if (!entities.get(index).isDead()) {
                entities.get(index).draw(g);
            }
        }
        for (int index = 0; index < bombs.size(); ++index) {
            bombs.get(index).draw(g);
        }
        if (!player.isDead()) player.draw(g);
    }

    public void setPlayerGoLeft(boolean value) {
        player.setGoLeft(value);
    }

    public void setPlayerGoRight(boolean value) {
        player.setGoRight(value);
    }

    public void setPlayerGoUp(boolean value) {
        player.setGoUp(value);
    }

    public void setPlayerGoDown(boolean value) {
        player.setGoDown(value);
    }

    public void meetTheDoor() {
        if (!player.isDead()) {
            int X = player.getPosition().getY() / TILE_SIZE;
            int Y = player.getPosition().getX() / TILE_SIZE;
            if (map.getGridAt(X, Y) == 'C') {
                gamePlayPanel.setGameState(GameState.END, true);
            }
        }
    }

    public void revire(Enemy enemy) {
        Point newPoint = new Point();
        Random random = new Random();
        int choose = random.nextInt(4);
        switch (choose) {
            case 0:
                newPoint = TOP_LEFT;
                break;
            case 1:
                newPoint = TOP_RIGHT;
                break;
            case 2:
                newPoint = UNDER_LEFT;
                break;
            case 3:
                newPoint = UNDER_RIGHT;
                break;
        }
        enemy.setPosition(newPoint);
        enemy.setDead(false);
    }

    public void explodeCollision() {
        if (!player.isDead() && explodes.peekFirst().isCollision(player)) {
            player.setDead(true);
        }
        for (int index = 0; index < entities.size(); ++index) {
            if (!entities.get(index).isDead() && explodes.peekFirst().isCollision(entities.get(index))) {
                entities.get(index).setDead(true);
                if (resourcesManagement.getMapResources().getNameMap().equals(TypeMap.CLASSICAL.getTypeMap())) {
                    revire((Enemy) entities.get(index));
                }
            }
        }
        for (int index = 0; index < entities.size(); ++index) {
            if (!entities.get(index).isDead() && explodes.peekFirst().isCollision(entities.get(index))) {
                entities.get(index).setDead(true);
            }
        }
        if (Enemy.numberOfEnemies <= 0
                && resourcesManagement.getMapResources().getNameMap().equals(TypeMap.KILL_ALL.getTypeMap())) {
            gamePlayPanel.setGameState(GameState.END, true);
        }
    }

    public void entitiesCollision() {
        for (int index = 0; index < entities.size(); ++index) {
            if (!entities.get(index).isDead() && player.isCollision(entities.get(index))) {
                player.setDead(true);
            }
        }
        System.out.println(Player.numberOfPlayers);
        if (Player.numberOfPlayers <= 0) {
            gamePlayPanel.setGameState(GameState.END, false);
        }
    }
}
