package com.example.helloworldapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


//All imports for searchClasses
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CreateGroupPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {


    private DatePickerDialog.OnDateSetListener studyDateSetListener;
    private Spinner spinner;
    private String url;
    RequestQueue queue;
    EditText searchText;

    //Global Create Course Inputs
    String courseName;
    String nameOfStudyGroup;
    String date;
    String time;
    String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group_page);

        //TextView Name = ((TextView)findViewById(R.id.InformationPageName));


        //Requester
        queue = Volley.newRequestQueue(this);
        url = "http://api.purdue.io/odata/Courses?$filter=contains(Title,'Intro')";
        //Spinner Creation
        spinner = findViewById(R.id.selectCourseList);
        searchText = ((EditText) findViewById(R.id.editText));

        //Initialize Date from Calender
        Calendar cal = Calendar.getInstance();
        date = cal.get(Calendar.MONTH)+" / " + cal.get(Calendar.DAY_OF_MONTH) +" / " + cal.get(Calendar.YEAR);

        getHTTPSRequest(url,queue);


    }

    //Functions of OnClick
    public void saveOnClick(View v){
        Log.i("i","iounr");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Query

        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }


    public void buttonIsClicked(View v){
        //Search text
        String newURL = "http://api.purdue.io/odata/Courses?$filter=contains(Title,'"+searchText.getText().toString()+"')";
        getHTTPSRequest(newURL,queue);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Not used
    }


    public ArrayList<String> getHTTPSRequest(String url, RequestQueue q){
        final ArrayList<String> out = new ArrayList<String>();//Initialize ArrayList for output

        JsonObjectRequest arrReq = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //txt.setText("GOT RESPONSE");
                        try{
                            JSONArray responseArray = response.getJSONArray("value");
                            if(responseArray.length()==0){//display message saying no results found
                                Toast.makeText(getApplicationContext(),"No results for Query",Toast.LENGTH_SHORT).show();
                            }
                            for(int i=0; i<responseArray.length();i++){
                                JSONObject g=responseArray.getJSONObject(i);
                                out.add(g.getString("Number") +": "+ g.getString("Title"));
                                //Log.i("f",g.getString("Number") +": "+ g.getString("Title"));//for testing
                            }
                            spinner.setAdapter(new ArrayAdapter<String>(CreateGroupPage.this,android.R.layout.simple_spinner_item,out));
                            spinner.setOnItemSelectedListener(CreateGroupPage.this);

                        }catch(JSONException e){
                            //to be done later
                            out.add("ERROR: DATA GRABBED WAS NOT JSON");
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        out.add("ERROR: COULD NOT CONNECT TO API");
                    }
                });
        q.add(arrReq);
        return out;
    }
}
