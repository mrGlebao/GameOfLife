package sbt.bit.zaborovskiy.entities;

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
        while (foreman.isWorking() && !isInterrupted()) {
//            System.out.println("Worker " + position);
            Cell cell = pickCell(foreman);
            if (cell == null) {
                continue;
            }
            Shard shard = new Shard(cell, grid);
            boolean isAlive = shard.cellWillBeAlive();
            sendResultToForeman(new Cell(cell.row, cell.column, isAlive));
            if (shard.cellWillBeAlive()) {
                System.out.println("Will be alive");
            }
        }
    }

    private synchronized Cell pickCell(Foreman foreman) {
        Foreman.Pair pair = foreman.getPair();
        if (pair != null) {
            return grid.get(pair.row, pair.column);
        }
        return null;
    }


    private void sendResultToForeman(Cell result) {
        foreman.setResult(result);
    }
}
