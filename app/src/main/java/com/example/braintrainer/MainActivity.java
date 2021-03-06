package com.example.braintrainer;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.*;

import static android.graphics.Color.GRAY;
import static android.graphics.Color.GREEN;

public class MainActivity extends AppCompatActivity {
    int ans;
    Button[] buttons=new Button[4];
    CountDownTimer countDownTimer;
    long time=31000;
    Random random1=new Random();
    int questionsSolved;
    boolean finished=false;
    TextView counter;
    TextView resultView;
    int numberOfquestions;
    public void check(View view)
    {
         Button temp=(Button)view;
        resultView.setVisibility(View.VISIBLE);
         int result=Integer.parseInt(temp.getText().toString());
        if(result==ans && !finished)
        {
            questionsSolved++;
            resultView.setText("Right");
        }
        else if(result!=ans && !finished)
            resultView.setText("Wrong");
        else if(finished)
            resultView.setText("Game Over");
        if(!finished) {
            play();
        }
        counter.setText(String.valueOf(questionsSolved)+"/"+String.valueOf(++numberOfquestions));

    }
    public void timerWork(){
        countDownTimer=new CountDownTimer(time,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TextView timer=(TextView)findViewById(R.id.timer);
                timer.setVisibility(1);
                timer.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                finished=true;
                Toast.makeText(MainActivity.this, "You solved "+String.valueOf(questionsSolved)+" questions.", Toast.LENGTH_SHORT).show();
                Button play_again=findViewById(R.id.play_again);
                play_again.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    public void play(){
        counter=(TextView)findViewById(R.id.counter);
        counter.setVisibility(1);
        int n1=random1.nextInt(6000);
        int n2=random1.nextInt(6000);
        String num1=Integer.toString(n1);
        String num2=Integer.toString(n2);
        String text=num1+"+"+num2+"=?";
        TextView textView=(TextView)findViewById(R.id.question);
        textView.setVisibility(1);
        textView.setText(text);
        MakeButtonVisible();
        int ansbutton=random1.nextInt(buttons.length);
        Button temp=buttons[ansbutton];
        ans=n1+n2;
        temp.setText(Integer.toString(ans));
        Set<Integer> hashSet=new HashSet<>();
        hashSet.add(ans);
        for(int i=0;i<4;i++)
        {
            if(ansbutton!=i)
            {
                int t1=random1.nextInt(6000)+random1.nextInt(6000);
                while(hashSet.contains(t1))
                {
                    t1=random1.nextInt(6000)+random1.nextInt(6000);
                }
                hashSet.add(t1);
                buttons[i].setText(Integer.toString(t1));
            }
        }
    }
    public void start(View view)
    {
        Button play=findViewById(R.id.play);
        play.setVisibility(View.INVISIBLE);
        Button play_again=findViewById(R.id.play_again);
        play_again.setVisibility(View.INVISIBLE);
        finished=false;
        questionsSolved=0;
        numberOfquestions=0;
        counter.setText("0/0");
        resultView.setVisibility(View.INVISIBLE);
        timerWork();
            play();

    }
    public void MakeButtonVisible()
    {
        buttons[0]=findViewById(R.id.A);
        buttons[1]=findViewById(R.id.B);
        buttons[2]=findViewById(R.id.C);
        buttons[3]=findViewById(R.id.D);
        for(int i=0;i<4;i++)
            buttons[i].setVisibility(1);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultView=(TextView)findViewById(R.id.result);
        counter=(TextView)findViewById(R.id.counter);
    }
}