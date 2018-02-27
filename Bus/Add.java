package com.example.mypc.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Iterator;

public class Add extends AppCompatActivity {
    Button login;
    EditText username, password;
    String uname,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        login = (Button) findViewById(R.id.searchbtn);
        username = (EditText) findViewById(R.id.origin);
        password = (EditText) findViewById(R.id.destination);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname=username.getText().toString();
                pass=password.getText().toString();
                new SendRequest().execute();
            }
        });
    }
    public class SendRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
        }

        protected String doInBackground(String... arg0) {

            try {
                SharedPreferences MyPref;
                SharedPreferences dta =getSharedPreferences("MyPref", MODE_PRIVATE);
                String id1=dta.getString("idd2",null);

                //  URL url = new URL("http://10.0.2.2:8080/test/test2.jsp");


                //URL url = new URL("http://10.0.2.2:8080/women/test.jsp");
                Ip i = new Ip();
                String ip = i.getIp();
                URL url = new URL("http://" + ip + "/bus/addfav.php");
                JSONObject postDataParams = new JSONObject();


                postDataParams.put("uname",uname);
                postDataParams.put("pass", pass);
                postDataParams.put("id", id1);


                Log.e("params", postDataParams.toString());

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                // connection.setRequestProperty("Content-Length", "" +
                //Integer.toString(urlParameters.getBytes().length));
                connection.setRequestProperty("Content-Language", "en-US");
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
//Send request
                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));
                // wr.write(getPostDataString(postDataParams));
                writer.flush();
                writer.close();
//Get Response
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');

                }
                return response.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {
            //o=result;
            int id=0;
            String str = result.trim();
            if(str.equals("1")) {
                Toast.makeText(Add.this, "Add Favorite", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Add.this,Favourities.class);
                startActivity(i);
            }
            else
                Toast.makeText(Add.this,"Fail to Add Favorite",Toast.LENGTH_SHORT).show();
                username.setText("");
            password.setText("");


        }
    }
    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();

            //Toast.makeText(getApplicationContext(),key, Toast.LENGTH_LONG).show();
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
