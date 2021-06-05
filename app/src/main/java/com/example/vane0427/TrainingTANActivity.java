package com.example.vane0427;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class TrainingTANActivity extends AppCompatActivity {

    private Spinner mSpinnerTime;
    private Spinner mSpinnerNumber;
    private Spinner mSpinnerOrder;

    private Button mButtonSubmit;

    private String mStringNumber;
    private String mStringTime;
    private String mStringOrder;

    private Integer mIntNumber;
    private Integer mIntTime;
    private Integer mIntOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_tanactivity);

        mSpinnerTime=findViewById(R.id.spn_tan_time);
        mSpinnerTime.setSelection(0);
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

        mSpinnerNumber=findViewById(R.id.spn_tan_number);
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

        mSpinnerOrder=findViewById(R.id.spn_tan_order);
        mSpinnerOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mStringOrder=parent.getItemAtPosition(position).toString();
                mIntOrder=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mButtonSubmit=findViewById(R.id.btn_tan);
        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TrainingTANActivity.this,TrainingActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("Time",mIntTime);
                bundle.putInt("Number",mIntNumber);
                bundle.putInt("Order",mIntOrder);
                bundle.putInt("Type",getIntent().getExtras().getInt("Type"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
}