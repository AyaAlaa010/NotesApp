package com.example.apieditdeleteinsert.models.Note;

import com.google.gson.annotations.SerializedName;

public class AddNoteResponse {
    @SerializedName("state")
   private Boolean success;

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
