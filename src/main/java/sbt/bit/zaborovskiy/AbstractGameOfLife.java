package sbt.bit.zaborovskiy;

import sbt.bit.zaborovskiy.entities.Grid;
import sbt.bit.zaborovskiy.parser.Parser;

import java.io.FileNotFoundException;
import java.util.List;

public abstract class AbstractGameOfLife implements GameOfLife {
    @Override
    public List<String> play(String inputFile) {
        List<String> list = readFile(inputFile);
        int iterations = getIterationsNumber(list);
//        int size = getSize(list);
        Grid grid = createGrid(list);
        prepareWorkers(grid);
        for (int i = 0; i < iterations; i++) {
            processGrid(grid);
        }
        List<String> result = convertGridToResult(grid);
        return result;
    }

    public abstract void prepareWorkers(Grid grid);

    private List<String> convertGridToResult(Grid grid) {
        return Parser.parseGrid(grid);
    }

    private void processGrid(Grid grid) {
        Grid newGrid = sumPhase(grid);
        updatePhase(grid, newGrid);
    }

    public abstract Grid sumPhase(Grid grid);

    public void updatePhase(Grid oldGrid, Grid newGrid) {
        oldGrid.state = newGrid.state;
    }

    private Grid createGrid(List<String> list) {
        list.remove(0);
        return new Grid(list);
    }

    private int getSize(List<String> list) {
        if (list != null && !list.isEmpty()) {
            return Parser.getSize(list.get(0));
        }
        return 0;
    }


    private int getIterationsNumber(List<String> list) {
        if (list != null && !list.isEmpty()) {
            return Parser.getIterations(list.get(0));
        }
        return 0;
    }

    private List<String> readFile(String inputFile) {
        try {
            return Parser.readFile(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
