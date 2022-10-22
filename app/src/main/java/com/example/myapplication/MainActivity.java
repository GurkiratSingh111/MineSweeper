package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import ca.cmpt276.as3.model.*;
import com.example.myapplication.model.*;


// MainActivity class has the button that connects it to mainactivity2
public class MainActivity extends AppCompatActivity {
    GameManager gamer;
    SelectGame sg;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pink)));
        setTitle("Ice Cream Finder");
        gamer = GameManager.getInstance();
        sg = SelectGame.getInstance();
        setupActivity2LaunchButton();
    }

    private void setupActivity2LaunchButton() {
        Button btn =findViewById(R.id.MainMenu);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(MainActivity.this, R.raw.click_sound);
                mp.start();
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

}