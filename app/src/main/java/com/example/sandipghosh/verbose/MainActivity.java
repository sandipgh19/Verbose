package com.example.sandipghosh.verbose;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.x;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txt1,txt2,txt3,txt4, register,logotext;
    Button login;
    ImageButton facebook,google;
    SharedPreferences sharedPreferences;
    Intent intent;
    ImageButton imgButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imgButton = (ImageButton) findViewById(R.id.back);
        imgButton.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.tooltext);


        txt1 = (TextView) findViewById(R.id.text1);
        txt2 = (TextView) findViewById(R.id.text2);
        txt3 = (TextView) findViewById(R.id.text3);
        txt4 = (TextView) findViewById(R.id.text4);
        register = (TextView) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login);
        logotext = (TextView) findViewById(R.id.logotext);
        sharedPreferences = this.getSharedPreferences("category", Context.MODE_PRIVATE);

        if(sharedPreferences.getString("user","seller").equals("buyer")) {

            textView.setText("Buyer");

            intent = new Intent(this,BuyerRegistration.class);
            logotext.setText("LOGIN AS BUYER");
        } else {
            textView.setText("Seller");
            intent = new Intent(this,SellerRegistration.class);
            logotext.setText("LOGIN AS SELLER");
        }

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

        else if(id==R.id.register) {
            startActivity(intent);
        } else if(id == R.id.back) {
            finish();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.back:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
