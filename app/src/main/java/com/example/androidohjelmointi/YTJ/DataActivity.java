package com.example.androidohjelmointi.YTJ;

import static com.example.androidohjelmointi.ui.home.HomeFragment.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidohjelmointi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {

    String businessID;
    String name;
    String registrationDate;
    String companyForm;
    private String url = "http://avoindata.prh.fi/bis/v1?totalResults=false&maxResults=10&resultsFrom=0&name=&companyRegistrationFrom=2014-02-28;";
    private  String searchTerm;
    RequestQueue requestQueue;
    private ProgressBar eProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Bundle extras = getIntent().getExtras();
        String value1 = extras.getString("Search text");
        if(extras == null){
            return;
        }
        if(value1 != null){
            url= "http://avoindata.prh.fi/bis/v1?totalResults=false&maxResults=10&resultsFrom=0&name=" + value1 + "&companyRegistrationFrom=2014-02-28;";

        }
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Log.e(TAG, "testi");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                    Log.i(TAG, "Response" + response.toString());
                        ArrayList<Item> itemList = new ArrayList<Item>();
                       try {
                           JSONArray responseItems = (JSONArray) response.getJSONArray("results");
                           eProgressBar.setMax(responseItems.length());
                           for (int i = 0; i < responseItems.length(); i ++){
                               JSONObject currentItem = responseItems.getJSONObject(i);
                               Log.e(TAG,"Response" + currentItem.getString("name"));
                           }
                       } catch (JSONException e) {
                           throw new RuntimeException(e);
                       }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        requestQueue.add(jsonObjectRequest);
    }
}