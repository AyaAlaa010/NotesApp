package com.example.apieditdeleteinsert.models.Note;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetNotResponse {

    @SerializedName("state")
  private boolean success;
    @SerializedName("notes")
    private List<Note> notes;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
