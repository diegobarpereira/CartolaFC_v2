package com.diegopereira.cartolafc.ligaauth;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RequestInterface {

    @Headers({"Cache-Control: no-cache"})

    @GET("/auth/ligas")
    Call<AuthLiga> getLigas(@Header("X-GLB-TOKEN") String token);

}
