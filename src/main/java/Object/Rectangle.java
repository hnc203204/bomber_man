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

    /**
     * is collision
     * @param other other
     * @return boolean
     */
    public boolean isCollision(Rectangle other) {
        int max_left = Math.max(
                getPosition().getX(),
                other.getPosition().getX()
        );
        int min_right = Math.min(
                getPosition().getX() + getWidth(),
                other.getPosition().getX() + other.getWidth()
        );
        int max_up = Math.max(
                getPosition().getY(),
                other.getPosition().getY()
        );
        int min_down = Math.min(
                getPosition().getY() + getHeight(),
                other.getPosition().getY() + other.getHeight()
        );
        return (min_right - max_left > 0 && min_down - max_up > 0);
    }

    public int getCollisionArea(Rectangle other) {
        int max_left = Math.max(
                getPosition().getX(),
                other.getPosition().getX()
        );
        int min_right = Math.min(
                getPosition().getX() + getWidth(),
                other.getPosition().getX() + other.getWidth()
        );
        int max_up = Math.max(
                getPosition().getY(),
                other.getPosition().getY()
        );
        int min_down = Math.min(
                getPosition().getY() + getHeight(),
                other.getPosition().getY() + other.getHeight()
        );
        return (min_right - max_left) * (min_down - max_up);
    }
}
