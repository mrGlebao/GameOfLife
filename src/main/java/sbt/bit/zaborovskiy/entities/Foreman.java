package sbt.bit.zaborovskiy.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//ThreadPoolOwner
public class Foreman extends Thread {
    private final int iterations;
    public volatile boolean endedEverything;
    private volatile List<Cell> roadMap;
    private List<Worker> employees;
    private volatile List<Pair> availableWork;
    private volatile boolean isWorking;
    private ExecutorService exec = Executors.newCachedThreadPool();
    private Grid grid;
    private volatile int resultSize;

    public Foreman(Grid grid, int employeesSize, int iterations) {
        this.grid = grid;
        this.roadMap = new ArrayList<>();
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

        for (int i = 0; i < iterations; i++) {
            isWorking = true;
            resultSize = 0;
            System.out.println("New cycle");
            for (Worker work : employees) {
                System.out.println("New worker! " + work.position);
                new Thread(work).start();
            }
            int size = grid.size;
            availableWork = generatePairs(size);
            while (!availableWork.isEmpty() && resultSize != size * size) {
                System.out.println(resultSize + " RESULT");
                System.out.println(size * size + " SIZE");
            }

            System.out.println("end loop");

//            for (Worker work : employees) {
//                System.out.println("Interrupt");
//                work.interrupt();
//            }
            isWorking = false;
            updateGrid();

        }
//        exec.shutdown();
        endedEverything = true;
        //exec.shutdownNow();
    }

    private synchronized void updateGrid() {
        grid.setState(roadMap);
        System.out.println(grid);
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
