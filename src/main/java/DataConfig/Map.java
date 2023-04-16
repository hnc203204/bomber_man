package DataConfig;

import Atributes.Point;
import Object.Entity.Character.Character;
import Object.Rectangle;
import ShortestPathAlgorithms.AllPairsShortestPath;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Map implements Configure {

    private char[][] map;

    private AllPairsShortestPath allPairsShortestPath;

    /**
     * read map.
     * @param file file
     */
    public void readMap(InputStream file) {
        Scanner inp = new Scanner(file);
        String line;
        int indexJ = 0;
        map = new char[ROW][COLUMN];
        while (inp.hasNext()) {
            line = inp.nextLine();
            for (int i = 0; i < line.length(); ++i) {
                map[indexJ][i] = line.charAt(i);
            }
            indexJ++;
        }

        allPairsShortestPath = new AllPairsShortestPath(this);
        allPairsShortestPath.update();
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
}
