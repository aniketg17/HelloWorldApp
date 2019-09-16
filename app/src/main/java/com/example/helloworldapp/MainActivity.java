package com.example.helloworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.RangeValueIterator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    TextView txt;
    String url;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txt = (TextView) findViewById(R.id.longstyle);
        queue = Volley.newRequestQueue(this);
        url ="http://api.purdue.io/odata/Courses?$filter=contains(Title, 'database')";

    }

    public void CreateGroupPageOnClick(View v){
        Intent intObj = new Intent(this, CreateGroupPage.class);
        startActivity(intObj);
    }

    ArrayList<String> gg;
    public void getHTTPSPlease(View v){
        JsonObjectRequest arrReq = new JsonObjectRequest( url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //txt.setText("GOT RESPONSE");
                        try{
                            gg=new ArrayList<String>();
                            JSONArray a = response.getJSONArray("value");
                            for(int i=0; i<a.length();i++){
                                JSONObject g=a.getJSONObject(i);
                                gg.add(g.getString("Number") +": "+ g.getString("Title"));
                                Log.i("f",g.getString("Number") +": "+ g.getString("Title"));
                            }
                        }catch(JSONException e){
                            //to be done later
                        }

                    }
                },

            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // txt.setText("NO");
                }
        });
        queue.add(arrReq);
    }
}
