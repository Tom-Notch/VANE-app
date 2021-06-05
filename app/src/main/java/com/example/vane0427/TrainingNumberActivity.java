package com.example.vane0427;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TrainingNumberActivity extends AppCompatActivity {

    private String mStringNumber;
    private String mStringOrderNumber;
    private int mIntNumber;
    private int mIntOrderNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_number);

        Button mButtonSubmitTime = findViewById(R.id.btn_submit_number);
        mButtonSubmitTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TrainingNumberActivity.this,"Do squats "+mStringNumber+" in "+mStringOrderNumber+" !",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(TrainingNumberActivity.this,TrainingActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("Time",-1);
                bundle.putInt("Number",mIntNumber);
                bundle.putInt("Order",mIntOrderNumber);
                bundle.putInt("Type",getIntent().getExtras().getInt("Type"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Spinner mSpinnerNumber = findViewById(R.id.spn_number);
        mSpinnerNumber.setSelection(4);
        mSpinnerNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mStringNumber=parent.getItemAtPosition(position).toString();
                mIntNumber=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner mSpinnerTimeOrder = findViewById(R.id.spn_order_number);
        mSpinnerTimeOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mStringOrderNumber=parent.getItemAtPosition(position).toString();
                mIntOrderNumber=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}