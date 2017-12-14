package com.example.nachu.mathgame;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Random;

public class GameScreen extends AppCompatActivity {
    Bundle bundle;
    String str;
    private int seconds;
    private int s=0;
    private String sign="+-*/";
    private int a,b,res;
    boolean answer,setans=false;
    float f;
    TextView que;
    TextView ans;
    TextView time;
    TextView score;
    ImageButton correct;
    ImageButton wrong;
    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        time = (TextView) findViewById(R.id.time);
        score = (TextView) findViewById(R.id.score);
        que = (TextView) findViewById(R.id.que);
        ans = (TextView) findViewById(R.id.ans);
        correct = (ImageButton) findViewById(R.id.correct);
        wrong = (ImageButton) findViewById(R.id.wrong);
        timer();
        wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 answer=false;
                check(answer);

            }
        });
        correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = true;
                check(answer);
            }

        });
        generateQue();

    }
    private void generateQue()
    {
        Random random=new Random();
        a=random.nextInt(100);
        b=random.nextInt(100);
        char ch=sign.charAt(random.nextInt(4));
        switch(ch)
        {
            case '+':res=a+b;
                break;
            case '-':res=a-b;
                break;
            case '*':res=a*b;
                break;
            case '/':res=a/b;
                break;
        }
        que.setText(""+a+ch+b);
        f=random.nextFloat();
        if(f>0.5f) {
                   setans=true;
                  ans.setText("=" + res);
        }
        else {
             ans.setText("=" + random.nextInt(100));
        }

    }
    public void check(boolean ANS)
    {
        if(ANS==setans)
        {
            s+=5;
            score.setText(""+s);
        }
        else
        {
            Vibrator vibrator=(Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(100);
        }
        setans=false;
        generateQue();

    }
    public void timer()
    {
        final Vibrator vibrator=(Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        final Handler h=new Handler();
        bundle=getIntent().getExtras();
        str=bundle.getString("time");
        seconds=Integer.parseInt(str);
        h.post(new Runnable() {
            @Override
            public void run() {

                time.setText("TIME : "+(--seconds));
                if(seconds>0)
                {
                    h.postDelayed(this,1000);
                }
                else
                {
                    //time out
                    vibrator.vibrate(1000);
                    Intent i=new Intent(GameScreen.this,ScoreCard.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("score",Integer.toString(s));
                    i.putExtras(bundle);
                    startActivity(i);

                }
            }
        });
    }
}
