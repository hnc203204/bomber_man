package ShortestPathAlgorithms;

import Atributes.Point;
import Atributes.Velocity;
import DataConfig.Configure;
import DataConfig.Map;
import Enums.Items;
import Enums.Move;

public class AllPairsShortestPath implements Configure {

    private static final int INF = 100000;
    private static final int ALL_NODES = ROW * COLUMN;
    private int[][] distance;
    private Map map;

    /**
     * constructor 1.
     */
    public AllPairsShortestPath(Map map) {
        distance = new int[ALL_NODES][ALL_NODES];
        this.map = map;
    }

    public void initial() {
        for (int node_u = 0; node_u < ALL_NODES; ++node_u) {
            for (int node_v = 0; node_v < ALL_NODES; ++node_v) {
                Point point = getPoint(node_u);
                if (node_u == node_v &&
                        map.getGridAt(point.getX(), point.getY()) == Items.GRASS.getCharacterOnMap()) {
                    distance[node_u][node_v] = 0;
                } else {
                    distance[node_u][node_v] = INF;
                }
            }
        }
        for (int node_u = 0; node_u < ALL_NODES; ++node_u) {
            Point point = getPoint(node_u);
            if (map.getGridAt(point.getX(), point.getY()) == Items.GRASS.getCharacterOnMap()) {
                for (Move x : Move.values()) {
                    Velocity v = x.getDirection();
                    if (
                            map.getGridAt(
                                    point.getX() + v.getX(),
                                    point.getY() + v.getY()
                            ) == Items.GRASS.getCharacterOnMap()) {
                        distance[node_u][getIndex(point.getX() + v.getX(), point.getY() + v.getY())]
                                = 1;
                    }
                }
            }
        }
        calculate();
    }

    public void calculate() {
        for (int k_node = 0; k_node < ALL_NODES; ++k_node) {
            for (int v_node = 0; v_node < ALL_NODES; ++v_node) {
                for (int u_node = 0; u_node < ALL_NODES; ++u_node) {
                    distance[u_node][v_node] =
                            Math.min(distance[u_node][k_node] + distance[k_node][v_node],
                                    distance[u_node][v_node]);
                }
            }
        }
    }

    public void update() {
        initial();
    }


    public boolean isTheShortestPath(Point u, Point k, Point v) {
        return distance[getIndex(u)][getIndex(v)] ==
                distance[getIndex(u)][getIndex(k)] + distance[getIndex(k)][getIndex(v)];
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public int getIndex(int x, int y) {
        return x * COLUMN + y;
    }

    public int getIndex(Point point) {
        return point.getX() * COLUMN + point.getY();
    }

    public Point getPoint(int index) {
        return new Point(index / COLUMN, index % COLUMN);
    }
}
