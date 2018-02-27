package com.example.user.busbooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BusSeatSelection extends AppCompatActivity {
    final String TAG = this.getClass().getSimpleName();
    WebView webView;
    String busname;
    FetchDetails fetchDetails = new FetchDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_seat_selection);
        webView = (WebView)findViewById(R.id.seat_selection);
        busname = getIntent().getStringExtra("busname");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Seat Selection");
        //Toast.makeText(BusSeatSelection.this,busname,Toast.LENGTH_SHORT).show();
        webView.loadUrl("http://192.168.43.240/busbooking/index.php");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVerticalScrollBarEnabled(true);
        webView.addJavascriptInterface(fetchDetails,"ob");
        webView.setWebChromeClient(new WebChromeClient());
        Toast.makeText(BusSeatSelection.this,busname,Toast.LENGTH_LONG).show();

        String url = "http://192.168.43.240/busbooking/busbookingamount.php";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG,response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error while reading",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("busname",busname);
                return params;
            }


        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);


    }

    public class FetchDetails{
        @android.webkit.JavascriptInterface
        public void fetchString(String fruits,String amount){

            String seats = fruits;
            String totalAmount = amount;
            //Log.v("test2", "arr:"+ Arrays.toString(fruits));

            Intent intent = new Intent(BusSeatSelection.this,TravellerInformation.class);
            intent.putExtra("seats",seats);
            intent.putExtra("amount",totalAmount);
            intent.putExtra("busnamee",busname);
            startActivity(intent);


            //Toast.makeText(BusSeatSelection.this,""+fruits+""+amount,Toast.LENGTH_LONG).show();
        }

    }





}
