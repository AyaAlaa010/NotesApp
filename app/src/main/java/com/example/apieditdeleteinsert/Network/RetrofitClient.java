package com.example.apieditdeleteinsert.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private RetrofitClient(){}
    private static Retrofit retrofit;
    public static NotApi getApi() {
        if (retrofit == null) {
            retrofit=new Retrofit.Builder().baseUrl("https://notes.amirmohammed.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit.create(NotApi.class);
    }



}
