package DataConfig;

public interface Configure {
    final int SCALE = 2;

    // SCREEN SETTING

    final int ORIGINAL_TILE = 26;
    final int TILE_SIZE = ORIGINAL_TILE * SCALE;
    final int COLUMN = 19;
    final int ROW = 17;
    final int SCREEN_HORIZONTAL = COLUMN * TILE_SIZE;
    final int SCREEN_VERTICAL = ROW * TILE_SIZE;

    // CHARACTER SETTING
    final int ORIGINAL_CHARACTER_WIDTH = 15;
    final int ORIGINAL_CHARACTER_HEIGHT = 24;
    final int CHARACTER_WIDTH = ORIGINAL_CHARACTER_WIDTH * SCALE;
    final int CHARACTER_HEIGHT = ORIGINAL_CHARACTER_HEIGHT * SCALE;
}
