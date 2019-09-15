package com.example.helloworldapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        TextView testTextView =  (TextView)findViewById(R.id.testTextView);

        testTextView.setText(getIntent().getStringExtra("Key"));

    }
}
