package sbt.bit.zaborovskiy.entities;

import java.util.Arrays;
import java.util.Objects;

/**
 * 3x3 piece of grid. A cell with neighbors
 */
public class Shard {
    private Cell[][] subGrid;
    private Cell center;
    private Grid grid;

    public Shard(Cell center, Grid grid) {
        this.center = center;
        this.subGrid = generateSubGrid(grid);
    }

    // 0 1 2
    //0
    //1
    //2
    private Cell[][] generateSubGrid(Grid grid) {
        Cell[][] result = new Cell[3][3];
        result[0][0] = grid.get(center.row - 1, center.column - 1);
        result[1][1] = this.center;
        result[2][2] = grid.get(center.row + 1, center.column + 1);
        result[0][1] = grid.get(center.row - 1, center.column);
        result[1][0] = grid.get(center.row, center.column - 1);
        result[2][0] = grid.get(center.row + 1, center.column - 1);
        result[2][1] = grid.get(center.row + 1, center.column);
        result[1][2] = grid.get(center.row, center.column + 1);
        result[0][2] = grid.get(center.row - 1, center.column + 1);
        return result;
    }

    public boolean cellWillBeAlive() {
        long aliveNeighbors = Arrays.stream(subGrid)
                .map(elem -> Arrays.stream(elem)
                        .filter(Objects::nonNull)
                        .filter(Cell::isAlive)
                        .count())
                .reduce(0L, (a, b) -> a + b);
        if (center.isAlive()) {
            aliveNeighbors = aliveNeighbors - 1;
        }
        if (center.isAlive()) {
            return aliveNeighbors == 2 || aliveNeighbors == 3;
        } else if (!center.isAlive()) {
            return aliveNeighbors == 3;
        } else return false;
    }

}
