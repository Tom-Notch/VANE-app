package com.example.vane0427;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TrainingTimeActivity extends AppCompatActivity {

    private String mStringTime;
    private String mStringOrderTime;
    private int mIntTime;
    private int mIntOrderTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_time);

        Button mButtonSubmitTime = findViewById(R.id.btn_submit_time);
        mButtonSubmitTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TrainingTimeActivity.this,"Do squats "+mStringTime+" in "+mStringOrderTime+" !",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(TrainingTimeActivity.this,TrainingActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("Time",mIntTime);
                bundle.putInt("Number",-1);
                bundle.putInt("Order",mIntOrderTime);
                bundle.putInt("Type",getIntent().getExtras().getInt("Type"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //ArrayAdapter<CharSequence> adapterTime=ArrayAdapter.createFromResource(getApplicationContext(),R.array.trainingTimeType, android.R.layout.simple_spinner_item);
        //adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //mSpinnerTime.setAdapter(adapterTime);

        Spinner mSpinnerTime = findViewById(R.id.spn_time);
        mSpinnerTime.setSelection(9);
        mSpinnerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mStringTime=parent.getItemAtPosition(position).toString();
                mIntTime=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner mSpinnerTimeOrder = findViewById(R.id.spn_order_time);
        mSpinnerTimeOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mStringOrderTime=parent.getItemAtPosition(position).toString();
                mIntOrderTime=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


}