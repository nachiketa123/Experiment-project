package com.example.nachu.mathgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class ScoreCard extends AppCompatActivity {
    private int score;
    private int highscore;
    TextView scorenum;
    TextView HiSc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);
        scorenum=(TextView)findViewById(R.id.number);
        HiSc=(TextView)findViewById(R.id.HiSc);
        HiSc.setVisibility(View.INVISIBLE);
        showScore();
    }
    public void showScore()
    {
        SharedPreferences sp;
        sp = getSharedPreferences("SCORE", Context.MODE_PRIVATE);
        highscore=sp.getInt("HIscore",0);
        SharedPreferences.Editor editor;
        Bundle bundle=getIntent().getExtras();
        String score=bundle.getString("score");
        this.score=Integer.parseInt(score);
        scorenum.setText(""+score);
        if(this.score>=highscore) {
            editor = sp.edit();
            editor.putInt("HIscore", this.score);
            editor.apply();
            highscore=this.score;
        }
        if(highscore>0)
        {
            HiSc.setVisibility(View.VISIBLE);
            HiSc.setText("High Score :"+highscore);
        }

    }

}
