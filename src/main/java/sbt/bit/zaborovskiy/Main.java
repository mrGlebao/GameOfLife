package sbt.bit.zaborovskiy;

import sbt.bit.zaborovskiy.entities.Foreman;
import sbt.bit.zaborovskiy.entities.Grid;

/**
 * Main class
 */
public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid(3);

        Foreman foreman = new Foreman(grid);
        foreman.start();
    }
}
