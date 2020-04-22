package com.example.factoriser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String Numb_extra = "com.example.factorise.Numb_extra";
    private static final String TAG = "MainActivity";
    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView s3 = findViewById(R.id.score);//Declaration of TextView
        TextView a = findViewById(R.id.tv1);//Declaration of another TextView
        Button bt1 = findViewById(R.id.bt1);//Declaration of buttons
        TextView hs = findViewById(R.id.hs);//declaration of high score text view
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int s5=sharedPreferences.getInt("high_score", 0);
        int s2=sharedPreferences.getInt("score",0);
        s3.setText("Score: " + s2);//setting the text of the score
        hs.setText("High Score: " + s5);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                EditText b = findViewById(R.id.et1);//declaring the edit text

                if(b.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Enter the Data", Toast.LENGTH_SHORT).show();
                else if ((Integer.parseInt(b.getText().toString()) == 0) || (Integer.parseInt(b.getText().toString()) == 1) || (Integer.parseInt(b.getText().toString()) == 2) || (Integer.parseInt(b.getText().toString()) == 3) || (Integer.parseInt(b.getText().toString()) == 4))
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
