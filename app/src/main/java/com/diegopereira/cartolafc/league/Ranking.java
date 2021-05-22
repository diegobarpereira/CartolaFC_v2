package com.diegopereira.cartolafc.league;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ranking {
    @SerializedName("rodada")
    @Expose
    private Integer rodada;
    @SerializedName("mes")
    @Expose
    private Integer mes;
    @SerializedName("turno")
    @Expose
    private Integer turno;
    @SerializedName("campeonato")
    @Expose
    private Integer campeonato;
    @SerializedName("patrimonio")
    @Expose
    private Integer patrimonio;
    @SerializedName("capitao")
    @Expose
    private Integer capitao;

    public Integer getRodada() {
        return rodada;
    }

    public void setRodada(Integer rodada) {
        this.rodada = rodada;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getTurno() {
        return turno;
    }

    public void setTurno(Integer turno) {
        this.turno = turno;
    }

    public Integer getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Integer campeonato) {
        this.campeonato = campeonato;
    }

    public Integer getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Integer patrimonio) {
        this.patrimonio = patrimonio;
    }

    public Integer getCapitao() {
        return capitao;
    }

    public void setCapitao(Integer capitao) {
        this.capitao = capitao;
    }
}
