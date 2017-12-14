package sbt.bit.zaborovskiy.entities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * A class to work with cells.
 */
public class Worker implements Runnable {

    private final Grid grid;

    public Worker(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void run() {
        Cell cell = pickCell(grid);
        Shard shard = new Shard(cell, grid);
        if (shard.cellWillBeAlive()) {
            System.out.println("Will be alive");
        }
    }

    private Cell pickCell(Grid grid) {
        throw new NotImplementedException();
    }
}
