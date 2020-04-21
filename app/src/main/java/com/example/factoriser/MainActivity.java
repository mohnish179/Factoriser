package com.example.factoriser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String Numb_extra="com.example.factoriser.Numb_extra";
    private static final String TAG = "MainActivity";
    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //int s2=getIntent().getIntExtra(screen2.s_extra,0);//getting value of score
        TextView s3=(TextView) findViewById(R.id.score);//Declaration of TextView
        TextView a=(TextView) findViewById(R.id.tv1);//Declaration of another TextView
        Button bt1=(Button) findViewById(R.id.bt1);//Declaration of buttons
        TextView hs=(TextView) findViewById(R.id.hs);//declaration of high score text view


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int s5=sharedPreferences.getInt("high_score", 0);
        int s2=sharedPreferences.getInt("score",0);
        s3.setText("Current Score: "+Integer.toString(s2));//setting the text of the score
   //int s4=getIntent().getIntExtra(screen2.s_extra2,0);
   hs.setText("Previous High Score: "+s5);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                EditText b=(EditText) findViewById(R.id.et1);//declaring the edit text

                if(b.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Enter the Data", Toast.LENGTH_SHORT).show();
                else if((Integer.valueOf(b.getText().toString())==0)||(Integer.valueOf(b.getText().toString())==1)||(Integer.valueOf(b.getText().toString())==2)||(Integer.valueOf(b.getText().toString())==3)||(Integer.valueOf(b.getText().toString())==04))
                {
                    Toast.makeText(MainActivity.this,"Enter valid no.",Toast.LENGTH_SHORT).show();
                }
                else
                { Intent xyz=new Intent(MainActivity.this,screen2.class);
                int no;
                no=Integer.parseInt(b.getText().toString());

                xyz.putExtra(Numb_extra,no);

                    startActivity(xyz);
                }
                }

        });

    }//oc


}//aca
  /*  int no;
                */