package com.diegopereira.cartolafc.favoritos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RequestInterface {
    @GET("/time/id/{time_id}")
    Call<Players> getTime(@Path("time_id") String nome);
}
