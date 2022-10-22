package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.example.myapplication.model.Game;
import com.example.myapplication.model.GameManager;
import com.example.myapplication.model.Mine;
import com.example.myapplication.model.SelectGame;
// This class shows textviews with instructions
public class MainActivity3 extends AppCompatActivity {
    GameManager gamer;
    SelectGame sg;
    MediaPlayer mp;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pink)));
        gamer = GameManager.getInstance();
        sg = SelectGame.getInstance();
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        //g = Game.getInstance();
        setTitle("Help");
        textView = findViewById(R.id.textView6);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

    }
}