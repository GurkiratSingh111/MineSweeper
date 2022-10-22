package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.model.Game;
import com.example.myapplication.model.GameManager;
import com.example.myapplication.model.Mine;
import com.example.myapplication.model.SelectGame;

public class MainActivity2 extends AppCompatActivity {
    GameManager gamer;
    //Game g;
    SelectGame sg;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pink)));
        gamer = GameManager.getInstance();
        sg = SelectGame.getInstance();
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        setupActivity3LaunchButton();
        setupOptionsButton();
        setTitle("Main Menu");
        setupPLayGameButton();
    }

    private void setupPLayGameButton() {
        Button btn = findViewById(R.id.PlayGameBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(MainActivity2.this, R.raw.click_sound);
                mp.start();
                Intent ip = new Intent(MainActivity2.this, PlayGame.class);
                startActivity(ip);
            }
        });
    }

    private void setupOptionsButton() {
        Button bt = findViewById(R.id.OptionsBtn);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(MainActivity2.this, R.raw.click_sound);
                mp.start();
                Intent i = new Intent(MainActivity2.this, MainActivity4.class);
                startActivity(i);
            }
        });
    }

    private void setupActivity3LaunchButton() {
        Button btn =findViewById(R.id.helpBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(MainActivity2.this, R.raw.click_sound);
                mp.start();
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }
}