package com.example.myapplication.model;

import java.util.*;
public class Mine {
    private int co_x;
    private int co_y;

    private int hint;

    private int isClicked;

    private boolean isMIne;

    public int getHint() {
        return hint;
    }

    public void setHint(int hint) {
        this.hint = hint;
    }

    public int getIsClicked() {
        return isClicked;
    }

    public void setIsClicked(int isClicked) {
        this.isClicked = isClicked;
    }



    public Mine(int co_x, int co_y) {
        this.co_x = co_x;
        this.co_y = co_y;
        this.isMIne = false;
        this.hint = 0;
        this.isClicked = 0;
    }

    public int getCo_x() {
        return co_x;
    }

    public void setCo_x(int co_x) {
        this.co_x = co_x;
    }

    public int getCo_y() {
        return co_y;
    }

    public void setCo_y(int co_y) {
        this.co_y = co_y;
    }

    public boolean getMIne() {
        return isMIne;
    }

    public void setMIne(boolean MIne) {
        isMIne = MIne;
    }
}
