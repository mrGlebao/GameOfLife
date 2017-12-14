package sbt.bit.zaborovskiy.entities;

public class Cell implements Cloneable {

    // anchor to grid
    public int row;
    public int column;

    private boolean isAlive;

    @Override
    public Cell clone() {
        try {
            Cell result  = (Cell)super.clone();
            result.setAlive(this.isAlive());
            return result;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new AssertionError("Can't create cell clone");
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
