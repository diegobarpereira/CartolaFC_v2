package com.diegopereira.cartolafc.partidas;

import com.diegopereira.cartolafc.liga.Players;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RequestInterface {


    //@GET("/partidas")
    //Call<Example> getPartidas();

    @GET("/partidas/{rodada}")
    Call<Example> getPartidas( @Path("rodada") String rod);

}
