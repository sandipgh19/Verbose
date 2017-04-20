package com.example.sandipghosh.verbose;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sandipghosh on 19/04/17.
 */

public class Verification extends AppCompatActivity implements View.OnClickListener {

    EditText confirm;
    TextView number;
    ImageButton imgButton;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verification);

        imgButton = (ImageButton) findViewById(R.id.back);
        imgButton.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.tooltext);
        textView.setText("Verification");


        confirm = (EditText) findViewById(R.id.confirm);

        number = (TextView) findViewById(R.id.phone);

        confirm.addTextChangedListener(new TextWatcher() {
            int len = 0;

            @Override
            public void afterTextChanged(Editable s) {
                String str = confirm.getText().toString();
                if (str.length() == 3 && len < str.length()) {//len check for backspace
                    confirm.append("-");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

                String str = confirm.getText().toString();
                len = str.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }


        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.back) {
            finish();
        }

    }
}
