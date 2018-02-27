package com.example.mypc.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FindmyRide extends AppCompatActivity {
    EditText t1,t2,t3,t4;
    String str,str1,str2,str3,str4;
    Spinner select;
    private TrackGPS gps;
    double lat,lot;
    AutoCompleteTextView actv;
    String[] fruits =null;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findmy_ride);
        t1=(EditText)findViewById(R.id.destination);
        t2=(EditText)findViewById(R.id.time3);
        t3=(EditText)findViewById(R.id.time4);
        select=(Spinner)findViewById(R.id.bustype);
        actv = (AutoCompleteTextView) findViewById(R.id.source);
        b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str=actv.getText().toString();
                str1=t1.getText().toString();
                str2=t2.getText().toString();
                str3=t3.getText().toString();
                str4=select.getSelectedItem().toString().trim();
                Intent i=new Intent(FindmyRide.this,ViewBus.class);
                i.putExtra("source",str);
                i.putExtra("dest",str1);
                i.putExtra("time1",str2);
                i.putExtra("time2",str3);
                i.putExtra("type",str4);
                startActivity(i);



            }
        });
        List<String> categories = new ArrayList<String>();
        categories.add("select type--");
        categories.add("Past Passenger");
        categories.add("Super Fast");
        categories.add("Limited Stop");
        ArrayAdapter ar=new ArrayAdapter(FindmyRide.this,android.R.layout.simple_spinner_dropdown_item,categories);
        ar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select.setAdapter(ar);
        select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        try {
            gps = new TrackGPS(com.example.mypc.myapplication.FindmyRide.this);
            if (gps.canGetLocation()) {
                lat = gps.getLongitude();
                lot = gps.getLatitude();
                Toast.makeText(FindmyRide.this, lat + "result" + lot,
                        Toast.LENGTH_LONG).show();
            }
        }catch(Exception e){}
        new SendRequest().execute();

    }
    public class SendRequest extends AsyncTask<String, Void, String> {
        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {


            try{
//                Toast.makeText(Det.this,"Hai",Toast.LENGTH_LONG);

                //  lat=9.78;
                //   lot=96.67;
                SharedPreferences MyPref;
                SharedPreferences dta =getSharedPreferences("MyPref", MODE_PRIVATE);
                String id1=dta.getString("idd2",null);
                //  URL url = new URL("http://10.0.2.2:8080/test/test2.jsp");
                String urlParameters =
                        "fName=" + URLEncoder.encode("???", "UTF-8") +
                                "&lName=" + URLEncoder.encode("???", "UTF-8");

                Ip i=new Ip();
                String ip=i.getIp();
                //URL url = new URL("http://10.0.2.2:8080/women/test.jsp");
                URL url = new URL("http://"+ip+"/bus/getstop.php");
                JSONObject postDataParams = new JSONObject();
            //    postDataParams.put("rid",id1);
                postDataParams.put("lat",lat);
                postDataParams.put("lot",lot);
                //  postDataParams.put("email", "abhay@gmail.com");

                Log.e("params",postDataParams.toString());

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                // connection.setRequestProperty("Content-Length", "" +
                //Integer.toString(urlParameters.getBytes().length));
                connection.setRequestProperty("Content-Language", "en-US");
                connection.setUseCaches (false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
//Send request
                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));
                // wr.write(getPostDataString(postDataParams));
                writer.flush ();
                writer.close ();
//Get Response
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');

                    // return str;
                }
                return response.toString();
            }
            catch(Exception e){
                e.printStackTrace();
                return new String("Exception: " + e.getMessage());


            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(FindmyRide.this, result+"result",
                    Toast.LENGTH_LONG).show();
            fruits=result.split("\\*");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (FindmyRide.this, android.R.layout.select_dialog_item, fruits);
            //Getting the instance of AutoCompleteTextView
            // actv.showDropDown();
            // actv.setThreshold(0);//will start working from first character
            actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
            actv.setTextColor(Color.RED);
            actv.setOnTouchListener(new View.OnTouchListener(){

                public boolean onTouch(View v, MotionEvent event){
                    actv.showDropDown();
                    return false;
                }
            });
            // new feedback.SendRequest1().execute();
           /* Intent a = new Intent(feedback.this, Activity3.class);
            startActivity(a);*/
        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}
