package com.example.apieditdeleteinsert.models.Note;

import com.google.gson.annotations.SerializedName;

public class AddNoteRequest {
    @SerializedName("title")
    String title;
    @SerializedName("body")
    String bodey;

    public AddNoteRequest(String title, String bodey) {
        this.title = title;
        this.bodey = bodey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBodey() {
        return bodey;
    }

    public void setBodey(String bodey) {
        this.bodey = bodey;
    }
}
