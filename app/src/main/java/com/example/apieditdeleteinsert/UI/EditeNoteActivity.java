 package com.example.apieditdeleteinsert.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apieditdeleteinsert.Network.NotApi;
import com.example.apieditdeleteinsert.Network.RetrofitClient;
import com.example.apieditdeleteinsert.R;
import com.example.apieditdeleteinsert.models.Note.AddNoteRequest;
import com.example.apieditdeleteinsert.models.Note.AddNoteResponse;
import com.example.apieditdeleteinsert.models.Note.Note;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

public class EditeNoteActivity extends AppCompatActivity {
    TextInputEditText editBodey, editTitle;
    NotApi notApi;
    Note note= new Note();
    private static final String TAG = "EditeNoteActivity";
    int myId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_note);
        editBodey = (TextInputEditText) findViewById(R.id.edit_bodey_edd);
        editTitle = (TextInputEditText) findViewById(R.id.edit_title_edd);
        Intent intent=getIntent();
          String noteTitle=intent.getStringExtra("TITLE");
        String  noteBodey=intent.getStringExtra("BODEY");
        myId=intent.getIntExtra("id",0);
        editBodey.setText(noteBodey);
        editTitle.setText(noteTitle);
    }

    public void getDataFromUi(View view) {
      String Title=editTitle.getText().toString().trim();
        String body=editBodey.getText().toString().trim();
        if (Title.isEmpty() || body.isEmpty()) {
            Toast.makeText(EditeNoteActivity.this, "please fill all data", Toast.LENGTH_LONG).show();
            return;
        }

        AddNoteRequest editRequest = new AddNoteRequest(Title, body);
        editNote(editRequest);

    }
    private void editNote(AddNoteRequest editRequest) {
      String  myToken = getSharedPreferences("notes", MODE_PRIVATE).getString("accessToken", "");
        String token = "Bearer " + myToken;
        Log.i(TAG, "editNote: my id = "+myId);
        RetrofitClient.getApi().editNote(editRequest,token,myId).enqueue(new Callback<AddNoteResponse>() {//****
            @Override
            public void onResponse(Call<AddNoteResponse> call, Response<AddNoteResponse> response) {
              Toast.makeText(EditeNoteActivity.this,"  success",Toast.LENGTH_LONG).show();
            // startActivity(new Intent(EditeNoteActivity.this,MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<AddNoteResponse> call, Throwable t) {
                String errorMessage = t.getLocalizedMessage();
                Toast.makeText(EditeNoteActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        });






    }



}