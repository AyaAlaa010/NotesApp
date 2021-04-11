package com.example.apieditdeleteinsert.UI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apieditdeleteinsert.Interface.InterfaceMenue;
import com.example.apieditdeleteinsert.R;
import com.example.apieditdeleteinsert.models.Note.Note;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NotesHolder> {
   List<Note> notes;
InterfaceMenue interfaceMenue ;
    public NoteAdapter(List<Note> notes,InterfaceMenue interfaceMenue) {

        this.notes = notes;
        this.interfaceMenue=interfaceMenue;
    }

    @NonNull
    @Override
    public NotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        NotesHolder myNotesHolder=new NotesHolder(view);
        return myNotesHolder;    }

    @Override
    public void onBindViewHolder(@NonNull NotesHolder holder, int position) {
        Note myNote=notes.get(position) ;
        int id= myNote.getId();
        holder.tvTitle.setText(myNote.getTitle());
        holder.tvBodey.setText(myNote.getBody());
        String title=holder.tvTitle.getText().toString();
        String bodey=holder.tvBodey.getText().toString();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceMenue.onDeleteEditeClick(myNote,v,title,bodey,id);


            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NotesHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvBodey;
        public NotesHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tv_title);
            tvBodey=itemView.findViewById(R.id.tv_bodey);
        }
    }
}
