package Enums;

import Atributes.Velocity;

import java.awt.event.KeyEvent;
import java.security.Key;


public enum Move {

    LEFT(KeyEvent.VK_LEFT, new Velocity(-1, 0)),
    RIGHT(KeyEvent.VK_RIGHT, new Velocity(1, 0)),
    UP(KeyEvent.VK_UP, new Velocity(0, -1)),
    DOWN(KeyEvent.VK_DOWN, new Velocity(0, 1));

    Move(int moveType, Velocity velocity) {
        this.moveType = moveType;
        this.direction = velocity;
    }
    private final int moveType;
    private final Velocity direction;

    /**
     * getter Velocity.
     * @return velocity
     */
    public Velocity getDirection() {
        return direction;
    }

    /**
     * get move type.
     * @return move type.
     */
    public int getMoveType() {
        return moveType;
    }
}
