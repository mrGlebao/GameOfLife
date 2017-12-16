package sbt.bit.zaborovskiy;

import sbt.bit.zaborovskiy.entities.Foreman;
import sbt.bit.zaborovskiy.entities.Grid;
import sbt.bit.zaborovskiy.entities.Parser;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Main class
 */
public class Main {
    public static void main(String[] args) {
        Parser p = new Parser();
        List<String> list = null;
        try {
            list = p.readFile("resources/input.txt");
            String line = list.remove(0);
            System.out.println(p.getN(line));
            System.out.println(p.getM(line));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        Grid grid = new Grid(list);
        System.out.println(list);
        Grid grid = new Grid(list);
//        grid.get(1, 0).setAlive(false);
//        grid.get(1, 1).setAlive(false);
//        grid.get(1, 2).setAlive(false);
//        grid.get(2, 0).setAlive(false);
//        grid.get(2, 1).setAlive(false);
//        grid.get(2, 2).setAlive(false);
        System.out.println(grid);
        Foreman foreman = new Foreman(grid, 8);
        foreman.start();
        System.out.println("Ready");

    }
}
