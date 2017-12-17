package sbt.bit.zaborovskiy;

import sbt.bit.zaborovskiy.implementations.GameOfLife;
import sbt.bit.zaborovskiy.implementations.SingleThreadedGameOfLife;

import java.util.List;

/**
 * Main class
 */
public class Main {
    public static void main(String[] args) {
        GameOfLife g = new SingleThreadedGameOfLife();
        List<String> list = g.play("resources/input.txt");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
