package com.diegopereira.cartolafc.league;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Variacao {

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
    private String capitao;

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

    public String getCapitao() {
        return capitao;
    }

    public void setCapitao(String capitao) {
        this.capitao = capitao;
    }
}
