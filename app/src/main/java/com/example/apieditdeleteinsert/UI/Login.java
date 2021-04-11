package com.example.apieditdeleteinsert.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.apieditdeleteinsert.Network.NotApi;
import com.example.apieditdeleteinsert.Network.RetrofitClient;
import com.example.apieditdeleteinsert.R;
import com.example.apieditdeleteinsert.models.Login.LoginRequest;
import com.example.apieditdeleteinsert.models.Login.LoginResponse;
import com.example.apieditdeleteinsert.models.Registration.RegisterRequest;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    TextInputEditText loginEtEmail, loginEtPassword;
    NotApi notApi;
   static String myToken;
    private static final String TAG = "Login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEtEmail = (TextInputEditText) findViewById(R.id.login_ed_email);
        loginEtPassword = (TextInputEditText) findViewById(R.id.login_ed_password);

//        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://notes.amirmohammed.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        notApi = retrofit.create(NotApi.class);


    }

    public void getDataFromUi(View view) {
        String email = loginEtEmail.getText().toString().trim();
        String password = loginEtPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(Login.this, "please fill all data", Toast.LENGTH_LONG).show();
            return;
        }
        LoginRequest loginRequest = new LoginRequest(email, password);
        login(loginRequest);
    }

    private void login(LoginRequest loginRequest) {
         myToken = getSharedPreferences("notes", MODE_PRIVATE).getString("accessToken", "");

            RetrofitClient.getApi().login(loginRequest).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.body().isSuccess()) {
                        // String token="Bearer"  + MainActivity.accessToken;///***************
                        myToken=response.body().getAccessToken();
                        SharedPreferences preferences=getSharedPreferences("notes", Context.MODE_PRIVATE);
                        preferences.edit().putString("accessToken",myToken).apply();
                        myToken = getSharedPreferences("notes", MODE_PRIVATE).getString("accessToken", "");
                        String token = "Bearer " + myToken;
                        Log.i(TAG, "onCreate:my token =  "+token);
                       // Log.i(TAG, "onResponse: xxxxxxxxxxxxx"+myToken);
//                        if(myToken.isEmpty()){
//
//                            Toast.makeText(Login.this,"token empty",Toast.LENGTH_LONG).show();
//
//                        }

                        myToken = response.body().getAccessToken();//************** the old  Mainactivity.accesstoken
                        startActivity(new Intent(Login.this, MainActivity.class));
                        finish();
                    }//***********************
                    else {
                        Toast.makeText(Login.this, response.body().getErrorMessage(), Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    String errorMessage = t.getLocalizedMessage();
                    Toast.makeText(Login.this, errorMessage, Toast.LENGTH_LONG).show();

                }
            });


        }

    public void openRegister(View view) {
        startActivity(new Intent(Login.this, Register.class));

    }
}