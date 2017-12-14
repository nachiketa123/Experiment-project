package com.example.nachu.mathgame;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static java.net.Proxy.Type.HTTP;

public class MainActivity extends AppCompatActivity {
    ImageButton play;
    ImageButton share;
    RadioButton rdbtn1,rdbtn2,rdbtn3;
    RadioGroup rg;
    TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          play= findViewById(R.id.play);
          share= findViewById(R.id.share);
          rdbtn1=(RadioButton) findViewById(R.id.rdbtn1);
          rdbtn2=(RadioButton) findViewById(R.id.rdbtn2);
          rdbtn3=(RadioButton) findViewById(R.id.rdbtn3);
          welcome=(TextView)findViewById(R.id.welcome);
          rg=(RadioGroup) findViewById(R.id.rg);
          play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v)
                {
                    play.setVisibility(View.GONE);
                    rg.setVisibility(View.VISIBLE);
                }
            });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Intent i=new Intent(MainActivity.this,GameScreen.class);
                Bundle bundle = null;
                if(checkedId==R.id.rdbtn1)
                {
                    String t="60";
                    bundle=new Bundle();
                    bundle.putString("time",t);
                }
                if(checkedId==R.id.rdbtn2)
                {
                    String t="180";
                    bundle=new Bundle();
                    bundle.putString("time",t);
                }
                if(checkedId==R.id.rdbtn3)
                {
                    String t="300";
                    bundle=new Bundle();
                    bundle.putString("time",t);
                }
                i.putExtras(bundle);
                startActivity(i);

            }
        });

        share.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
              Intent intent=new Intent(Intent.ACTION_SEND);
              intent.setType("text/plain");
              intent.putExtra(Intent.EXTRA_TEXT,"https://drive.google.com/open?id=1BYkUGG8XU-XyuBDagHIZMsFLynyM8OUj");
               startActivity(intent)
               ;}
        });

    }
}
