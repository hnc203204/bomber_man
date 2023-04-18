package DataConfig;

import Atributes.Sprite;

public class MapResources {

    private Sprite mapSprite;
    private String mapFile;
    private Sprite brick;
    private Sprite[] Left;
    private Sprite[] Right;
    private Sprite[] Up;
    private Sprite[] Down;
    private String nameMap;

    public MapResources(
            Sprite mapSprite,
            String mapFile,
            Sprite brick,
            Sprite[] Left,
            Sprite[] Right,
            Sprite[] Up,
            Sprite[] Down,
            String nameMap) {
        this.mapSprite = mapSprite;
        this.mapFile = mapFile;
        this.Left = Left;
        this.Right = Right;
        this.Up = Up;
        this.Down = Down;
        this.nameMap = nameMap;
        this.brick = brick;

    }


    public Sprite getBrick() {
        return brick;
    }

    public Sprite getMapSprite() {
        return mapSprite;
    }

    public Sprite[] getDown() {
        return Down;
    }

    public Sprite[] getLeft() {
        return Left;
    }

    public Sprite[] getRight() {
        return Right;
    }

    public Sprite[] getUp() {
        return Up;
    }

    public String getMapFile() {
        return mapFile;
    }

    public String getNameMap() {
        return nameMap;
    }


}
