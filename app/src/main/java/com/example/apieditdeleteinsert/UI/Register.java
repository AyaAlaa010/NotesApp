package com.example.apieditdeleteinsert.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.apieditdeleteinsert.Network.NotApi;
import com.example.apieditdeleteinsert.Network.RetrofitClient;
import com.example.apieditdeleteinsert.R;
import com.example.apieditdeleteinsert.models.Registration.RegisterRequest;
import com.example.apieditdeleteinsert.models.Registration.RegisterResponse;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
    TextInputEditText textName, textPassword, textEmail, textConfirmPassword;
    NotApi notApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textName = (TextInputEditText) findViewById(R.id.ed_name);
        textPassword = (TextInputEditText) findViewById(R.id.ed_password);
        textEmail = (TextInputEditText) findViewById(R.id.ed_email);
        textConfirmPassword = (TextInputEditText) findViewById(R.id.ed_confirm);

//        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://notes.amirmohammed.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        notApi = retrofit.create(NotApi.class);


    }

    public void getDataFromUi(View view) {
        String name = textName.getText().toString().trim();
        String email = textEmail.getText().toString().trim();
        String password = textPassword.getText().toString().trim();
        String confirmPassword = textConfirmPassword.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(Register.this, "please fill all data", Toast.LENGTH_LONG).show();
            return;
        }
        RegisterRequest registerRequest = new RegisterRequest(name, email, password, confirmPassword);
        register(registerRequest);


    }

    private void register(RegisterRequest registerRequest) {
        RetrofitClient.getApi().register(registerRequest).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.body().isSuccess()) {
                    Toast.makeText(Register.this, "Acount Created", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(Register.this, response.body().getErrorMessage(), Toast.LENGTH_LONG).show();


                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String errorMessage = t.getLocalizedMessage();
                Toast.makeText(Register.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }
}