package com.example.myapplication.model;

//package model;

import java.util.ArrayList;

public class GameManager {
    private ArrayList<Game> games;

    private static GameManager instance;

    private GameManager(){
        games = new ArrayList<Game>();
    }

    public static GameManager getInstance(){
        if(instance==null){
            instance = new GameManager();
        }
        return instance;
    }

    public void addGame(Game g){
        games.add(g);
    }

    public void deleteGame(int i){
        games.remove(i);
    }

}

