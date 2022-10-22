package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.model.Game;
import com.example.myapplication.model.GameManager;
import com.example.myapplication.model.SelectGame;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ShowGames extends AppCompatActivity {
    GameManager gamer;
    SelectGame sg;
    ArrayList<String> gameString;
    int delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_games);
        setTitle("Games List");
        gamer = GameManager.getInstance();
        sg = SelectGame.getInstance();
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        delete = gamer.getDel();
        loadData(delete);
        //gamesStr = gamer.setGameTable();
        int a = 0;
        if(gamer.getGames().size()==0 && gameString.size()>0){
            a++;
        }
        else{
            gameString = gamesString(gamer, gameString);
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_list,gameString);
        ListView listView = (ListView)findViewById(R.id.listofGames);
        listView.setAdapter(adapter);
        saveData();
    }

    private void populateListView() {
        //String[] items = {"my", "blue", "pencil"};
        ArrayList<String> gamesStr = gamer.setGameTable();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_list,gamesStr);
        ListView listView = (ListView)findViewById(R.id.listofGames);
        listView.setAdapter(adapter);
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(gameString);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData(int del){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        gameString = gson.fromJson(json, type);

        if(gameString==null||del==1){
            gameString = new ArrayList<String>();
            if(del==1){
                gamer.setDel(0);
            }
        }

    }

    private ArrayList<String> gamesString(GameManager games, ArrayList<String> arrayList) {

        if(games!=null && arrayList.size()!=0){
            if(arrayList.size()!=0){
                int size = games.getGames().size()-1;
                int size_a = arrayList.size() - 1;
                if(!(games.getGames().get(size).gametoString().equals(arrayList.get(size_a)))){
                    for(int i = 0; i<games.getGames().size();i++){
                        arrayList.add(games.getGames().get(i).gametoString());
                    }
                }
            }

        }
        else if(games!=null && arrayList.size()==0){
            for(int i = 0; i<games.getGames().size();i++){
                arrayList.add(games.getGames().get(i).gametoString());
            }
        }
        /*
        else if(games==null && arrayList.size()!=0){
            return arrayList;
        }

         */

        return arrayList;
    }

    /*
    private ArrayList<String> gamesString(GameManager games, ArrayList<String> arrayList) {
        int size = games.getGames().size()-1;
        int size_a = arrayList.size() - 1;
        if(games!=null && !(games.getGames().get(size).gametoString().equals(arrayList.get(size_a)))){
            if(games.getGames().size()!=0 && arrayList.size()!=0){

            }
            for(int i = 0; i<games.getGames().size();i++){
                arrayList.add(games.getGames().get(i).gametoString());
            }
        }
        else{
            return arrayList;
        }

        return arrayList;
    }

     */

    private ArrayList<String> gamestoString(GameManager games, ArrayList<String> arrayList) {
        if(games!=null){
            for(int i = arrayList.size()-1; i<games.getGames().size();i++){
                arrayList.add(games.getGames().get(i).gametoString());
            }
        }
        else{
            return arrayList;
        }

        return arrayList;
    }

    private boolean checkArrayList(GameManager g, ArrayList<String> arrayList){
        ArrayList<String> games = new ArrayList<>();
        games = g.setGameTable();
        int count = 0;
        if(arrayList.size()==g.getGames().size()){
            for(int i = 0; i<games.size(); i++){
                if(games.get(i).equals(arrayList.get(i))){
                    count++;
                }
            }
            if(count==games.size()&&count==arrayList.size()){
                return true;
            }
        }

        return false;
    }
    private boolean checklastGame(GameManager g, ArrayList<String> arrayList){
        int size = g.getGames().size();
        int sizea = arrayList.size();
        if(g.getGames().get(size - 1).gametoString().equals(arrayList.get(sizea-1))){
            return true;
        }
        return false;
    }
}