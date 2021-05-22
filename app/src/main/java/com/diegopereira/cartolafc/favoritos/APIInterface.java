package com.diegopereira.cartolafc.favoritos;

import com.diegopereira.cartolafc.groups.Input;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("/time/id/{time_id}")
    Call<Players> getTime(@Path("time_id") String nome);

}