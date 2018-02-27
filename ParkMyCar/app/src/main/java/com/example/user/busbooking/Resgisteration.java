package com.example.user.busbooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class Resgisteration extends AppCompatActivity {
    EditText name,email,pass,cpass,phone,address;
    Button register, login;
    AlertDialog.Builder builder;
    //String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String emailinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgisteration);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        cpass = (EditText) findViewById(R.id.conform_password);
        phone = (EditText) findViewById(R.id.phone);
        address = (EditText) findViewById(R.id.address);
        register = (Button) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login);
        emailinput = email.getText().toString().trim();



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().equals("")||email.getText().toString().equals("")||pass.getText().toString().equals("")||cpass.getText().toString().equals("")||phone.getText().toString().equals("")||address.getText().toString().equals("")){

                    builder = new AlertDialog.Builder(Resgisteration.this);
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
                else if (!(pass.getText().toString().equals(cpass.getText().toString()))){

                    builder = new AlertDialog.Builder(Resgisteration.this);
                    builder.setTitle("Error");
                    builder.setMessage("password not matching");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            pass.setText("");
                            cpass.setText("");

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }
                else {
                    BackgroundTask backgroundTask = new BackgroundTask(Resgisteration.this);
                    backgroundTask.execute("register",name.getText().toString(),email.getText().toString(),pass.getText().toString(),phone.getText().toString(),address.getText().toString());


                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Resgisteration.this,Login.class);
                startActivity(intent);
            }
        });




    }

}
