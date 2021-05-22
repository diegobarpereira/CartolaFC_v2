package com.diegopereira.cartolafc.league;

import com.diegopereira.cartolafc.teste.Players;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface APIInterface {

    @Headers({"Cache-Control: no-cache"})

    @GET("/auth/liga/{slug}")


    Call<League> getTime(@Header("X-GLB-TOKEN") String token, @Path("slug") String slug);





}