package Atributes;

public class Velocity extends Point {

    /**
     * constructor 1.
     */
    public Velocity() {
        super();
    }

    /**
     * constructor 2.
     * @param vectorX vector's x
     * @param vectorY vector's y
     */
    public Velocity(int vectorX, int vectorY) {
        super(vectorX, vectorY);
    }

    /**
     * constructor 3.
     * @param oth other velocity.
     */
    public Velocity(Velocity oth) {
        super(oth.getX(), oth.getY());
    }

    @Override
    public String toString() {
        return String.format("%s%s", getClass().getName(), super.toString());
    }
}
