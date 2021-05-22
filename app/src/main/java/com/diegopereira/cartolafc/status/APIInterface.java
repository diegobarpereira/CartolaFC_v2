package com.diegopereira.cartolafc.status;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("/mercado/status")
    //@GET("/b/Z6W6")
    Call<Status> getStatus();

}