package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.model.Game;
import com.example.myapplication.model.GameManager;
import com.example.myapplication.model.Mine;
import com.example.myapplication.model.SelectGame;

import java.util.ArrayList;

//import ca.cmpt276.as3.model.*;
public class MainActivity4 extends AppCompatActivity {
    GameManager gamer;
    SelectGame sg;
    //Game g;
    int rows, columns, mines;
    //MediaPlayer mp1;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        gamer = GameManager.getInstance();
        sg = SelectGame.getInstance();

        //g = Game.getInstance();
        setTitle("Options");
        createNumMines();
        createBoardSizes();

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(MainActivity4.this, R.raw.click_sound);
                mp.start();
                Intent intent = new Intent(MainActivity4.this, ShowGames.class);
                startActivity(intent);
            }
        });

        Button rem = findViewById(R.id.removeAll);
        rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(MainActivity4.this, R.raw.click_sound);
                mp.start();
                gamer.removeAllGames();
            }
        });

    }

    private void createBoardSizes() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_board_size);
        int[] board_games = getResources().getIntArray(R.array.board_size_game);
        // Create the Buttons
        for(int i = 0; i < board_games.length; i=i+2){
            int board_game_row = board_games[i];
            int board_game_col = board_games[i+1];

            RadioButton rbtn = new RadioButton(this);
            rbtn.setText(board_game_row + " rows * " + board_game_col + " columns");
            rbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp = MediaPlayer.create(MainActivity4.this, R.raw.click_sound);
                    mp.start();
                    sg.setC(board_game_col);
                    sg.setR(board_game_row);
                    //g.setNumberOfRows(board_game_row);
                    //g.setNumberOfColumns(board_game_col);

                }
            });

            // Set On CLick Callbacks

            group.addView(rbtn);
        }
    }

    private void createNumMines() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_mines);
        int[] number_mines = getResources().getIntArray(R.array.num_mines);
        // Create the Buttons
        for(int i = 0; i < number_mines.length; i++){
            int number_mine = number_mines[i];

            //cookie.setNumberOfMines(number_mine);

            RadioButton rbtn = new RadioButton(this);
            rbtn.setText(number_mine + " mines");

            rbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp = MediaPlayer.create(MainActivity4.this, R.raw.click_sound);
                    mp.start();
                    sg.setM(number_mine);
                    //g.setNumberOfMines(number_mine);

                }
            });

            // Set On CLick Callbacks

            group.addView(rbtn);
        }

    }
}