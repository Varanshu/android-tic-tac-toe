package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int active=0;
    int [][] winner={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameActive=true;

    public void dropin(View view){

        ImageView img=(ImageView) view;
        img.setTranslationY(-2000);
        img.getTag();
        int counter=Integer.parseInt(img.getTag().toString());

        if(gamestate[counter]==2 && gameActive) {

            gamestate[counter] = active;
            if (active == 0) {
                img.setImageResource(R.drawable.yellow);
                active = 1;
            } else {
                img.setImageResource(R.drawable.red);
                active = 0;
            }
            img.animate().translationYBy(2000).rotation(3600).setDuration(400);

            String player;
            for (int[] position : winner) {
                if (gamestate[position[0]] == gamestate[position[1]] &&  gamestate[position[1]] == gamestate[position[2]] && gamestate[position[0]] != 2) {
                    gameActive=false;
                    if (active == 0) {
                        player = "Red";
                    } else {
                        player = "Yellow";
                    }

                   Button button=(Button)findViewById(R.id.TryAgainButton);

                    TextView winner=(TextView) findViewById(R.id.WinnertTextView);

                    winner.setText(player + " has won");

                    button.setVisibility(View.VISIBLE);
                    winner.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){
        Button button=(Button)findViewById(R.id.TryAgainButton);

        TextView winner=(TextView) findViewById(R.id.WinnertTextView);


        button.setVisibility(View.INVISIBLE);
        winner.setVisibility(View.INVISIBLE);
        GridLayout grid=(GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<grid.getChildCount();i++){
            ImageView child=(ImageView) grid.getChildAt(i);
            child.setImageDrawable(null);
        }
        for(int j=0;j<gamestate.length;j++){
            gamestate[j]=2;
        }
        active=0;
        gameActive=true;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
