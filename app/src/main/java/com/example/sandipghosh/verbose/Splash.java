package com.example.sandipghosh.verbose;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by sandipghosh on 19/04/17.
 */

public class Splash extends AppCompatActivity implements View.OnClickListener {

    ImageButton buyer,seller;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        buyer = (ImageButton) findViewById(R.id.buyer);
        seller = (ImageButton) findViewById(R.id.seller);

        sharedPreferences = this.getSharedPreferences("category", Context.MODE_PRIVATE);
        buyer.setOnClickListener(this);
        seller.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {

        int id = v.getId();

        if(id == R.id.buyer) {

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("user","buyer");
            editor.commit();

            Intent next = new Intent(this,MainActivity.class);
            startActivity(next);

        } else if(id == R.id.seller) {

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("user","seller");
            editor.commit();

            Intent next = new Intent(this,MainActivity.class);
            startActivity(next);

        }

    }
}
