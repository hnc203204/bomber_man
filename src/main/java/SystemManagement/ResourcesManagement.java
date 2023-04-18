package SystemManagement;

import Atributes.Sprite;
import DataConfig.Configure;
import DataConfig.MapResources;

import java.awt.*;

public class ResourcesManagement implements Configure {

    private static int NUMBER_OF_BUTTONs = 2;
    private MapResources[] mapResources;
    private int currentButton = 0;
    private Sprite nose;

    public ResourcesManagement() {
        mapResources = new MapResources[] {
                new MapResources(
                        new Sprite("/map_1.png"),
                        "src/main/resources/map_1_txt.txt",
                        new Sprite("/brick0.png"),
                        new Sprite[] {
                                new Sprite("/left_1_0.png"),
                                new Sprite("/left_1_1.png"),
                                new Sprite("/left_1_2.png")
                        },
                        new Sprite[] {
                                new Sprite("/right_1_0.png"),
                                new Sprite("/right_1_1.png"),
                                new Sprite("/right_1_2.png")
                        },
                        new Sprite[] {
                                new Sprite("/up_1_0.png"),
                                new Sprite("/up_1_1.png"),
                                new Sprite("/up_1_2.png")
                        },
                        new Sprite[] {
                                new Sprite("/down_1_0.png"),
                                new Sprite("/down_1_1.png"),
                                new Sprite("/down_1_2.png")
                        },
                        "Classical"

                ),
                new MapResources(
                        new Sprite("/map_2.png"),
                        "src/main/resources/map_2_txt.txt",
                        new Sprite("/brick1.png"),
                        new Sprite[] {
                                new Sprite("/left_2_0.png"),
                                new Sprite("/left_2_1.png"),
                                new Sprite("/left_2_2.png")
                        },
                        new Sprite[] {
                                new Sprite("/right_2_0.png"),
                                new Sprite("/right_2_1.png"),
                                new Sprite("/right_2_2.png")
                        },
                        new Sprite[] {
                                new Sprite("/up_2_0.png"),
                                new Sprite("/up_2_1.png"),
                                new Sprite("/up_2_2.png")
                        },
                        new Sprite[] {
                                new Sprite("/down_2_0.png"),
                                new Sprite("/down_2_1.png"),
                                new Sprite("/down_2_2.png")
                        },
                        "Kill all"
                )
        };
        nose = new Sprite("/chooseButton0.png");

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

    public MapResources getMapResources() {
        return mapResources[currentButton];
    }

    public void draw(Graphics2D g) {
        g.setFont(new Font("Chiller", Font.ITALIC, 24));
        g.setColor(Color.WHITE);
        g.drawString(mapResources[0].getNameMap(), LEFT_PAV, UP_PAV - 20);
        g.drawString(mapResources[1].getNameMap(), LEFT_PAV + MAP_WIDTH + 2 * TILE_SIZE, UP_PAV - 20);
        g.drawImage(nose.getImag(),
                LEFT_PAV - TILE_SIZE + (MAP_WIDTH + 2 * TILE_SIZE) * currentButton,
                UP_PAV - 40,
                POINT_WIDTH,
                POINT_HEIGHT
                ,null);
        g.drawImage(
                mapResources[0].getMapSprite().getImag(),
                LEFT_PAV,
                UP_PAV,
                MAP_WIDTH,
                MAP_HEIGHT,
                null
        );
        g.drawImage(
                mapResources[1].getMapSprite().getImag(),
                LEFT_PAV + MAP_WIDTH + 2 * TILE_SIZE,
                UP_PAV,
                MAP_WIDTH,
                MAP_HEIGHT,
                null
        );
    }
}
