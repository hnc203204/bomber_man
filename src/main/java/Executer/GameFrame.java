package Executer;

import Panel.GamePlayPanel;

import javax.swing.*;

public class GameFrame extends JFrame {

    GamePlayPanel gamePanel;

    //TODO: Game menu

    public GameFrame() {
        super("Bomberman");
        gamePanel = new GamePlayPanel();

        add(gamePanel);
        gamePanel.gameStart();
    }


}
