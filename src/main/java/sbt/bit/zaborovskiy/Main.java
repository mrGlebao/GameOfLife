package sbt.bit.zaborovskiy;

import sbt.bit.zaborovskiy.entities.Foreman;
import sbt.bit.zaborovskiy.entities.Grid;

/**
 * Main class
 */
public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid(3);

        Foreman foreman = new Foreman(grid, 2);
        foreman.start();
//        try {
//            Thread.sleep(5_000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("Ready");
//        foreman.interrupt();
    }
}
