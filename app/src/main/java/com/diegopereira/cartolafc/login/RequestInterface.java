package com.diegopereira.cartolafc.login;

import com.diegopereira.cartolafc.partidas.Example;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RequestInterface {

    @Headers({
            "Cache-Control: no-cache",
            "Content-Type: application/json",
            "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.89 Safari/537.36",
            "data: teste",
            "dataType: json",
    })
    @POST("/api/authentication")
    Call<ResponseBody> getLogin( @Body RequestBody body);

    @Headers({"Cache-Control: no-cache"})

    @GET("/auth/ligas")
    Call<ResponseBody> getLigas(@Header("X-GLB-TOKEN") String token);

}
