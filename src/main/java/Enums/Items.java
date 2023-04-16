package Enums;

public enum Items {
    GRASS('.'),
    BRICK('$'),
    BOMB('*'),
    WALL('#');

    Items(char characterOnMap) {
        this.characterOnMap = characterOnMap;
    }

    private final char characterOnMap;

    /**
     * get character on map.
     * @return char
     */
    public char getCharacterOnMap() {
        return characterOnMap;
    }
}
