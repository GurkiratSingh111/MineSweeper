package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.example.myapplication.model.Game;
import com.example.myapplication.model.GameManager;
import com.example.myapplication.model.Mine;
import com.example.myapplication.model.SelectGame;

public class MainActivity3 extends AppCompatActivity {
    GameManager gamer;
    SelectGame sg;
    MediaPlayer mp;
    TextView textView;
    //Game g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        gamer = GameManager.getInstance();
        sg = SelectGame.getInstance();
        //g = Game.getInstance();
        setTitle("Help");
        textView = findViewById(R.id.textView6);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

    }
}