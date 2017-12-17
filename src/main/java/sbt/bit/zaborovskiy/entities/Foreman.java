package sbt.bit.zaborovskiy.entities;

import sbt.bit.zaborovskiy.dto.Cell;
import sbt.bit.zaborovskiy.dto.Grid;

import java.util.ArrayList;
import java.util.List;

//ThreadPoolOwner
public class Foreman extends Thread {
    public volatile boolean isWorking;
    private volatile List<Cell> roadMap;
    private List<Worker> employees;
    private volatile List<Integer> availableWork;
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

    public synchronized Cell[] getRow() {
        return (availableWork == null || availableWork.isEmpty())
                ? null
                : grid.state[availableWork.remove(0)];
    }

    public void run() {
        resultSize = 0;
        System.out.println("New cycle");
        for (Worker work : employees) {
            System.out.println("New worker! " + work.position);
            work.start();
        }
        int size = grid.size;
        availableWork = generateRows(size);
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

    private List<Integer> generateRows(int size) {
        List<Integer> rows = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            rows.add(i);
        }
        return rows;
    }

    public synchronized void setResult(Cell result) {
        roadMap.add(result);
        resultSize += 1;
    }

}
