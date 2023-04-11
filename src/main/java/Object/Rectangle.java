package Object;

import Atributes.Point;
import DataConfig.Configure;

public class Rectangle implements Configure {
    protected Point position;
    protected int width;
    protected int height;

    /**
     * constructor 1.
     */
    Rectangle() {

    }

    /**
     * constructor 2.
     * @param position position
     * @param width width
     * @param height height
     */
    public Rectangle(Point position, int width, int height) {
        try {
            setPosition(position);
            setWidth(width);
            setHeight(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Rectangle(Rectangle other) {
        try {
            setPosition(other.getPosition());
            setWidth(other.width);
            setHeight(other.height);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * set height.
     * @param height height
     */
    public void setHeight(int height) throws Exception {
        if (height < 0) {
            throw new IllegalArgumentException("Invalid height!");
        }
        this.height = height;
    }

    /**
     * get height.
     * @return int
     */
    public int getHeight() {
        return height;
    }

    /**
     * set position.
     * @param position Point
     */
    public void setPosition(Point position) {
        this.position = new Point(position.getX(), position.getY());
    }

    /**
     * get position.
     * @return point
     */
    public Point getPosition() {
        return position;
    }

    /**
     * set width.
     * @param width width
     * @throws Exception illegal
     */
    public void setWidth(int width) throws Exception {
        if (width < 0) {
            throw new IllegalArgumentException("Invalid width!");
        }
        this.width = width;
    }

    /**
     * get width.
     * @return int
     */
    public int getWidth() {
        return width;
    }
}
