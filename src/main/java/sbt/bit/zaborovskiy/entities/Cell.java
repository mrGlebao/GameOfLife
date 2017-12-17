package sbt.bit.zaborovskiy.entities;

public class Cell implements Cloneable {

    // anchor to grid
    public int row;
    public int column;

    private boolean isAlive;

    public Cell(int row, int column, boolean isAlive) {
        this.row = row;
        this.column = column;
        this.isAlive = isAlive;
    }

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

    @Override
    public String toString() {
        return isAlive ? "1" : "0";
    }
}
