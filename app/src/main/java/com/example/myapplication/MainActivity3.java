package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.model.Game;
import com.example.myapplication.model.GameManager;
import com.example.myapplication.model.Mine;
import com.example.myapplication.model.SelectGame;

public class MainActivity3 extends AppCompatActivity {
    GameManager gamer;
    SelectGame sg;
    //Game g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        gamer = GameManager.getInstance();
        sg = SelectGame.getInstance();
        //g = Game.getInstance();
        setTitle("Help");

    }
}