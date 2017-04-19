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

public class BuyerRegistration extends AppCompatActivity implements View.OnClickListener {

    EditText name,email,password,confirmPassword,city,phone;
    Button register;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_registration);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        city = (EditText) findViewById(R.id.city);
        phone = (EditText) findViewById(R.id.phone);

        register = (Button) findViewById(R.id.register);

        login = (TextView) findViewById(R.id.login);

        register.setOnClickListener(this);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id==R.id.login) {

            Intent pre = new Intent(this,MainActivity.class);
            startActivity(pre);
        } else if(id == R.id.register) {

            Toast.makeText(this,"Register",Toast.LENGTH_LONG).show();
        }
    }
}
