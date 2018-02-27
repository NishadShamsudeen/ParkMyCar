package com.example.user.busbooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookedTickets extends AppCompatActivity {
    String namea;
    final String TAG = this.getClass().getSimpleName();
    ListView listView;
    String[] data,pname;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_tickets);
        listView = (ListView)findViewById(R.id.listview);
        namea = getIntent().getStringExtra("name");
        //Toast.makeText(BookedTickets.this,namea,Toast.LENGTH_SHORT).show();

        String url = "http://192.168.43.240/busbooking/bookedtickets.php";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG,response);
                if (response.equals("nobookings")){

                    builder = new AlertDialog.Builder(BookedTickets.this);
                    builder.setTitle("Message");
                    builder.setMessage("Sorry, no tickets booked from this account so far!!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Intent intent = new Intent(BookedTickets.this,Home.class);
                            startActivity(intent);

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();



                }
                else {


                    //Toast.makeText(BookedTickets.this,"resultlist",Toast.LENGTH_SHORT).show();

                    try {
                        final JSONArray ja = new JSONArray(response);
                        JSONObject jo = null;
                        data = new String[ja.length()];
                        pname = new String[ja.length()];
                        for (int i=0;i<ja.length();i++){
                            jo = ja.getJSONObject(i);
                            data[i]=jo.getString("bookingid");
                            pname[i]=jo.getString("pname");
                            List<String> a = Arrays.asList(pname);
                            final ArrayList<String> updatableList = new ArrayList<String>();
                            updatableList.addAll(a);
                            adapter = new ArrayAdapter<String>(BookedTickets.this,android.R.layout.simple_list_item_1,updatableList);
                            listView.setAdapter(adapter);
                            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                @Override
                                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    final int positionToRemove = i;
                                    builder = new AlertDialog.Builder(BookedTickets.this);
                                    builder.setTitle("Cancel Ticket?");
                                    builder.setMessage("Are you sure you want to delete " + positionToRemove);
                                    builder.setNegativeButton("Cancel",null);

                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, final int i) {
                                            dialogInterface.dismiss();
                                            updatableList.remove(positionToRemove);
                                            adapter.notifyDataSetChanged();
                                            Toast.makeText(BookedTickets.this,"Ticket cancelled successfully",Toast.LENGTH_SHORT).show();
                                            String url = "http://192.168.43.240/busbooking/bookedticketsdelete.php";
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
                                                    for (int i=0;i<ja.length();i++){

                                                        params.put("id",data[i]);
                                                        Log.d(TAG,data[i]);


                                                    }
                                                    return params;

                                                }


                                            };

                                            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

                                        }
                                    });
                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
                                    return false;
                                }
                            });
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



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
                params.put("name",namea);
                Log.d(TAG,namea);
                return params;
            }


        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);


    }
}
