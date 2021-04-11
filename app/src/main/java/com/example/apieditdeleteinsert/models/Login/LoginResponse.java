package com.example.apieditdeleteinsert.models.Login;

import com.example.apieditdeleteinsert.models.User.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {




    @SerializedName("message")
    @Expose
    String errorMessage;

    @SerializedName("state")
    @Expose
    private Boolean Success;


    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("access_token")
    @Expose
    private   String accessToken;

    public Boolean isSuccess() {
        return Success;
    }

    public void setSuccess(Boolean Success) {
        this.Success = Success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public  String getAccessToken() {
        return accessToken;
    }

    public   void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

