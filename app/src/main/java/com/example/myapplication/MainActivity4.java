package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.model.Game;
import com.example.myapplication.model.GameManager;
import com.example.myapplication.model.Mine;

//import ca.cmpt276.as3.model.*;
public class MainActivity4 extends AppCompatActivity {
    GameManager gamer;
    Game g;
    int rows, columns, mines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        gamer = GameManager.getInstance();
        g = Game.getInstance();
        setTitle("Options");
        createNumMines();
        createBoardSizes();
        //g = new Game(mines, rows, columns);
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
                    //rows = board_game_row;
                    //columns = board_game_col;
                    //Game g = new Game(board_game_row,board_game_col,0);
                    g.setNumberOfRows(board_game_row);
                    g.setNumberOfColumns(board_game_col);

                    //cookie.setNumberOfColumns(board_game_col);
                    //cookie.setNumberOfRows(board_game_row);
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
                    //mines = number_mine;
                    g.setNumberOfMines(number_mine);

                    //cookie.setNumberOfMines(number_mine);
                    //Toast.makeText(MainActivity4.this, "Number of mines: " + cookie.getNumberOfMines(), Toast.LENGTH_SHORT).show();
                }
            });

            // Set On CLick Callbacks

            group.addView(rbtn);
        }

    }
}