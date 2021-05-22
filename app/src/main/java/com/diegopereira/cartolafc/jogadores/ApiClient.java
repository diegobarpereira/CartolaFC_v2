package com.diegopereira.cartolafc.jogadores;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL="https://api.cartolafc.globo.com";
    public static Retrofit retrofit = null;
    public static APIInterface instance=null;

    public static Retrofit getApiClient(){

        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    public static APIInterface getInterface(){

        if (instance==null){
            instance=getApiClient().create(APIInterface.class);
        }


        return instance;
    }
}
