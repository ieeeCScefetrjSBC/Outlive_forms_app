package com.example.felipe.androidexperiments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button button_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_start = (Button)findViewById(R.id.button_start);


        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int_quest = new Intent(MainActivity.this, QuestionnaireActivity.class);
                startActivity(int_quest);
            }
        });
    }

}