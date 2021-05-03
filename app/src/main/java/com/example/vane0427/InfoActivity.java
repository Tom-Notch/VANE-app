package com.example.vane0427;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {

    private Button mButtonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        mButtonInfo=findViewById(R.id.btn_info);
        mButtonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InfoActivity.this,"Submit Successfully!",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(InfoActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}