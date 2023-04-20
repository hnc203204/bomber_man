package SystemManagement;

import Atributes.Sprite;
import DataConfig.Configure;

import java.awt.*;

public class PauseMenu implements Configure {

    private static final int NUMBER_OF_BUTTONs = 2;
    private Sprite table;
    private Sprite[] menuButton;
    private Sprite[] continueButton;
    private int currentButton = 0;


    public PauseMenu() {
        table = new Sprite("/theend0.png");
        menuButton = new Sprite[]{
                new Sprite("/menu0.png"),
                new Sprite("/menu1.png")
        };
        continueButton = new Sprite[]{
                new Sprite("/tiep_tuc0.png"),
                new Sprite("/tiep_tuc1.png")
        };
    }

    public int getCurrentButton() {
        return currentButton;
    }

    public void setCurrentButton(int currentButton) {
        this.currentButton = currentButton;
    }

    public void upToButton() {
        setCurrentButton((getCurrentButton() - 1 + NUMBER_OF_BUTTONs) % NUMBER_OF_BUTTONs);
    }

    public void downToButton() {
        setCurrentButton((getCurrentButton() + 1 + NUMBER_OF_BUTTONs) % NUMBER_OF_BUTTONs);
    }

    public void draw(Graphics2D g) {
        g.drawImage(
                table.getImag(),
                6 * TILE_SIZE,
                4 * TILE_SIZE,
                PAUSE_MENU_WIDTH,
                PAUSE_MENU_HEIGHT,
                null
        );

        g.drawImage(
                continueButton[currentButton == 0 ? 1 : 0].getImag(),
                7 * TILE_SIZE,
                5 * TILE_SIZE,
                4 * TILE_SIZE,
                TILE_SIZE,
                null
        );
        g.drawImage(
                menuButton[currentButton == 0 ? 0 : 1].getImag(),
                7 * TILE_SIZE,
                7 * TILE_SIZE,
                4 * TILE_SIZE,
                TILE_SIZE,
                null
        );

    }
}
