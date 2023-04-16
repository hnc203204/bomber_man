package SystemManagement;

import Atributes.Sprite;
import DataConfig.Configure;

import java.awt.*;

public class EndGameManagement implements Configure {


    public static final int NUMBER_OF_BUTTONs = 2;
    private int currentButton = 0;
    private Sprite table;
    private Sprite gameOver;
    private Sprite[] endTitle;
    private Sprite isContinue;
    private Sprite[] chooseButton;
    private Sprite nose;

    private boolean win;

    public EndGameManagement() {
        table = new Sprite("/theend0.png");
        endTitle = new Sprite[] {
                new Sprite("/gameover0.png"),
                new Sprite("/youwin0.png")
        };
        isContinue = new Sprite("/continueButton10.png");
        chooseButton = new Sprite[] {
                new Sprite("/yes0.png"),
                new Sprite("/no0.png")
        };
        nose = new Sprite("/chooseButton0.png");
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public void setCurrentButton(int currentButton) {
        this.currentButton = currentButton;
    }

    public int getCurrentButton() {
        return currentButton;
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
                LABEL_X * TILE_SIZE,
                LABEL_Y * TILE_SIZE,
                TITLE_WIDTH,
                TITLE_HEIGHT,
                null
        );
        g.drawImage(
                endTitle[((isWin()) ? 1 : 0)].getImag(),
                (LABEL_X + 2) * TILE_SIZE,
                (LABEL_Y + 1) * TILE_SIZE,
                END_TITLE_WIDTH,
                END_TITLE_HEIGHT,
                null
        );
        g.drawImage(isContinue.getImag(),
                (LABEL_X + 1) * TILE_SIZE,
                (LABEL_Y + 1) * TILE_SIZE + TITLE_HEIGHT,
                CONTINUE_BUTTON_WIDTH,
                CONTINUE_BUTTON_HEIGHT,
                null);
        g.drawImage(chooseButton[0].getImag(),
                (LABEL_X + 2) * TILE_SIZE,
                (LABEL_Y + 2) * TILE_SIZE + TITLE_HEIGHT,
                YES_TITLE_WIDTH,
                YES_TITLE_HEIGHT,
                null);
        g.drawImage(chooseButton[1].getImag(),
                (LABEL_X + 2) * TILE_SIZE,
                (LABEL_Y + 3) * TILE_SIZE + TITLE_HEIGHT,
                NO_TITLE_WIDTH,
                NO_TITLE_HEIGHT,
                null);
        g.drawImage(nose.getImag(),
                (LABEL_X + 1) * TILE_SIZE,
                (LABEL_Y + 2 + currentButton) * TILE_SIZE + TITLE_HEIGHT,
                POINT_WIDTH,
                POINT_HEIGHT
                ,null);
    }

}
