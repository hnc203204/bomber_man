package Atributes;

public class Point {
    private int x;
    private int y;

    /**
     * Constructor 1.
     */
    public Point() {

    }

    /**
     * Constructor 2.
     * @param x Ox axis
     * @param y Oy axis
     */
    public Point(int x, int y) {
        try {
            setX(x);
            setY(y);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor 3.
     * @param oth oth point
     */
    public Point(Point oth) {
        try {
            setX(oth.getX());
            setY(oth.getY());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * getter x.
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * setter y.
     * @param x Ox axis
     */
    public void setX(int x) throws Exception {
        if (x < 0) {
            throw new IllegalArgumentException("Invalid x!");
        }
        this.x = x;
    }

    /**
     * getter y.
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * setter y.
     * @param y Oy axis
     */
    public void setY(int y) throws Exception {
        if (y < 0) {
            throw new IllegalArgumentException("Invalid y!");
        }
        this.y = y;
    }

    /**
     * return info.
     * @return String
     */
    public String toString() {
        return String.format("[%d %d]", getX(), getY());
    }
}
