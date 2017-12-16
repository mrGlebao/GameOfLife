package sbt.bit.zaborovskiy.entities;

import org.junit.jupiter.api.function.Executable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * A class to work with cells.
 */
public class Worker implements Runnable {

    private final Foreman foreman;
    private final Grid grid;

    private static int staticPosition = 0;

    private int position;
    {
        staticPosition = staticPosition + 1;
    }
    public Worker(Foreman foreman, Grid grid) {
        System.out.println("Creating worker on position "+staticPosition );
        this.foreman = foreman;
        this.grid = grid;
        this.position = staticPosition;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Worker " + position);
            Cell cell = pickCell(foreman);
            if (cell == null) {
                return;
            }
            Shard shard = new Shard(cell, grid);
            boolean isAlive = shard.cellWillBeAlive();
            sendResultToForeman(new Cell(cell.row, cell.column, isAlive));
            if (shard.cellWillBeAlive()) {
                System.out.println("Will be alive");
            }
        }
    }

    private Cell pickCell(Foreman foreman) {
        Foreman.Pair pair = foreman.getPair();
        if ( pair != null) {
            return grid.get(pair.row, pair.column);
        }
        return null;
    }


    private void sendResultToForeman(Cell result) {
        foreman.setResult(result);
    }
}
