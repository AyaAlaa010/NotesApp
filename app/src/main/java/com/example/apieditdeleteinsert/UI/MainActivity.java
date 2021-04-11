package com.example.apieditdeleteinsert.UI;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apieditdeleteinsert.Interface.InterfaceMenue;
import com.example.apieditdeleteinsert.Network.NotApi;
import com.example.apieditdeleteinsert.Network.RetrofitClient;
import com.example.apieditdeleteinsert.R;
import com.example.apieditdeleteinsert.models.Login.LoginResponse;
import com.example.apieditdeleteinsert.models.Note.AddNoteResponse;
import com.example.apieditdeleteinsert.models.Note.GetNotResponse;
import com.example.apieditdeleteinsert.models.Note.Note;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    NoteAdapter noteAdapter;
    RecyclerView recyclerView;
    List<Note> notes = new ArrayList<>();
    NotApi notApi;
    public static String valueofToken;
    private static final String TAG = "MainActivity";
    LoginResponse loginResponse = new LoginResponse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        getNotes();


    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_note);


    }

    //    private void initRetrofit() {
//
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://notes.amirmohammed.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        notApi = retrofit.create(NotApi.class);
//
//
//    }
//    Note note= new Note();
    void getNotes() {

        String myToken = getSharedPreferences("notes", MODE_PRIVATE).getString("accessToken", "");
        String token = "Bearer " + myToken;
        Log.i(TAG, "getNotes: my access token = " + token);
        RetrofitClient.getApi().getNotes(token).enqueue(new Callback<GetNotResponse>() {//**
            @Override
            public void onResponse(Call<GetNotResponse> call, Response<GetNotResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    notes = response.body().getNotes();
                    noteAdapter = new NoteAdapter(notes, interfaceMenue);
                    recyclerView.setAdapter(noteAdapter);
                }  //  noteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<GetNotResponse> call, Throwable t) {
                String errorMessage = t.getLocalizedMessage();
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void openAddNoteActivity(View view) {
        startActivity(new Intent(MainActivity.this, AddNote.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getNotes();
    }


    InterfaceMenue interfaceMenue = new InterfaceMenue() {
        @Override
        public void onDeleteEditeClick(Note note, View view, String tvTitle, String tvBody, int id) {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
            popupMenu.getMenuInflater().inflate(R.menu.popup_edit_delete, popupMenu.getMenu());
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId() == R.id.mypop_Edite) {
                        Intent intent = new Intent(MainActivity.this, EditeNoteActivity.class);
                        intent.putExtra("TITLE", tvTitle);
                        intent.putExtra("BODEY", tvBody);
                        intent.putExtra("id", id);
                        startActivity(intent);

                    } else if (item.getItemId() == R.id.mypop_delete) {
                        new AlertDialog.Builder(MainActivity.this).setMessage("are you sure to delete this article")
                                .setNegativeButton("cancle", null)
                                .setPositiveButton("delete", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String myToken = getSharedPreferences("notes", MODE_PRIVATE).getString("accessToken", "");
                                        String token = "Bearer " + myToken;
                                        RetrofitClient.getApi().deleteNote(id, token).enqueue(new Callback<AddNoteResponse>() {
                                            @Override
                                            public void onResponse(Call<AddNoteResponse> call, Response<AddNoteResponse> response) {
                                                if (response.body().isSuccess()) {
                                                    Toast.makeText(MainActivity.this, "deleted", Toast.LENGTH_LONG).show();
                                                    getNotes();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<AddNoteResponse> call, Throwable t) {
                                                String errorMessage = t.getLocalizedMessage();
                                                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }
                                }).show();

                    }

                    return false;
                }

            });
        }
    };


}
//    public void deleteNote( Note myNote){
//        notes.remove(myNote);
//        noteAdapter.notifyDataSetChanged();
//
//
//
//    }
