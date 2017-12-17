package sbt.bit.zaborovskiy.entities;

import sbt.bit.zaborovskiy.dto.Cell;
import sbt.bit.zaborovskiy.dto.Shard;

/**
 * A class to work with cells.
 */
public class Worker extends Thread {

    private static int staticPosition = 0;
    private final Foreman foreman;
    private final Grid grid;
    public int position;

    {
        staticPosition = staticPosition + 1;
    }

    public Worker(Foreman foreman, Grid grid) {
        System.out.println("Creating worker on position " + staticPosition);
        this.foreman = foreman;
        this.grid = grid;
        this.position = staticPosition;
    }

    @Override
    public void run() {
        while (!isInterrupted() && !foreman.isInterrupted()) {
            Cell[] gridRow = pickRow();
            if (gridRow != null) {
                for (Cell cell : gridRow) {
                    processCell(cell);
                }
            }
        }
    }

    private void processCell(Cell cell) {
        if (cell == null) {
            return;
        }
        Shard shard = new Shard(cell, grid);
        boolean isAlive = shard.cellWillBeAlive();
        sendResultToForeman(new Cell(cell.row, cell.column, isAlive));
    }

    private synchronized Cell[] pickRow() {
        return foreman.getRow();
    }


    private void sendResultToForeman(Cell result) {
        foreman.setResult(result);
    }

}
