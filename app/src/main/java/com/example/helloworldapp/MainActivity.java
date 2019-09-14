package com.example.helloworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnSubmit = (Button) findViewById(R.id.button);

    }

    public void InformationPageOnClick(View v){
        Intent intObj = new Intent(MainActivity.this,InformationPage.class);
        startActivity(intObj);
    }
}
