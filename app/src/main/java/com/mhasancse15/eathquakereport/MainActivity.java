package com.mhasancse15.eathquakereport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mhasancse15.eathquakereport.adepter.EarthquakeAdepter;
import com.mhasancse15.eathquakereport.data.Earthquake;
import com.mhasancse15.eathquakereport.util.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EarthquakeAdepter adepter;
    private ArrayList<Earthquake> earthquakeArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.quake_list);
        downloadData();

    }

    /*-----------------download data from url---------------*/
    public void downloadData(){

        /*-----------send request for url--------*/
        JsonObjectRequest jsonEarthquakeObject = new JsonObjectRequest(Request.Method.GET, Utils.BASE_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray featuresJsonArray = response.getJSONArray("features");
                    for (int i = 0 ; i<featuresJsonArray.length() ; i++){
                        JSONObject obj =  featuresJsonArray.getJSONObject(i);
                        JSONObject propertiesObj = obj.getJSONObject("properties");

                        double mag = propertiesObj.getInt("mag");
                        String place = propertiesObj.getString("place");
                        Long time = propertiesObj.getLong("time");
                        Earthquake earthquake = new Earthquake(mag,place,time);
                        earthquakeArrayList.add(earthquake);
                        adepter = new EarthquakeAdepter(MainActivity.this,R.layout.earthquake_list_item,earthquakeArrayList);
                        listView.setAdapter(adepter);
                        adepter.notifyDataSetChanged();
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(jsonEarthquakeObject);
    }
}