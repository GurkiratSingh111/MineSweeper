package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.model.Game;
import com.example.myapplication.model.GameManager;
import com.example.myapplication.model.Mine;

public class MainActivity2 extends AppCompatActivity {
    GameManager gamer;
    Game g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gamer = GameManager.getInstance();
        g = Game.getInstance();
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
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }
}