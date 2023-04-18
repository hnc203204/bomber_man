package SystemManagement;

import Atributes.Sprite;
import DataConfig.Configure;
import Panel.GamePlayPanel;

import java.awt.*;

public class GameTitleManagement implements Configure {


    private static GameTitleManagement gameTitleManagement;
    public static final int NUMBER_OF_BUTTONs = 2;
    private Sprite title;
    private Sprite[] startButton;
    private Sprite[] continueButton;
    private int currentButton;
//    private Sprite quitButton;

    private GameTitleManagement() {
        title = new Sprite("/title0.png");
        startButton = new Sprite[] {
                new Sprite("/start0.png"),
                new Sprite("/start1.png")
        };
        continueButton = new Sprite[] {
                new Sprite("/continue0.png"),
                new Sprite("/continue1.png")
        };
        currentButton = 0;

    }

    public static GameTitleManagement getInstance() {
        if (gameTitleManagement == null) {
            gameTitleManagement = new GameTitleManagement();
        }
        return gameTitleManagement;
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
        g.drawImage(title.getImag(), TILE_SIZE * LABEL_X, TILE_SIZE * LABEL_Y,
                TITLE_WIDTH, TITLE_HEIGHT, null);
        g.drawImage(startButton[((currentButton == 0) ? 1 : 0)].getImag(), TILE_SIZE * (LABEL_X + 2), TILE_SIZE * (LABEL_Y + 1) + TITLE_HEIGHT,
                START_BUTTON_WIDTH, START_BUTTON_HEIGHT, null);
        g.drawImage(continueButton[((currentButton == 1) ? 1 : 0)].getImag(), TILE_SIZE * (LABEL_X + 2), TILE_SIZE * (LABEL_Y + 2) + TITLE_HEIGHT,
                CONTINUE_BUTTON_WIDTH, CONTINUE_BUTTON_HEIGHT, null);

    }

}
