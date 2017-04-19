package com.example.sandipghosh.verbose;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txt1, register,logotext;
    Button login;
    ImageButton facebook,google;
    SharedPreferences sharedPreferences;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt1 = (TextView) findViewById(R.id.text1);
        register = (TextView) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login);
        logotext = (TextView) findViewById(R.id.logotext);
        sharedPreferences = this.getSharedPreferences("category", Context.MODE_PRIVATE);

        if(sharedPreferences.getString("user","seller").equals("buyer")) {

            intent = new Intent(this,BuyerRegistration.class);
            logotext.setText("LOGIN AS BUYER");
        } else {
            intent = new Intent(this,SellerRegistration.class);
            logotext.setText("LOGIN AS SELLER");
        }

        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts/Corbel.ttf");
        txt1.setTypeface(tf);
        register.setTypeface(tf);
        login.setTypeface(tf);

        facebook = (ImageButton) findViewById(R.id.facebook);
        google = (ImageButton) findViewById(R.id.google);

        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if(id==R.id.login) {
            Toast.makeText(this,"Login",Toast.LENGTH_LONG).show();
        }

        if(id==R.id.register) {
            startActivity(intent);
        }

    }
}
