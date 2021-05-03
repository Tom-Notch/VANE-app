package com.example.vane0427;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button mButtonLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mButtonLog=findViewById(R.id.btn_log);
        mButtonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"Login Successfully!",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(LoginActivity.this,InfoActivity.class);
                startActivity(intent);
            }
        });
    }
}