package com.diegopereira.cartolafc.jogadores;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Jogador {
    @SerializedName("atletas")
    @Expose
    private List<Atleta> atletas = null;
    @SerializedName("clubes")
    @Expose
    private Map<Integer, Clubes> clubes;
    @SerializedName("posicoes")
    @Expose
    private Map<Integer, Posicoes> posicoes;
    @SerializedName("status")
    @Expose
    private Map<Integer, Status> status;

    public List<Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(List<Atleta> atletas) {
        this.atletas = atletas;
    }

    public Map<Integer, Clubes> getClubes() {
        return clubes;
    }

    public void setClubes( Map<Integer, Clubes> clubes ) {
        this.clubes = clubes;
    }

    public Map<Integer, Posicoes> getPosicoes() {
        return posicoes;
    }

    public void setPosicoes( Map<Integer, Posicoes> posicoes ) {
        this.posicoes = posicoes;
    }

    public Map<Integer, Status> getStatus() {
        return status;
    }

    public void setStatus( Map<Integer, Status> status ) {
        this.status = status;
    }
}