package com.diegopereira.cartolafc.login;

import android.text.TextUtils;

import com.diegopereira.cartolafc.CONSTANTS;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LigaGenerator {
    private static Retrofit retrofit;
    private static Gson gson;

    public static Retrofit getRetrofit() {
        gson = new GsonBuilder()
                .create();

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.cartolafc.globo.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}