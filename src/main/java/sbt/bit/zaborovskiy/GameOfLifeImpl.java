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
            iterations = p.getM(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(list);
        Grid grid = new Grid(list);
        System.out.println(grid);
        Foreman foreman = new Foreman(grid, 2, iterations);
        foreman.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (foreman.isWorking()) {
        }

        System.out.println("Ready");

        return p.parseGrid(grid);
    }
}
