package com.example.sandipghosh.verbose;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sandipghosh on 19/04/17.
 */

public class SellerRegistration extends AppCompatActivity implements View.OnClickListener {

    EditText company,email,password,confirmPassword,service,phone;

    Button register;

    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_registration);

        register = (Button) findViewById(R.id.register);
        login = (TextView) findViewById(R.id.login);
        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {

        int id = v.getId();

        if(id == R.id.register) {

            Toast.makeText(this,"Register",Toast.LENGTH_LONG).show();
        } else if(id == R.id.login) {

            Intent pre = new Intent(this, MainActivity.class);
            startActivity(pre);
        }

    }
}
