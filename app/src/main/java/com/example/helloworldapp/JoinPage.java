package com.example.helloworldapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class JoinPage extends AppCompatActivity {

    String[] data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_page);

        data =  new String[]{"COURSE 1", "COURSE 2", "COURSE 3","COURSE 4"};

        ListView courselistListView = (ListView) findViewById(R.id.courselistListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);

        courselistListView.setAdapter(adapter);

        courselistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                intent.putExtra("Key",data[position]);
                intent.setClass(JoinPage.this,test.class);
                startActivity(intent);
            }
        });
    }
}
