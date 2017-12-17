package sbt.bit.zaborovskiy;

import sbt.bit.zaborovskiy.entities.Foreman;
import sbt.bit.zaborovskiy.entities.Grid;
import sbt.bit.zaborovskiy.entities.Parser;

import java.io.FileNotFoundException;
import java.util.List;

public class GameOfLifeImpl implements GameOfLife {
    @Override
    public List<String> play(String inputFile) {
        Parser p = new Parser();
        List<String> list = null;
        int iterations = 0;
        try {
            list = Parser.readFile(inputFile);
            String line = list.remove(0);
            iterations = Parser.getIterations(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(list);
        Grid grid = new Grid(list);
        System.out.println(grid);
        Foreman foreman = new Foreman(grid, 2);
        foreman.start();

        while (!foreman.endedEverything) {
        }

        System.out.println("Ready");

        return p.parseGrid(grid);
    }
}
