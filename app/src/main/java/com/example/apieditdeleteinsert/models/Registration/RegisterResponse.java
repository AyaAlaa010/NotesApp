package com.example.apieditdeleteinsert.models.Registration;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {
    @SerializedName("state")
    boolean isSuccess;
    @SerializedName("errors")
    String errorMessage;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public RegisterResponse(boolean isSuccess, String errorMessage) {
        this.isSuccess = isSuccess;
        this.errorMessage = errorMessage;
    }


}
