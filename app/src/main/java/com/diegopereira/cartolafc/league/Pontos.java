package com.diegopereira.cartolafc.league;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pontos {
    @SerializedName("rodada")
    @Expose
    private Double rodada;
    @SerializedName("mes")
    @Expose
    private Double mes;
    @SerializedName("turno")
    @Expose
    private Double turno;
    @SerializedName("campeonato")
    @Expose
    private Double campeonato;
    @SerializedName("capitao")
    @Expose
    private Integer capitao;

    public Double getRodada() {
        return rodada;
    }

    public void setRodada(Double rodada) {
        this.rodada = rodada;
    }

    public Double getMes() {
        return mes;
    }

    public void setMes(Double mes) {
        this.mes = mes;
    }

    public Double getTurno() {
        return turno;
    }

    public void setTurno(Double turno) {
        this.turno = turno;
    }

    public Double getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Double campeonato) {
        this.campeonato = campeonato;
    }

    public Integer getCapitao() {
        return capitao;
    }

    public void setCapitao(Integer capitao) {
        this.capitao = capitao;
    }
}
