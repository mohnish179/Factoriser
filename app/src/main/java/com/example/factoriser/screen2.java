package com.example.factoriser;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class screen2 extends AppCompatActivity {
    private static final String TAG = "screen2";
    public static final String s_extra = "com.example.factorise.s_extra";
    static int score=0;
    int high_score;
    TextView count_time;
    CountDownTimer c;
    @SuppressLint("ResourceType")
    Button[] v = new Button[3];//Declaration of the 3 buttons
    public int k1;
    ConstraintLayout lay,lay3;
    int counter = 10;
    Vibrator z;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
        lay = findViewById(R.id.options);
        lay3 = findViewById(R.id.lay3);//for landscape
        int number=getIntent().getIntExtra(MainActivity.Numb_extra,0);
        high_score=get_no();
        score=get_no1();
        final int[] a = new int[number];
        int k=0;
        for (int i = 1; i <= number; i++)//finding all factors
        {
            if(number%i==0)
                a[k++]=i;
        }
        int rand;
        Random r=new Random();
        int[] b = new int[3];
        int n1;
        k1 = r.nextInt(3);//randomly choosing option no.
        if(k==2)
            b[k1] = a[r.nextInt(k)];//randomly choosing correct oprtion
        else
        {
            rand= ThreadLocalRandom.current().nextInt(1,k-1);
            b[k1] = a[rand];//randomly choosing the correct option except 1 and the no. itself
        }
        for (int i = 0; i < 3; i++) {
            if (i != k1)//checking whether it doesn't override correct ans
            {
                n1 = r.nextInt(number);//getting random no.
                for (int j = 0; j < k; j++)//checking its not a factor
                {
                    if (a[j] == n1 || n1 == 0) {
                        i--;
                        break;
                    }
                }
                for (int j = 0; j < 3; j++)//checking if its not any other option
                {
                    if (n1 == b[j]) {
                        i--;
                        break;
                    }
                }
                b[i] = n1;//assigning the wrong option
            }//k1 if
        }//i_loop
        TextView x1 = findViewById(R.id.m1);
        v[0] = findViewById(R.id.m2);
        v[1] = findViewById(R.id.m3);
        v[2] = findViewById(R.id.m4);
        v[0].setText(Integer.toString(b[0]));
        v[1].setText(Integer.toString(b[1]));
        v[2].setText(Integer.toString(b[2]));
        z = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //declaration of all objects
        Button x5 = findViewById(R.id.m5);
        x5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abc = new Intent(screen2.this, MainActivity.class);
                startActivity(abc);
            }
        });//button to go back
        count_time = findViewById(R.id.counttime);
        c = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                count_time.setText(String.valueOf(counter));
                counter--;
            }
            @Override
            public void onFinish() {
                count_time.setText("Oh");
                openDialog3();
            }


        }.start();


        v[0].setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {

               if(k1==0)
               {openDialog1();
                   bg_green(lay,lay3);
               }
               else {
                   openDialog2();
                  bg_red(lay,lay3);

               }
            }
        });
        v[1].setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {

                if(k1==1)
                {
                    openDialog1();
                   bg_green(lay,lay3);

                }
                else {
                    openDialog2();
                    bg_red(lay,lay3);
                }
            }
        });
        v[2].setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {

                if(k1==2) {
                    openDialog1();
                    bg_green(lay,lay3);
                }
                else {
                    openDialog2();
                    bg_red(lay,lay3);

                }
            }
        });



    }//od


    @SuppressLint("NewApi")
    public void openDialog1()
    {
        AlertDialog.Builder pop_up = new AlertDialog.Builder(this);
        pop_up.setTitle("Answer");
        pop_up.setMessage("It is Correct");
        c.cancel();
        score+=1;
        if(score>=high_score)
        {high_score=score;
            put_no();
        }
        put_no1();

        pop_up.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent abc = new Intent(screen2.this, MainActivity.class);
                abc.putExtra(s_extra,score);
                startActivity(abc);

            }
        });
        pop_up.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                Intent abc = new Intent(screen2.this, MainActivity.class);
                abc.putExtra(screen2.s_extra,score);
                startActivity(abc);
            }
        });


        pop_up.show();


    }//od1
    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void openDialog2()
    {z.vibrate(400);
         AlertDialog.Builder pop_up = new AlertDialog.Builder(this);
        pop_up.setTitle("Answer");
        pop_up.setMessage("It is wrong. The correct answer is "+v[k1].getText());
        c.cancel();

        score=0;
        put_no1();
        pop_up.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent abc = new Intent(screen2.this, MainActivity.class);
                //abc.putExtra(screen2.s_extra2,high_score);
                abc.putExtra(screen2.s_extra,score);
                startActivity(abc);

            }
        });
        pop_up.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                Intent abc = new Intent(screen2.this, MainActivity.class);
                abc.putExtra(screen2.s_extra,score);
                startActivity(abc);
            }
        });

        pop_up.show();

    }//od2
    public void put_no()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("high_score",score);
        editor.apply();
    }
    public int get_no()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getInt("high_score", 0);
    }
    public void put_no1()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putInt("score",score);
        editor.apply();
    }
    public int get_no1()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getInt("score", 0);
    }
public void bg_green(ConstraintLayout lay,ConstraintLayout lay3)
    {

            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            {
                // In landscape
                lay3.setBackgroundColor(getResources().getColor(R.color.Green));
            }
            else {
                // In portrait
                lay.setBackgroundColor(getResources().getColor(R.color.Green));
            }

    }//bg_green
 public void bg_red(ConstraintLayout lay,ConstraintLayout lay3)
 {
     int orientation = getResources().getConfiguration().orientation;
     if (orientation == Configuration.ORIENTATION_LANDSCAPE)
     {
         // In landscape
         lay3.setBackgroundColor(getResources().getColor(R.color.Red));
     }
     else {
         // In portrait
         lay.setBackgroundColor(getResources().getColor(R.color.Red));
     }
 }//bg_red
    @SuppressLint("NewApi")
    public void openDialog3()
    {bg_red(lay,lay3);
        AlertDialog.Builder pop_up = new AlertDialog.Builder(this);
        pop_up.setTitle("Time's Up");
        pop_up.setMessage("Oh oh! Time's up. The correct answer is "+v[k1].getText());
        pop_up.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent abc = new Intent(screen2.this, MainActivity.class);
                        //abc.putExtra(screen2.s_extra2,high_score);
                        abc.putExtra(screen2.s_extra, score);
                        startActivity(abc);
                    }
                });
                pop_up.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {

                        Intent abc = new Intent(screen2.this, MainActivity.class);
                        abc.putExtra(screen2.s_extra,score);
                        startActivity(abc);
                    }
                });

                pop_up.show();
            }
    }//apa