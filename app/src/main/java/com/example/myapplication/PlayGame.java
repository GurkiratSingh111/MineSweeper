package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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

public class PlayGame extends AppCompatActivity {
    GameManager gamer;
    Game g;
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
        gamer = GameManager.getInstance();
        g = Game.getInstance();

        text1 = findViewById(R.id.found);
        text2 = findViewById(R.id.scans);
        buttons = new Button[g.getNumberOfRows()][g.getNumberOfColumns()];
        setTitle("Game");
        g.addArray();
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
                    if(g.getMines().get(index).getIsClicked() ==0 && scans<(g.getNumberOfRows()*g.getNumberOfColumns())){
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

                                        Intent i5 = new Intent(PlayGame.this, MainActivity2.class);
                                        startActivity(i5);

                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                    int newWidth = button.getWidth();
                    int newHeight = button.getHeight();
                    Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bomb);
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                    Resources resource = getResources();
                    button.setBackground(new BitmapDrawable(resource, scaledBitmap));
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