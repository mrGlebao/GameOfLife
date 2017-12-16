package sbt.bit.zaborovskiy.entities;

import java.util.List;

/**
 * An infinite grid of cells
 */
public class Grid {

    public Cell[][] state;
    public int size;

    public Grid(int n) {
        state = new Cell[n][n];
        for(int i = 0; i< n; i++) {
            for(int j = 0; j< n; j++) {
                state[i][j] = new Cell(i, j, true);
            }
        }
        size = n;
    }

    public Grid(List<String> lines) {
        state = new Cell[lines.size()][lines.size()];
        size = lines.size();
        // row
        for(int i = 0; i< lines.size(); i ++) {
            // column
            String line = lines.get(i);
            for(int j = 0; j < line.length(); j++) {
                boolean isAlive = line.charAt(j) == '1';
                state[i][j] = new Cell(i, j, isAlive);
            }
        }
    }

    private int actualIndex(int position) {
        position = position % size; // crop
        return position == -1
                ? size - 1 // to the opposite side
                : position == size
                ? 0 // to the opposite side
                : position;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("<Grid("+size+")>\n");
        for(Cell[] cell: state) {
            for(Cell c: cell) {
                result.append(c);
            }
            result.append("\n");
        }
        return result.toString();
    }


    public Cell get(int row, int column) {
        return state[actualIndex(row)][actualIndex(column)];
    }

}
