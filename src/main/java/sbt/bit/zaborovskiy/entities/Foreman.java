package sbt.bit.zaborovskiy.entities;

//ThreadPoolOwner
public class Foreman {
    private boolean[][] roadMap;

    public Foreman(int n) {
        this.roadMap = new boolean[n][n];
    }

}
