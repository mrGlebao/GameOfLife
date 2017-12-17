package sbt.bit.zaborovskiy.entities;

import sbt.bit.zaborovskiy.dto.Cell;

import java.util.ArrayList;
import java.util.List;

//ThreadPoolOwner
public class Foreman extends Thread {
    public volatile boolean isWorking;
    private volatile List<Cell> roadMap;
    private List<Worker> employees;
    private volatile List<Pair> availableWork;
    private volatile Grid grid;
    private volatile int resultSize;

    public Foreman(Grid grid, int employeesSize) {
        this.grid = grid;
        this.roadMap = new ArrayList<>();
        employees = new ArrayList<>();
        for (int i = 0; i < employeesSize; i++) {
            employees.add(new Worker(this, grid));
        }
    }

    @Override
    public synchronized void start() {
        isWorking = true;
        super.start();
    }

    public synchronized Pair getPair() {
        return (availableWork == null || availableWork.isEmpty())
                ? null
                : availableWork.remove(0);
    }

    public void run() {
        resultSize = 0;
        System.out.println("New cycle");
        for (Worker work : employees) {
            System.out.println("New worker! " + work.position);
            work.start();
        }
        int size = grid.size;
        availableWork = generatePairs(size);
        while (resultSize != size * size) {
        }
        for (Thread work : employees) {
            System.out.println("Interrupt! ");
            work.interrupt();
        }
        updateGrid();
        isWorking = false;
    }

    private synchronized void updateGrid() {
        grid.setState(roadMap);
//        System.out.println(grid);
        roadMap = new ArrayList<>();
    }

    private List<Pair> generatePairs(int size) {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                pairs.add(new Pair(i, j));
            }
        }
        return pairs;
    }

    public synchronized void setResult(Cell result) {
        roadMap.add(result);
        resultSize += 1;
    }


    public class Pair {
        public int row;
        public int column;

        public Pair(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

}
