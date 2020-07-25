package com.example.tictaktoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
{
    int activePlayer=0;
    boolean gameActive=true;
    int gameState[]={2,2,2,2,2,2,2,2,2};
    int [][]winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    Button button;
    int count=0;
    TextView textView;

// 0 for jerry and 1 for tom and 2 for empty
    @SuppressLint("SetTextI18n")
    public void dropIn(View view)
    {
        ImageView counter=(ImageView)view;
        int tappedCounter=Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && gameActive)
        {
            gameState[tappedCounter]=activePlayer;

            counter.setTranslationY(-500);
            if(activePlayer==0)
            {
                count+=1;
                counter.setImageResource(R.drawable.jerry);
                activePlayer=1;
            }
            else
            {
                count+=1;
                counter.setImageResource(R.drawable.tom);
                activePlayer=0;
            }
            counter.animate().translationYBy(500).setDuration(400);

            for(int[]winningPosition:winningPositions)
            {
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]] && gameState[winningPosition[1]]==gameState[winningPosition[2]] && gameState[winningPosition[0]]!=2)
                {
                    gameActive=false;
                    String winner="";
                    if(activePlayer==0)
                    {
                        winner="Tom has WON !";
                    }
                    else if(activePlayer==1)
                    {
                        winner="Jerry has WON !";
                    }
                    button=findViewById(R.id.button);
                    textView=findViewById(R.id.textView);
                    textView.setText(winner);
                    count=0;
                    button.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                }
                else if((gameState[winningPosition[0]]!=gameState[winningPosition[1]] || gameState[winningPosition[1]]!=gameState[winningPosition[2]] || gameState[winningPosition[0]]!=gameState[winningPosition[2]]) && gameState[winningPosition[0]]!=2 && count>9)
                {
                    gameActive=false;
                    button=findViewById(R.id.button);
                    textView=findViewById(R.id.textView);
                    textView.setText("NO WINNER ...Both played WELL !");
                    button.setVisibility(View.VISIBLE);
                    count=0;
                    textView.setVisibility(View.VISIBLE);

                }

            }
        }
    }
    public void playAgain(View view)
    {
        button= findViewById(R.id.button);
        textView=findViewById(R.id.textView);
        button.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout=findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++)
        {
            ImageView counter= (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        activePlayer=0;
        gameActive=true;
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}