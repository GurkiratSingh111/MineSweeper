package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import ca.cmpt276.as3.model.*;
import com.example.myapplication.model.*;



public class MainActivity extends AppCompatActivity {

    //Mine cookie;
    GameManager gamer;
    Game g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Cookie Finder");
        gamer = GameManager.getInstance();
        g = Game.getInstance();
        //cookie = Mine.getInstance();
        setupActivity2LaunchButton();
    }

    private void setupActivity2LaunchButton() {
        Button btn =findViewById(R.id.MainMenu);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

}