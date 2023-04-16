package Atributes;

import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.swing.*;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ResourceBundle;
import DataConfig.Configure;


public class Sprite implements Configure {


    /**
     * constructor 1.
     * @param pathImage pathImage
     */
    public Sprite(String pathImage) {
        this.pathImage = pathImage;
        try {
            this.imag = ImageIO.read(getClass().getResourceAsStream(pathImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * constructor 2.
     * @param oth other Sprite
     */
    public Sprite(Sprite oth) {
        this.pathImage = oth.getPathImage();
        this.imag = oth.getImag();
    }

    private final String pathImage;
    private BufferedImage imag = null;

    /**
     * getter pathImage.
     * @return pathImage.
     */
    public String getPathImage() {
        return pathImage;
    }

    /**
     * get image.
     * @return Buffered Image
     */
    public BufferedImage getImag() {
        return imag;
    }
}
