package com.diegopereira.cartolafc.classificacao;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserClient {

    @POST("dashboard/login")
    Call<User> login( @Body Login login );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("v1/campeonatos/10/fases/55")
    Call<ResponseBody> getSecret( @Header ("Authorization") String token);

    @GET("v1/campeonatos/10/fases/55")
    Call<Tabela> getTabela();

}
