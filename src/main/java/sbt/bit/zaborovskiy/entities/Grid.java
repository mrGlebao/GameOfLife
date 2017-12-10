package sbt.bit.zaborovskiy.entities;

/** An infinite grid of cells
 *
 */
public class Grid {

    private Cell[][] state;

    public Grid(int n) {
        state = new Cell[n][n];
    }

}
