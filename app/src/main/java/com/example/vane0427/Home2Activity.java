package com.example.vane0427;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home2Activity extends AppCompatActivity {

    private Button mButtonExercise;
    private Button mButtonMotivation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        mButtonExercise=findViewById(R.id.btn_doExercise2);
        mButtonExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home2Activity.this,TrainingSelectActivity.class);
                startActivity(intent);
            }
        });



    }
}