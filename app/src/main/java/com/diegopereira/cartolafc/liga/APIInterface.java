package com.diegopereira.cartolafc.liga;

import com.diegopereira.cartolafc.jogadores.Jogador;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {
    //@GET("/time/id/1245808") //Diego
    @GET("/time/id/{time_id}")

    Call<Players> getTime(@Path("time_id") String nome);



    //Call<Players> getPlayers();

}