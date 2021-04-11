package com.example.apieditdeleteinsert.Network;

import com.example.apieditdeleteinsert.models.Login.LoginRequest;
import com.example.apieditdeleteinsert.models.Login.LoginResponse;
import com.example.apieditdeleteinsert.models.Note.AddNoteRequest;
import com.example.apieditdeleteinsert.models.Note.AddNoteResponse;
import com.example.apieditdeleteinsert.models.Note.GetNotResponse;
import com.example.apieditdeleteinsert.models.Registration.RegisterRequest;
import com.example.apieditdeleteinsert.models.Registration.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NotApi {
    @POST("public/api/register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest); //response لback dataيرجع// request يرسل send data


    @POST("public/api/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest); //response لback dataيرجع// request يرسل send data


    @GET("public/api/notes")
    Call<GetNotResponse> getNotes(@Header("Authorization") String token);

    @POST("public/api/notes/add")
    Call<AddNoteResponse> addNotes(@Body AddNoteRequest addNoteRequest,
    @Header("authorization") String token);

    @POST("public/api/notes/edit/{noteId}")
    Call<AddNoteResponse> editNote(@Body AddNoteRequest addNoteRequest
    ,@Header("authorization")String token,
        @Path("noteId") int noteId);

    @GET("public/api/notes/delete/{noteId}")
    Call<AddNoteResponse> deleteNote(@Path("noteId") int noteId,
            @Header("authorization")String token);

}
