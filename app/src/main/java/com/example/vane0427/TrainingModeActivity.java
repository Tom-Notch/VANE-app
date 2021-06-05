package com.example.vane0427;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TrainingModeActivity extends AppCompatActivity
{

	private Button mButtonTime;
	private Button mButtonCount;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_training_mode);

		mButtonTime = findViewById(R.id.btn_time);
		mButtonTime.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Toast.makeText(TrainingModeActivity.this, "Training will base on time!", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(TrainingModeActivity.this, TrainingTimeActivity.class);
				Bundle bundle=new Bundle();
				bundle.putInt("Type",getIntent().getExtras().getInt("Type"));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});

		mButtonCount = findViewById(R.id.btn_count);
		mButtonCount.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Toast.makeText(TrainingModeActivity.this, "Training will base on number!", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(TrainingModeActivity.this, TrainingNumberActivity.class);
				Bundle bundle=new Bundle();
				bundle.putInt("Type",getIntent().getExtras().getInt("Type"));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});

	}
}