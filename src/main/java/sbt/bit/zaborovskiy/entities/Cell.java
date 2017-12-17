package sbt.bit.zaborovskiy.entities;

public class Cell {

    // anchor to grid
    public int row;
    public int column;

    private boolean isAlive;

    public Cell(int row, int column, boolean isAlive) {
        this.row = row;
        this.column = column;
        this.isAlive = isAlive;
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
