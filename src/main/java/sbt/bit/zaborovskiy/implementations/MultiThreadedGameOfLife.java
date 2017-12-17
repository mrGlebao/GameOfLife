package sbt.bit.zaborovskiy.implementations;

import sbt.bit.zaborovskiy.entities.Foreman;
import sbt.bit.zaborovskiy.entities.Grid;

public class MultiThreadedGameOfLife extends AbstractGameOfLife {
    @Override
    public void prepareWorkers(Grid grid) {

    }

    @Override
    public Grid sumPhase(Grid grid) {
        Foreman foreman = new Foreman(grid, 8);
        foreman.start();
        while (foreman.isWorking) {
        }

        return grid;
    }
}
