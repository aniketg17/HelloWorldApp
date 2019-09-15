package com.example.helloworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }
  //  public void createPageOnClick(View V)
    {
//        Intent intent = new Intent(this,);
//        startactivity();
    }
    public void joinPageOnClick(View V)
    {
        Intent intent = new Intent(this,JoinPage.class);
        startActivity(intent);
    }
}
