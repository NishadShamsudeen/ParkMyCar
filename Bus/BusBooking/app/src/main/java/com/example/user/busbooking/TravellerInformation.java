package com.example.user.busbooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TravellerInformation extends AppCompatActivity {

    final String TAG = this.getClass().getSimpleName();
    final String TAG2 = this.getClass().getSimpleName();
    String busname,seatno,amount,busorigin,busdestination,bustime,username;
    TextView bsname,origin,destination,dtime,seats,totalfare;
    EditText pname,page,pmobile,pemail;
    AlertDialog.Builder builder;
    Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traveller_information);
        seatno = getIntent().getStringExtra("seats");
        amount = getIntent().getStringExtra("amount");
        busname = getIntent().getStringExtra("busnamee");
        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        username = prefs.getString("name", "UNKNOWN");
        Log.d(TAG,username);

        bsname = (TextView)findViewById(R.id.busname);
        origin = (TextView)findViewById(R.id.busorigin);
        destination = (TextView)findViewById(R.id.busdestination);
        dtime = (TextView)findViewById(R.id.busdepature);
        seats = (TextView)findViewById(R.id.seatno);
        totalfare = (TextView)findViewById(R.id.totalamount);
        pname = (EditText)findViewById(R.id.pname);
        page = (EditText)findViewById(R.id.page);
        pmobile = (EditText)findViewById(R.id.pphone);
        pemail = (EditText)findViewById(R.id.pemail);
        pay = (Button)findViewById(R.id.bookb);

        String url = "http://192.168.43.240/busbooking/ticketbookings.php";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG,response);
                try {
                    JSONArray businfo = new JSONArray(response);
                    for(int i=0; i < businfo.length(); i++) {
                        JSONObject jsonobject = businfo.getJSONObject(i);
                        busorigin = jsonobject.getString("origin");
                        busdestination = jsonobject.getString("destination");
                        bustime = jsonobject.getString("depature");
                        origin.setText(busorigin);
                        destination.setText(busdestination);
                        dtime.setText(bustime);
                        //Toast.makeText(TravellerInformation.this,busorigin,Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


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

        bsname.setText(busname);
        seats.setText(seatno);
        totalfare.setText(amount);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "http://192.168.43.240/busbooking/ticketbookingclick.php";
                final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response2) {
                        Log.d(TAG2,response2);
                        if (response2.contains("success")){

                            builder = new AlertDialog.Builder(TravellerInformation.this);
                            builder.setTitle("Booking Success");
                            builder.setMessage("Your Booking is Successful!!");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(TravellerInformation.this,Home.class);
                                    startActivity(intent);

                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();

                        }
                        else {


                            //Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                        }

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
                        params.put("pname",pname.getText().toString());
                        params.put("page",page.getText().toString());
                        params.put("mobile",pmobile.getText().toString());
                        params.put("email",pemail.getText().toString());
                        params.put("username",username);
                        return params;
                    }


                };

                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

            }
        });



    }
}
