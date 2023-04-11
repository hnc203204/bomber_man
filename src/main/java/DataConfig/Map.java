package DataConfig;

import Atributes.Point;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Map implements Configure {

    char[][] map;

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
    }

    public char getGridAt(int indexI, int indexJ) {
        if (indexI >= 0 && indexI < ROW && indexJ >= 0 && indexJ < COLUMN)
            return map[indexI][indexJ];
        return '!';
    }


    public boolean validMove(int indexPixelX, int indexPixelY) {

        int indexILeft = indexPixelX / TILE_SIZE;
        int indexIRight = (indexPixelX + TILE_SIZE - 1) / TILE_SIZE;
        int indexJLeft = indexPixelY / TILE_SIZE;
        int indexJRight = (indexPixelY + TILE_SIZE - 1) / TILE_SIZE ;
        return (!isWall(indexILeft, indexJLeft)
                && !isWall(indexILeft, indexJRight)
                && !isWall(indexIRight, indexJLeft)
                && !isWall(indexIRight, indexJRight));
    }

    public ArrayList<Point> exactGrid(int indexPixelX, int indexPixelY) {
        int indexILeft = indexPixelX / TILE_SIZE;
        int indexIRight = (indexPixelX + TILE_SIZE - 1) / TILE_SIZE;
        int indexJLeft = indexPixelY / TILE_SIZE;
        int indexJRight = (indexPixelY + TILE_SIZE - 1) / TILE_SIZE;
        ArrayList<Point> res = new ArrayList<>();
        if (!isWall(indexILeft, indexJLeft)) {
            res.add(new Point(indexJLeft, indexILeft));
        }
        if (!!isWall(indexILeft, indexJRight)) {
            res.add(new Point(indexJRight, indexILeft));
        }
        if (!isWall(indexIRight, indexJLeft)) {
            res.add(new Point(indexJLeft, indexIRight));
        }
        if (!isWall(indexIRight, indexJRight)) {
            res.add(new Point(indexJRight, indexIRight));
        }
        return res;
    }

    private boolean isWall(int indexI, int indexJ) {
//        System.out.println(initMap[indexJ][indexI]);
        return (map[indexJ][indexI] == '#' || map[indexJ][indexI] == '@');
    }
}
