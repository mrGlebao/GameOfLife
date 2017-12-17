package sbt.bit.zaborovskiy.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//ThreadPoolOwner
public class Foreman extends Thread {
    private final int iterations;
    private volatile Cell[][] roadMap;
    private List<Worker> employees;
    private volatile List<Pair> availableWork;

    private volatile boolean isWorking;

    private ExecutorService exec = Executors.newCachedThreadPool();
    private Grid grid;

    public Foreman(Grid grid, int employeesSize, int iterations) {
        this.grid = grid;
        this.roadMap = new Cell[grid.size][grid.size];
        this.iterations = iterations;
        employees = new ArrayList<>();
        for (int i = 0; i < employeesSize; i++) {
            employees.add(new Worker(this, grid));
        }
    }

    public synchronized Pair getPair() {
        return (availableWork == null || availableWork.isEmpty())
                ? null
                : availableWork.remove(0);
    }

    public void run() {
        isWorking = true;
//        availableWork = generatePairs(grid.size);
        for (Worker work : employees) {
            System.out.println("New worker! " + work.position);
//            exec.execute(work);
            work.start();
        }
        for (int i = 0; i < iterations; i++) {
            System.out.println("New cycle");
            int size = grid.size;
            availableWork = generatePairs(size);
            while (!availableWork.isEmpty()) {
//                System.out.println(availableWork.size());
            }
            System.out.println("end loop");

            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateGrid();

        }
        exec.shutdown();
        isWorking = false;
        //exec.shutdownNow();
    }

    private void updateGrid() {
        grid.state = roadMap;
        System.out.println(grid);
        roadMap = new Cell[roadMap.length][roadMap.length];
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

    public void setResult(Cell result) {
        roadMap[result.row][result.column] = result;
    }

    public boolean hasWork() {
        return availableWork != null && !availableWork.isEmpty();
    }

    public synchronized boolean isWorking() {
        return isWorking;
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
