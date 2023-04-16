package Object.Entity.Character;

import Atributes.Point;
import Atributes.Sprite;
import Atributes.Velocity;
import DataConfig.Map;
import Object.Entity.Entity;
import SystemManagement.EntitiesManagement;

public class Enemy extends Character {

    public static int numberOfEnemies = 0;

    public Enemy(Point position, int width, int height, Sprite original, Velocity velocity, Map map, EntitiesManagement entitiesManagement) {
        super(position, width, height, original, velocity, map, entitiesManagement);
        currentSprite = 0;
        charImage = new Sprite[] {
          new Sprite("/enemy0.png"),
          new Sprite("/enemy1.png"),
          new Sprite("/enemy2.png"),
          new Sprite("/enemy3.png")
        };
        numberOfEnemies++;

    }

    @Override
    public void setDead(boolean dead) {
        super.setDead(dead);
        if (dead == true) {
            numberOfEnemies--;
        }
    }

    @Override
    public Sprite getSprite() {
        return charImage[currentSprite / 6];
    }

    @Override
    public void update() {
        currentSprite = (currentSprite + 1) % 24;
        entitiesManagement.entitiesCollision();
    }
}
