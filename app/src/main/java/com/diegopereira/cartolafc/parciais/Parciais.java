package com.diegopereira.cartolafc.parciais;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Parciais {
    @SerializedName("rodada")
    @Expose
    private Integer rodada;
    @SerializedName("atletas")
    @Expose
    private Map<String, Atletas> atletas;
    @SerializedName("posicoes")
    @Expose
    private Map<Integer, Posicoes> posicoes;
    @SerializedName("clubes")
    @Expose
    private Map<Integer, Clubes> clubes;

    public Integer getRodada() {
        return rodada;
    }

    public void setRodada( Integer rodada) {
        this.rodada = rodada;
    }

    public Map<String, Atletas> getAtletas() {
        return atletas;
    }

    public void setAtletas( Map<String, Atletas> atletas) {
        this.atletas = atletas;
    }



    public Map<Integer, Posicoes> getPosicoes() {
        return posicoes;
    }

    public void setPosicoes( Map<Integer, Posicoes> posicoes ) {
        this.posicoes = posicoes;
    }

    public Map<Integer, Clubes> getClubes() {
        return clubes;
    }

    public void setClubes( Map<Integer, Clubes> clubes ) {
        this.clubes = clubes;
    }
}
