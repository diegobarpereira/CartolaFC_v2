package com.diegopereira.cartolafc.teste;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {
    //@GET("/time/id/1245808") //Diego
    //@GET("/time/id/1235701") //Thiago
    //@GET("/time/id/937747") //Rodrigo
    //@GET("/time/id/7630223") //DraVascular

    @GET("/time/id/{time_id}")

    Call<Players> getTime(@Path("time_id") String nome);
    Call<Players> getPontos(@Path("time_id") Double pontos);


}