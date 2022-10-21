package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.model.GameManager;
import com.example.myapplication.model.SelectGame;

import java.util.ArrayList;

public class ShowGames extends AppCompatActivity {
    GameManager gamer;
    SelectGame sg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_games);
        gamer = GameManager.getInstance();
        sg = SelectGame.getInstance();
        populateListView();
    }

    private void populateListView() {
        //String[] items = {"my", "blue", "pencil"};
        ArrayList<String> gamesStr = gamer.setGameTable();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_list,gamesStr);
        ListView listView = (ListView)findViewById(R.id.listofGames);
        listView.setAdapter(adapter);
    }
}