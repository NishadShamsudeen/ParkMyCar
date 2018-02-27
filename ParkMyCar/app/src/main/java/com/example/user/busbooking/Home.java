package com.example.user.busbooking;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    Button busesearch,bookingd,logout,feedbackb;
    TextView namedisplay,emaildisplay;
    private String namea,emaila;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Home");

        namedisplay = (TextView)findViewById(R.id.namedisplay);
        emaildisplay = (TextView)findViewById(R.id.emaildisplay);
        busesearch = (Button)findViewById(R.id.bus_search);
        bookingd = (Button)findViewById(R.id.booking_details);
        feedbackb = (Button)findViewById(R.id.feedback);
        logout = (Button)findViewById(R.id.logout);
        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        namea = prefs.getString("name", "UNKNOWN");
        emaila =prefs.getString("email","UNKNOWN");
        Toast.makeText(Home.this,"Welcome "+namea,Toast.LENGTH_SHORT).show();
        namedisplay.setText("Hi, "+namea);
        emaildisplay.setText(emaila);
        busesearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,BusSearch.class);
                startActivity(intent);
            }
        });

        bookingd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,BookedTickets.class);
                intent.putExtra("name",namea);
                startActivity(intent);

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder = new AlertDialog.Builder(Home.this);
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Intent intent = new Intent(Home.this,Login.class);
                        startActivity(intent);

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        feedbackb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,FeedBack.class);
                intent.putExtra("name",namea);
                intent.putExtra("email",emaila);
                startActivity(intent);

            }
        });




    }
}
