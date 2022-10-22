package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.Game;
import com.example.myapplication.model.GameManager;
import com.example.myapplication.model.Mine;
import com.example.myapplication.model.SelectGame;

public class PlayGame extends AppCompatActivity {
    GameManager gamer;
    SelectGame sg;
    Game g;
    MediaPlayer mp1;
    MediaPlayer mp2;
    int mines = 0;
    int scans = 0;
    //TextView text1 = findViewById(R.id.found);
    //TextView text2 = findViewById(R.id.scans);

    Button buttons[][];
    TextView text1, text2;
    //TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.pink)));
        gamer = GameManager.getInstance();
        sg = SelectGame.getInstance();
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        //g = Game.getInstance();
        g = new Game(sg.getM(), sg.getR(), sg.getC());
        text1 = findViewById(R.id.found);
        text2 = findViewById(R.id.scans);
        buttons = new Button[g.getNumberOfRows()][g.getNumberOfColumns()];
        setTitle("Game");
        //g.addArray();
        g.addingMines();
        g.checkMines();

        text1.setText("Found 0 of " + g.getNumberOfMines() + " mines.");
        populateButtons();



    }

    private void populateButtons() {
        TableLayout gameTable = findViewById(R.id.table);

        for (int row = 0; row < g.getNumberOfRows(); row++) {
            TableRow tableRow = new TableRow(this);

            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));

            gameTable.addView(tableRow);
            for (int col = 0; col < g.getNumberOfColumns(); col++) {
                final int FINAL_COL = col;
                final int FINAL_ROW = row;
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));
                //button.setText("" + row + ", " + col);
                button.setPadding(0, 0, 0, 0);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_ROW, FINAL_COL);
                    }
                });
                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    private void gridButtonClicked(int x, int y) {
        //Toast.makeText(this, "Button Clicked ON Row " + x + " and column " + y, Toast.LENGTH_SHORT).show();

        int index = g.ReturnIndex(x, y);
        Button button = buttons[x][y];
        if (g.getMines().get(index).getMIne() == false){
            mp1 = MediaPlayer.create(PlayGame.this, R.raw.scan_sound);
            mp1.start();
            if(g.getMines().get(index).getIsClicked() ==0 && scans<(g.getNumberOfRows()*g.getNumberOfColumns())){
                scans++;
                g.getMines().get(index).setIsClicked(1);
                g.setScansUsed(scans);
                text2.setText("# Scans used: " + g.getScansUsed());
            }

            button.setText(" " + g.getMines().get(index).getHint());}
        else {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp1 = MediaPlayer.create(PlayGame.this, R.raw.scan_sound);
                    mp1.start();
                    if(g.getMines().get(index).getIsClicked() ==0 && scans<(g.getNumberOfRows()*g.getNumberOfColumns())){
                        //Adding lines in this
                        /*
                        for(int i=0;i<g.getMines().size();i++) {
                            g.getMines().get(i).setMIne(false);
                            g.checkMines();
                            for (int m = 0; m < g.getMines().size(); m++) {
                                if (g.getMines().get(i).getCo_x() == x && g.getMines().get(i).getIsClicked() == 1) {
                                    buttons[x][g.getMines().get(i).getCo_y()].setText("" + g.getMines().get(i).getHint());
                                } else if (g.getMines().get(i).getCo_y() == y && g.getMines().get(i).getIsClicked() == 1) {
                                    buttons[g.getMines().get(i).getCo_x()][y].setText("" + g.getMines().get(i).getHint());
                                }
                            }
                        }
                        */

                        // Till this
                        scans++;
                        g.getMines().get(index).setIsClicked(1);
                        g.setScansUsed(scans);
                        text2.setText("# Scans used: " + g.getScansUsed());
                    }
                    button.setText(" " + g.getMines().get(index).getHint());
                }
            });
        }

        lockButtonSize();
        for(int i=0;i<g.getMines().size();i++)
        {
            if(g.getMines().get(i).getCo_x()==x && g.getMines().get(i).getCo_y()==y)
            {
                if(g.getMines().get(i).getMIne()==true)
                {
                    mp2 = MediaPlayer.create(PlayGame.this, R.raw.ice_cream_found);
                    mp2.start();

                    if(mines<g.getNumberOfMines()){
                        mines++;
                        g.setMinesFound(mines);
                        text1.setText("Found " + g.getMinesFound() + " of " + g.getNumberOfMines() + " mines.");
                    }

                    if(mines==g.getNumberOfMines()){
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage(R.string.confirm_dialog_message)
                                .setTitle(R.string.confirm_dialog_title)

                                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        gamer.addGame(g);

                                        Intent i5 = new Intent(PlayGame.this, MainActivity2.class);
                                        startActivity(i5);


                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                    int newWidth = button.getWidth();
                    int newHeight = button.getHeight();
                    Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ice_cream);
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                    Resources resource = getResources();
                    button.setBackground(new BitmapDrawable(resource, scaledBitmap));
                    for(int k=0;k<g.getMines().size();k++) {
                        g.getMines().get(i).setMIne(false);
                        g.checkMines();
                        for (int m = 0; m < g.getMines().size(); m++) {
                            if (g.getMines().get(m).getCo_x() == x && g.getMines().get(m).getIsClicked() >= 1) {
                                buttons[x][g.getMines().get(m).getCo_y()].setText("" + g.getMines().get(m).getHint());
                            } else if (g.getMines().get(m).getCo_y() == y && g.getMines().get(m).getIsClicked() >= 1) {
                                buttons[g.getMines().get(m).getCo_x()][y].setText("" + g.getMines().get(m).getHint());
                            }
                            /*if (g.getMines().get(i).getCo_x() == x && g.getMines().get(i).getIsClicked() >= 1) {
                                buttons[x][g.getMines().get(i).getCo_y()].setText("" + g.getMines().get(i).getHint());
                            } else if (g.getMines().get(i).getCo_y() == y && g.getMines().get(i).getIsClicked() >= 1) {
                                buttons[g.getMines().get(i).getCo_x()][y].setText("" + g.getMines().get(i).getHint());
                            }*/
                        }
                    }
                }

            }
        }



    }

    private void lockButtonSize() {
        for(int row = 0; row < g.getNumberOfRows(); row++){
            for(int col = 0; col<g.getNumberOfColumns(); col++){
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }



}