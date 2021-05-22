package com.diegopereira.cartolafc.destaques;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("/mercado/destaques")
    Call<List<Destaques>> getDestaques();
}