package com.diegopereira.cartolafc.league;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Teste {
    @SerializedName("time_id")
    @Expose
    private Integer time_id;
    @SerializedName("atleta_id")
    @Expose
    private Integer atleta_id;
    @SerializedName("capitao_id")
    @Expose
    private Integer capitaoId;
    @SerializedName("apelido")
    @Expose
    private String apelido;
    @SerializedName("clube_id")
    @Expose
    private Integer clube_id;
    @SerializedName("posicao_id")
    @Expose
    private Integer posicao_id;

    public Integer getTime_id() {
        return time_id;
    }

    public void setTime_id(Integer time_id) {
        this.time_id = time_id;
    }

    public Integer getAtleta_id() {
        return atleta_id;
    }

    public void setAtleta_id(Integer atleta_id) {
        this.atleta_id = atleta_id;
    }

    public Integer getCapitaoId() {
        return capitaoId;
    }

    public void setCapitaoId(Integer capitaoId) {
        this.capitaoId = capitaoId;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Integer getClube_id() {
        return clube_id;
    }

    public void setClube_id(Integer clube_id) {
        this.clube_id = clube_id;
    }

    public Integer getPosicao_id() {
        return posicao_id;
    }

    public void setPosicao_id(Integer posicao_id) {
        this.posicao_id = posicao_id;
    }
}
