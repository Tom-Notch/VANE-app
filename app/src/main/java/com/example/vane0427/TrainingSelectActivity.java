package com.example.vane0427;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TrainingSelectActivity extends AppCompatActivity {

    private Button mButtonSquat;
    private Button mButtonPushUp;
    private Button mButtonHipBridge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_select);

        mButtonSquat=findViewById(R.id.btn_squat);
        mButtonSquat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TrainingSelectActivity.this,"You select to do squats!",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(TrainingSelectActivity.this,TrainingModeActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("Type",0);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        mButtonPushUp=findViewById(R.id.btn_push_up);
        mButtonPushUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TrainingSelectActivity.this,"You select to do push-ups!",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(TrainingSelectActivity.this,TrainingModeActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("Type",1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        mButtonHipBridge=findViewById(R.id.btn_hip_bridge);
        mButtonHipBridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TrainingSelectActivity.this,"You select to do hip bridges!",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(TrainingSelectActivity.this,TrainingTANActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("Type",2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}