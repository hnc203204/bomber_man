package Panel;

import Atributes.Sprite;
import Atributes.Velocity;
import DataConfig.Configure;
import Enums.GameState;
import Object.Entity.Character.Player;
import SystemManagement.EndGameManagement;
import SystemManagement.EntitiesManagement;
import Atributes.Point;
import SystemManagement.GameTitleManagement;
import SystemManagement.PauseMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GamePlayPanel extends JPanel implements Runnable, KeyListener, Configure {

    //TODO: Fix Map
    //TODO: Scores.
    //TODO: Status.
    //TODO: Lives.
    //TODO: More map.
    //TODO: Keep track.
    //TODO: Audio.
    //TODO: Dead animation.



    EntitiesManagement entitiesManagement;
    GameTitleManagement gameTitleManagement;
    GameState gameState;
    EndGameManagement endGameManagement;
    PauseMenu pauseMenu;

    public GamePlayPanel() {
//        this.keyListener = keyListener;

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(SCREEN_HORIZONTAL, SCREEN_VERTICAL));
        setDoubleBuffered(true);
        addKeyListener(this);
        setFocusable(true);
        gameState = GameState.GAMETITLE;
        entitiesManagement = EntitiesManagement.getInstance(this);
        entitiesManagement.updateEnemyBot();
        gameTitleManagement = GameTitleManagement.getInstance();
        endGameManagement = EndGameManagement.getInstance();
        pauseMenu = new PauseMenu();
    }

    public void setGameState(GameState gameState, boolean win) {
        endGameManagement.setWin(win);
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    Thread gameThread;

    public void gameStart() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {


        System.out.println(System.nanoTime());

        double drawInterval = 1000000000.0 / FPS;


        double nextDrawTime = System.nanoTime() + drawInterval;

        ExecutorService executorService = Executors.newCachedThreadPool();


        while (gameThread != null) {
            entitiesManagement.execute();

            repaint();

            try {
                double remaindingTime = nextDrawTime - System.nanoTime();
                if (remaindingTime < 0) {
                    remaindingTime = 0;
                }
                remaindingTime /= 1000000.0;
                Thread.sleep((long)(remaindingTime));
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        switch (gameState) {
            case GAMETITLE:
                gameTitleManagement.draw(g2);
                break;

            case PAUSE:
                entitiesManagement.drawEntities(g2);
                pauseMenu.draw(g2);
                break;

            case PLAY:
                entitiesManagement.drawEntities(g2);
                break;

            case END:
                endGameManagement.draw(g2);
                break;
            case MAP_MENU:
                entitiesManagement.getResourcesManagement().draw(g2);
                break;
        }


//        System.out.println();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (gameState) {
            case PLAY:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        entitiesManagement.setPlayerGoLeft(true);
                        break;
                    case KeyEvent.VK_UP:
                        entitiesManagement.setPlayerGoUp(true);
                        break;
                    case KeyEvent.VK_DOWN:
                        entitiesManagement.setPlayerGoDown(true);
                        break;
                    case KeyEvent.VK_RIGHT:
                        entitiesManagement.setPlayerGoRight(true);
                        break;
                    case KeyEvent.VK_SPACE: {
                        entitiesManagement.playerLayingBomb();
                        break;
                    }
                    case KeyEvent.VK_ESCAPE:
                        gameState = GameState.PAUSE;
                        break;
                }
                break;
            case GAMETITLE:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        gameTitleManagement.upToButton();
                        break;
                    case KeyEvent.VK_DOWN:
                        gameTitleManagement.downToButton();
                        break;
                    case KeyEvent.VK_ENTER:
                        switch (gameTitleManagement.getCurrentButton())
                        {
                            case 0:
                                gameState = GameState.MAP_MENU;
                                break;
                            case 1:
                                gameState = GameState.PLAY;
                                break;

                        }
                        break;
                }
                break;
            case END:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        endGameManagement.upToButton();
                        break;
                    case KeyEvent.VK_DOWN:
                        endGameManagement.downToButton();
                        break;
                    case KeyEvent.VK_ENTER:
                        if (endGameManagement.getCurrentButton() == 0) {
                            entitiesManagement.reset();
                            gameState = GameState.PLAY;
                        } else {
                            entitiesManagement.reset();
                            gameState = GameState.GAMETITLE;
                        }
                        break;
                }
                break;
            case MAP_MENU:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        entitiesManagement.getResourcesManagement().upToButton();
                        break;
                    case KeyEvent.VK_RIGHT:
                        entitiesManagement.getResourcesManagement().downToButton();
                        break;
                    case KeyEvent.VK_ENTER: {
                        entitiesManagement.reset();
                        gameState = GameState.PLAY;
                        break;
                    }
                }
                break;
            case PAUSE:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        pauseMenu.upToButton();
                        break;
                    case KeyEvent.VK_DOWN:
                        pauseMenu.upToButton();
                        break;
                    case KeyEvent.VK_ENTER:
                        switch (pauseMenu.getCurrentButton()) {
                            case 0:
                                gameState = GameState.PLAY;
                                break;
                            case 1:
                                gameState = GameState.GAMETITLE;
                                break;
                        }
                        break;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                entitiesManagement.setPlayerGoLeft(false);
                break;
            case KeyEvent.VK_UP:
                entitiesManagement.setPlayerGoUp(false);
                break;
            case KeyEvent.VK_DOWN:
                entitiesManagement.setPlayerGoDown(false);
                break;
            case KeyEvent.VK_RIGHT:
                entitiesManagement.setPlayerGoRight(false);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
