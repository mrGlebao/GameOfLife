package sbt.bit.zaborovskiy;

import java.util.List;

/**
 * Main class
 */
public class Main {
    public static void main(String[] args) {
        GameOfLife g = new GameOfLifeImpl();
        List<String> list = g.play("resources/input.txt");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
