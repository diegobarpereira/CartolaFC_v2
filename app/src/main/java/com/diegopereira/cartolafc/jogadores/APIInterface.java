package com.diegopereira.cartolafc.jogadores;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("/atletas/mercado")
    Call<Jogador> getAtletas();

}