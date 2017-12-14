package sbt.bit.zaborovskiy.entities;

/**
 * An infinite grid of cells
 */
public class Grid {

    private Cell[][] state;
    private int size;

    public Grid(int n) {
        state = new Cell[n][n];
        size = n;
    }

    private int actualIndex(int position) {
        position = position % size; // crop
        return position == -1
                ? size - 1 // to the opposite side
                : position == size
                ? 0 // to the opposite side
                : position;
    }

    public Cell get(int row, int column) {
        return state[actualIndex(row)][actualIndex(column)];
    }

}
