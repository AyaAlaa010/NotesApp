package com.example.apieditdeleteinsert.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.apieditdeleteinsert.Network.NotApi;
import com.example.apieditdeleteinsert.Network.RetrofitClient;
import com.example.apieditdeleteinsert.R;
import com.example.apieditdeleteinsert.models.Login.LoginRequest;
import com.example.apieditdeleteinsert.models.Note.AddNoteRequest;
import com.example.apieditdeleteinsert.models.Note.AddNoteResponse;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddNote extends AppCompatActivity {
    TextInputEditText editBodey, editTitle;
    NotApi notApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);


        editBodey = (TextInputEditText) findViewById(R.id.body_ed);
        editTitle = (TextInputEditText) findViewById(R.id.ed_title);
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://notes.amirmohammed.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        notApi = retrofit.create(NotApi.class);
    }

    public void getDataFromUi(View view) {
        String Title=editTitle.getText().toString().trim();
        String body=editBodey.getText().toString().trim();
        if (Title.isEmpty() || body.isEmpty()) {
            Toast.makeText(AddNote.this, "please fill all data", Toast.LENGTH_LONG).show();
            return;
        }
        AddNoteRequest addRequest = new AddNoteRequest(Title, body);
        addNote(addRequest);
    }
    private void addNote(AddNoteRequest addRequest) {
      String  myToken=getSharedPreferences("notes",MODE_PRIVATE).getString("accessToken","");
        String token = "Bearer " + myToken;
        RetrofitClient.getApi().addNotes(addRequest,token).enqueue(new Callback<AddNoteResponse>() {//***
            @Override
            public void onResponse(Call<AddNoteResponse> call, Response<AddNoteResponse> response) {
                if (response.body().isSuccess()) {
                    Toast.makeText(AddNote.this, "Note Added", Toast.LENGTH_LONG).show();
                    finish();}
            }
            @Override
            public void onFailure(Call<AddNoteResponse> call, Throwable t) {
                String errorMessage = t.getLocalizedMessage();
                Toast.makeText(AddNote.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        });

    }}
