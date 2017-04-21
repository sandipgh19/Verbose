package com.example.sandipghosh.verbose;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import static android.R.attr.x;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {

    TextView txt1,txt2,txt3,txt4, register,logotext;
    Button login;
    ImageButton facebook,google;
    SharedPreferences sharedPreferences;
    Intent intent;
    ImageButton imgButton;
    TextView textView;

    private GoogleApiClient mGoogleApiClient;

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private static final int RC_SIGN_IN = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
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

        facebookLogin();

        facebook = (ImageButton) findViewById(R.id.facebook);
        google = (ImageButton) findViewById(R.id.google);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        register.setOnClickListener(this);
        login.setOnClickListener(this);

        google.setOnClickListener(this);
        facebook.setOnClickListener(this);


    }

    private void facebookLogin() {


        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));

        loginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        String accessToken = loginResult.getAccessToken()
                                .getToken();
                        Log.i("accessToken", accessToken);

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object,
                                                            GraphResponse response) {

                                        String id;
                                        String email;
                                        String name;
                                        String gender;
                                        String birthday;

                                        Log.i("LoginActivity",
                                                response.toString());
                                        try {
                                            id = object.getString("id");
                                            try {
                                                URL profile_pic = new URL(
                                                        "http://graph.facebook.com/" + id + "/picture?type=large");
                                                Log.i("profile_pic",
                                                        profile_pic + "");

                                            } catch (MalformedURLException e) {
                                                e.printStackTrace();
                                            }
                                            name = object.getString("name");
                                            // Log.d(TAG, name);
                                            email = object.getString("email");
                                            gender = object.getString("gender");
                                            birthday = object.getString("birthday");

                                            // Log.d(TAG, name + "\n" + id + "\n" + email + "\n" + gender + "\n" + birthday);

                                            String Email = email;
                                            String Name = name;
                                            String Password = "";


                                           data(name);


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields",
                                "id,name,email,gender, birthday");
                        request.setParameters(parameters);
                        request.executeAsync();

                    }

                    //Toast.makeText(this,"Welcome",Toast.LENGTH_LONG).show();

                    @Override
                    public void onCancel() {
                        System.out.println("onCancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        System.out.println("onError");
                        Log.v("LoginActivity", exception.getCause().toString());
                    }

                });

    }

    private void googleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //If signin
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            handleSignInResult(result);
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);


    }

    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();

            String Email = acct.getEmail();
            String Name = acct.getDisplayName();
            String Password = "";

            data(Name);


        } else {
            // Log.d(TAG, result.getStatus().toString());
        }
    }

    public void data(String name) {
        Toast.makeText(this,"Welcome "+name,Toast.LENGTH_LONG).show();
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
        } else if(id == R.id.facebook) {
            loginButton.performClick();


        } else if(id == R.id.google) {

            googleSignIn();

        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
