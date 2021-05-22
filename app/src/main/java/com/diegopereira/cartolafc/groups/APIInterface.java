package com.diegopereira.cartolafc.groups;

import com.diegopereira.cartolafc.jogadores.Jogador;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("/times")
    Call<List<Input>> getAtletas(@Query(value = "q", encoded = true) String text);

}