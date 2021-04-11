package com.example.apieditdeleteinsert.Interface;

import android.view.View;
import android.widget.TextView;

import com.example.apieditdeleteinsert.models.Note.Note;

public interface InterfaceMenue {
    public void onDeleteEditeClick(Note note, View view ,String tvTitle, String tvBody,int id);

}
