package com.example.myapplication.model;

//package model;

import java.util.ArrayList;

public class GameManager {
    private ArrayList<Game> games;


    public ArrayList<String> setGameTable() {
        ArrayList<String> str = new ArrayList<>();
        for(int i =0; i<games.size();i++){
            str.add("Game no: " + i+1 +". " + games.get(i).gametoString());
        }
        /*
        for(Game g: games){
            str.add(g.gametoString());
        }

         */
        return str;
    }

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

    public void removeAllGames(){
        for(int i = 0; i < games.size(); i++){
            games.remove(i);
        }
    }

    public void addGame(Game g){
        games.add(g);
    }

    public void deleteGame(int i){
        games.remove(i);
    }

}

