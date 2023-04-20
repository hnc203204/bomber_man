package Bot;

import Atributes.Point;
import Atributes.Velocity;
import DataConfig.Configure;
import DataConfig.Map;
import Enums.Move;
import Object.Entity.Character.Enemy;
import Object.Entity.Character.Player;
import ShortestPathAlgorithms.AllPairsShortestPath;

import java.util.ArrayList;

public class EnemyBot implements Runnable, Configure {
    private Player player;
    private Enemy enemy;

    private AllPairsShortestPath allPairsShortestPath;

    public EnemyBot(Player player, Enemy enemy, AllPairsShortestPath allPairsShortestPath) {
        this.player = player;
        this.enemy = enemy;
        this.allPairsShortestPath = allPairsShortestPath;
    }

    public AllPairsShortestPath getAllPairsShortestPath() {
        return allPairsShortestPath;
    }

    public void setAllPairsShortestPath(AllPairsShortestPath allPairsShortestPath) {
        this.allPairsShortestPath = allPairsShortestPath;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Velocity getDirection() {


        ArrayList<Point> playerPositions
                = player.getMap().exactGrid(player.getPosition().getX(), player.getPosition().getY(), player);
        ArrayList<Point> enemyPositions
                = enemy.getMap().exactGrid(enemy.getPosition().getX(), enemy.getPosition().getY(), enemy);

        for (Point enemyPoint : enemyPositions) {
            for (Point playerPoint : playerPositions) {
                Point enemyPostion =
                        new Point(enemyPoint.getY() / TILE_SIZE,
                            enemyPoint.getX() / TILE_SIZE);
                Point playerPosition =
                        new Point(playerPoint.getY() / TILE_SIZE,
                                playerPoint.getX() / TILE_SIZE);
                for (Move x : Move.values()) {
                    Point nextPoint = new Point(
                            enemyPostion.getX() + x.getDirection().getY(),
                            enemyPostion.getY() + x.getDirection().getX()
                    );
                    if (allPairsShortestPath.isTheShortestPath(
                            enemyPostion,
                            nextPoint,
                            playerPosition) &&
                    enemy._makeMovementPerSecond(x.getDirection(), enemy.getMap())) {
                        return x.getDirection();
                    }
                }
            }
        }

        return new Velocity(0, 0);


    }

    public void update() {
        if (getDirection() != null){
            enemy.makeMovementPerSecond(getDirection(), enemy.getMap());
        }
    }
    @Override
    public void run() {
        update();
    }
}
