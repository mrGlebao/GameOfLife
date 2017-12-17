package sbt.bit.zaborovskiy.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    public static List<String> readFile(String fileName) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();

        Scanner scan = new Scanner(new File(fileName));
        while (scan.hasNextLine()) {
            lines.add(scan.nextLine());
        }
        scan.close();

        return lines;
    }

    public Integer getN(String s) {
        return Integer.valueOf(s.split(" ")[0]);
    }

    public Integer getM(String s) {
        return Integer.valueOf(s.split(" ")[1]);
    }

    public List<String> parseGrid(Grid grid) {
        List<String> result = new ArrayList<>();
        for(Cell[] cells: grid.state) {
            String row = "";
            for(Cell c: cells) {
                row+=c.toString();
            }
            result.add(row);
        }
        return result;
    }

}
