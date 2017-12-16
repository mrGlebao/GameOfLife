package sbt.bit.zaborovskiy.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//ThreadPoolOwner
public class Foreman extends Thread {
    private Cell[][] roadMap;

    private List<Pair> availableWork;

    private ExecutorService exec = Executors.newCachedThreadPool();
    private Grid grid;

    public Foreman(Grid grid) {
        this.grid = grid;
        this.roadMap = new Cell[grid.size][grid.size];
    }

    public synchronized Pair getPair() {
        return availableWork.isEmpty() ? null : availableWork.remove(0);
    }

    public void run() {
        int size = this.roadMap.length;
        availableWork = generatePairs(size);
        System.out.println("New worker!");
        exec.execute(new Worker(this, grid));
        System.out.println("New worker!");
        exec.execute(new Worker(this, grid));
        System.out.println("New worker!");
        exec.execute(new Worker(this, grid));
        while(!availableWork.isEmpty()) {
//            System.out.println(availableWork.size());
        }
        exec.shutdown();
        updateGrid();

    }

    private void updateGrid() {
        grid.state = roadMap;
        System.out.println(grid);
        roadMap = new Cell[roadMap.length][roadMap.length];
    }

    private List<Pair> generatePairs(int size) {
        List<Pair> pairs = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j< size; j++) {
                pairs.add(new Pair(i, j));
            }
        }
        return pairs;
    }

    public void setResult(Cell result) {
        roadMap[result.row][result.column] = result;
    }

    public class Pair {
        public int row;
        public int column;
        public Pair(int row, int column){
            this.row = row;
            this.column = column;
        }
    }

}
