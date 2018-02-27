package com.example.user.busbooking;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BusSearch extends AppCompatActivity {

    final String TAG = this.getClass().getSimpleName();

    EditText origin,destination,date;
    AlertDialog.Builder builder;
    Button search;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        origin = (EditText)findViewById(R.id.origin);
        destination = (EditText)findViewById(R.id.destination);
        search = (Button) findViewById(R.id.searchbutton);

        date = (EditText)findViewById(R.id.date);
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();

            }
        };

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(BusSearch.this, dateSetListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });






        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (origin.getText().toString().equals("")||destination.getText().toString().equals("")||date.getText().toString().equals("")){

                    builder = new AlertDialog.Builder(BusSearch.this);
                    builder.setTitle("Error");
                    builder.setMessage("Please fill all the fields");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }
                else {
                    String url = "http://192.168.43.240/busbooking/busselection.php";
                    final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG,response);
                            if (response.contains("buses_list")){
                                Intent intent = new Intent(BusSearch.this,BusList.class);
                                intent.putExtra("origina",origin.getText().toString());
                                intent.putExtra("destinationa",destination.getText().toString());
                                intent.putExtra("datea",date.getText().toString());
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"no buses",Toast.LENGTH_SHORT).show();
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
                            params.put("origin",origin.getText().toString());
                            params.put("destination",destination.getText().toString());
                            params.put("dofjourney",date.getText().toString());
                            return params;
                        }


                    };

                    MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);



                }
            }
        });







    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date.setText(sdf.format(myCalendar.getTime()));
    }

}
