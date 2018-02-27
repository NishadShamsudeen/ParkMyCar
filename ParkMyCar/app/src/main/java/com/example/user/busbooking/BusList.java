package com.example.user.busbooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.amigold.fundapter.interfaces.DynamicImageLoader;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kosalgeek.android.json.JsonConverter;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BusList extends AppCompatActivity implements Response.Listener<String> {


    String origin,destination,date;
    final String TAG = this.getClass().getSimpleName();
    ListView listView;
    TextView displaydate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);
        origin = getIntent().getStringExtra("origina");
        destination = getIntent().getStringExtra("destinationa");
        date = getIntent().getStringExtra("datea");
        displaydate = (TextView)findViewById(R.id.data_date);
        displaydate.setText(date);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(origin + " to " + destination);

        //Toast.makeText(BusList.this," "+origin+" "+destination+" "+date,Toast.LENGTH_SHORT).show();
        String url = "http://192.168.43.240/busbooking/buslist.php";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,this, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error while reading",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("origin",origin);
                params.put("destination",destination);
                params.put("dofjourney",date);
                return params;
            }


        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        listView = (ListView)findViewById(R.id.lst1);
    }

    @Override
    public void onResponse(String response) {
        Log.d(TAG,response);
        //Toast.makeText(BusList.this,response,Toast.LENGTH_SHORT).show();
        ArrayList<Buses> jsonObject = new JsonConverter<Buses>().toArrayList(response,Buses.class);
        BindDictionary<Buses> dictionary = new BindDictionary<>();
        dictionary.addStringField(R.id.mall_name, new StringExtractor<Buses>() {
            @Override
            public String getStringValue(Buses buses, int position) {
                return buses.busname;
            }
        });

        dictionary.addStringField(R.id.bustype, new StringExtractor<Buses>() {
            @Override
            public String getStringValue(Buses buses, int position) {
                return buses.bustype;
            }
        });

        dictionary.addStringField(R.id.depature_time, new StringExtractor<Buses>() {
            @Override
            public String getStringValue(Buses buses, int position) {
                return buses.depature;
            }
        });

        dictionary.addStringField(R.id.arrival_time, new StringExtractor<Buses>() {
            @Override
            public String getStringValue(Buses buses, int position) {
                return buses.arrival;
            }
        });

        dictionary.addStringField(R.id.bus_seatno, new StringExtractor<Buses>() {
            @Override
            public String getStringValue(Buses buses, int position) {
                return buses.seats;
            }
        });

        dictionary.addStringField(R.id.mall_type, new StringExtractor<Buses>() {
            @Override
            public String getStringValue(Buses buses, int position) {
                return buses.fare;
            }
        });

        dictionary.addStringField(R.id.journey_time, new StringExtractor<Buses>() {
            @Override
            public String getStringValue(Buses buses, int position) {
                return buses.journeytime;
            }
        });

        dictionary.addDynamicImageField(R.id.busimage, new StringExtractor<Buses>() {
            @Override
            public String getStringValue(Buses buses, int position) {
                return buses.image_url;
            }
        }, new DynamicImageLoader() {
            @Override
            public void loadImage(String url, ImageView imageView) {
                Picasso.with(getApplicationContext()).load("http://192.168.43.240/busbooking/"+ url).into(imageView);

            }
        });

        FunDapter<Buses> funDapter = new FunDapter<>(getApplicationContext(),jsonObject,R.layout.layout_bus_custom,dictionary);
        listView.setAdapter(funDapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String selected =  ((TextView)view.findViewById(R.id.mall_name)).getText().toString();

                Intent intent = new Intent(BusList.this,BusSeatSelection.class);
                intent.putExtra("busname",selected);
                startActivity(intent);

            }
        });



    }
}
