package com.diegopereira.cartolafc.classificacao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Time {
    @SerializedName("time_id")
    @Expose
    private Integer timeId;
    @SerializedName("nome_popular")
    @Expose
    private String nomePopular;

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

    public String getNomePopular() {
        return nomePopular;
    }

    public void setNomePopular(String nomePopular) {
        this.nomePopular = nomePopular;
    }
}
