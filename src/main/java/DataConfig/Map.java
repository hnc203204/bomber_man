package DataConfig;

import Atributes.Point;
import Atributes.Sprite;
import Enums.Items;
import Object.Entity.Character.Character;
import Object.Rectangle;
import ShortestPathAlgorithms.AllPairsShortestPath;

import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.RandomAccess;
import java.util.Scanner;

public class Map implements Configure {

    private char[][] originalMap;
    private char[][] map;

    private AllPairsShortestPath allPairsShortestPath;
    private Sprite mapSprite;

    /**
     * read map.
     * @param file file
     */
    public void readMap(InputStream file, Sprite mapSprite) {
        Scanner inp = new Scanner(file);
        String line;
        int indexJ = 0;
        map = new char[ROW][COLUMN];
        originalMap = new char[ROW][COLUMN];
        while (inp.hasNext()) {
            line = inp.nextLine();
            for (int i = 0; i < line.length(); ++i) {
                map[indexJ][i] = line.charAt(i);
            }
            indexJ++;
        }
        for (int X = 0; X < ROW; ++X) {
            for (int Y = 0; Y < COLUMN; ++Y) {
                originalMap[X][Y] =
                        (map[X][Y] == '$' ? '.' : map[X][Y]);
            }
        }
        this.mapSprite = mapSprite;
        allPairsShortestPath = new AllPairsShortestPath(this);
        allPairsShortestPath.update();
    }

    public void generateDoor() {
        for (int X = 9; X < ROW - 1; ++X) {
            for (int Y = 3; Y < COLUMN - 2; ++Y) {
                if (getGridAt(X, Y) == Items.BRICK.getCharacterOnMap()) {
                    Random random = new Random();
                    int isChoose = random.nextInt(2);
                    if (isChoose == 1) {
                        originalMap[X][Y] = 'C';
                        return;
                    }
                }
            }
        }
    }

    public AllPairsShortestPath getAllPairsShortestPath() {
        return allPairsShortestPath;
    }

    public void setAllPairsShortestPath(AllPairsShortestPath allPairsShortestPath) {
        this.allPairsShortestPath = allPairsShortestPath;
    }

    public char getGridAt(int indexI, int indexJ) {
        if (indexI >= 0 && indexI < ROW && indexJ >= 0 && indexJ < COLUMN) {
            return map[indexI][indexJ];
        }
        return '!';
    }

    public void setGridAt(int indexI, int indexJ, char value) {
        if (indexI >= 0 && indexI < ROW && indexJ >= 0 && indexJ < COLUMN) {
            map[indexI][indexJ] = value;
//            allPairsShortestPath.update();
        }
    }

    public void restoreOriginal(int indexI, int indexJ) {
        if (indexI >= 0 && indexI < ROW && indexJ >= 0 && indexJ < COLUMN) {
            map[indexI][indexJ] = originalMap[indexI][indexJ];
            allPairsShortestPath.update();
        }
    }



    public boolean validMove(int indexPixelX, int indexPixelY, Character character) {

        int indexILeft = indexPixelX / TILE_SIZE;
        int indexIRight = (indexPixelX + character.getWidth() - 1) / TILE_SIZE;
        int indexJLeft = indexPixelY / TILE_SIZE;
        int indexJRight = (indexPixelY + character.getHeight() - 1) / TILE_SIZE ;
        return (!isWall(indexILeft, indexJLeft)
                && !isWall(indexILeft, indexJRight)
                && !isWall(indexIRight, indexJLeft)
                && !isWall(indexIRight, indexJRight));
    }

    public Point onGrid(int indexPixelX, int indexPixelY, Character character) {
        ArrayList<Point> relateGrids = exactGrid(indexPixelX, indexPixelY, character);
        int answer = 0;
        Point exactlyGrid = null;
        for (int index = 0; index < relateGrids.size(); ++index) {
            int area = character.getCollisionArea(new Rectangle(relateGrids.get(index), TILE_SIZE, TILE_SIZE));
            if (area > answer) {
                answer = area;
                exactlyGrid = relateGrids.get(index);
            }
        }
        return exactlyGrid;
    }

    public ArrayList<Point> exactGrid(int indexPixelX, int indexPixelY, Character character) {
        int indexILeft = indexPixelX / TILE_SIZE;
        int indexIRight = (indexPixelX + character.getWidth() - 1) / TILE_SIZE;
        int indexJUp = indexPixelY / TILE_SIZE;
        int indexJDown = (indexPixelY + character.getHeight() - 1) / TILE_SIZE;
        ArrayList<Point> res = new ArrayList<>();
        if (!isWall(indexILeft, indexJUp)) {
            res.add(new Point(indexILeft * TILE_SIZE, indexJUp * TILE_SIZE));
        }
        if (!!isWall(indexILeft, indexJDown)) {
            res.add(new Point(indexILeft * TILE_SIZE, indexJDown * TILE_SIZE));
        }
        if (!isWall(indexIRight, indexJUp)) {
            res.add(new Point(indexIRight * TILE_SIZE, indexJUp * TILE_SIZE));
        }
        if (!isWall(indexIRight, indexJDown)) {
            res.add(new Point(indexILeft * TILE_SIZE, indexJDown * TILE_SIZE));
        }
        return res;
    }

    private boolean isWall(int indexI, int indexJ) {
//        System.out.println(initMap[indexJ][indexI]);
        return (map[indexJ][indexI] == '#' || map[indexJ][indexI] == '$' || map[indexJ][indexI] == '*');
    }

    public void draw(Graphics2D g) {
        g.drawImage(
                mapSprite.getImag(),
                0,
                0,
                SCREEN_HORIZONTAL,
                SCREEN_VERTICAL,
                null
        );
    }
}
