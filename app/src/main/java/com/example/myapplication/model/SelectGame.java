package com.example.myapplication.model;

public class SelectGame {
    private int r;
    private int c;
    private int m;

    private static SelectGame instance;

    private SelectGame() {
        this.r = 0;
        this.c = 0;
        this.m = 0;
    }

    public static SelectGame getInstance(){
        if(instance==null){
            instance = new SelectGame();
        }
        return instance;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }
}
