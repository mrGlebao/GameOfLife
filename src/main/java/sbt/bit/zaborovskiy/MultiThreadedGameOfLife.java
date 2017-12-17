package sbt.bit.zaborovskiy;

import sbt.bit.zaborovskiy.entities.Foreman;
import sbt.bit.zaborovskiy.entities.Grid;

public class MultiThreadedGameOfLife extends AbstractGameOfLife {
    @Override
    public void prepareWorkers(Grid grid) {
//        Foreman foreman = new Foreman(grid, 2);
//        foreman.start();
    }

    @Override
    public Grid sumPhase(Grid grid) {
        Foreman foreman = new Foreman(grid, 2);
        foreman.start();
        while (foreman.isWorking) {
        }
        return grid;
    }
}
