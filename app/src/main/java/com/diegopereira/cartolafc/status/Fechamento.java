package com.diegopereira.cartolafc.status;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fechamento {
    @SerializedName("dia")
    @Expose
    private Integer dia;
    @SerializedName("mes")
    @Expose
    private Integer mes;
    @SerializedName("ano")
    @Expose
    private Integer ano;
    @SerializedName("hora")
    @Expose
    private Integer hora;
    @SerializedName("minuto")
    @Expose
    private Integer minuto;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getHora() {
        return hora;
    }

    public void setHora(Integer hora) {
        this.hora = hora;
    }

    public Integer getMinuto() {
        return minuto;
    }

    public void setMinuto(Integer minuto) {
        this.minuto = minuto;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }
}
