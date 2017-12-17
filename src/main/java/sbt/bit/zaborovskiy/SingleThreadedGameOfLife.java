package sbt.bit.zaborovskiy;

import sbt.bit.zaborovskiy.dto.Cell;
import sbt.bit.zaborovskiy.entities.Grid;
import sbt.bit.zaborovskiy.dto.Shard;

public class SingleThreadedGameOfLife extends AbstractGameOfLife{
    @Override
    public void prepareWorkers(Grid grid) {

    }

    @Override
    public Grid sumPhase(Grid grid) {
        Grid result = new Grid(grid.size);
        for(Cell[] row :grid.state) {
            for(Cell cell: row) {
                Shard shard = new Shard(cell, grid);
                result.state[cell.row][cell.column] = new Cell(cell.row, cell.column, shard.cellWillBeAlive());
            }
        }
        return result;
    }

}
