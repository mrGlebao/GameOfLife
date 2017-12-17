package sbt.bit.zaborovskiy.implementations;

import sbt.bit.zaborovskiy.entities.Foreman;
import sbt.bit.zaborovskiy.dto.Grid;
import sbt.bit.zaborovskiy.settings.Config;

public class MultiThreadedGameOfLife extends AbstractGameOfLife {
    @Override
    public void prepareWorkers(Grid grid) {

    }

    @Override
    public Grid sumPhase(Grid grid) {
        Foreman foreman = new Foreman(grid, Config.THREAD_NUMBER);
        foreman.start();
        while (foreman.isWorking) {
        }

        return grid;
    }
}
