package com.diegopereira.cartolafc.league;

import com.diegopereira.cartolafc.liga.Posicoes;
import com.diegopereira.cartolafc.liga.Time;
import com.diegopereira.cartolafc.teste.Atletas;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Players {

    @SerializedName("atletas")
    @Expose
    private List<Atleta> atletas = null;

    @SerializedName("posicoes")
    @Expose
    private Posicoes posicoes;

    @SerializedName("esquema_id")
    @Expose
    private Integer esquemaId;
    @SerializedName("rodada_atual")
    @Expose
    private Integer rodadaAtual;
    @SerializedName("patrimonio")
    @Expose
    private Double patrimonio;
    @SerializedName("valor_time")
    @Expose
    private Integer valorTime;
    @SerializedName("capitao_id")
    @Expose
    private Integer capitaoId;
    @SerializedName("pontos")
    @Expose
    private Double pontos;
    @SerializedName("pontos_campeonato")
    @Expose
    private Double pontosCampeonato;
    @SerializedName("time")
    @Expose
    private Times time;

    public List<Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(List<Atleta> atletas) {
        this.atletas = atletas;
    }


    public Posicoes getPosicoes() {
        return posicoes;
    }

    public void setPosicoes(Posicoes posicoes) {
        this.posicoes = posicoes;
    }

    public Integer getEsquemaId() {
        return esquemaId;
    }

    public void setEsquemaId(Integer esquemaId) {
        this.esquemaId = esquemaId;
    }

    public Integer getRodadaAtual() {
        return rodadaAtual;
    }

    public void setRodadaAtual(Integer rodadaAtual) {
        this.rodadaAtual = rodadaAtual;
    }

    public Double getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Double patrimonio) {
        this.patrimonio = patrimonio;
    }

    public Integer getValorTime() {
        return valorTime;
    }

    public void setValorTime(Integer valorTime) {
        this.valorTime = valorTime;
    }

    public Integer getCapitaoId() {
        return capitaoId;
    }

    public void setCapitaoId(Integer capitaoId) {
        this.capitaoId = capitaoId;
    }

    public Double getPontos() {
        return pontos;
    }

    public void setPontos(Double pontos) {
        this.pontos = pontos;
    }

    public Double getPontosCampeonato() {
        return pontosCampeonato;
    }

    public void setPontosCampeonato(Double pontosCampeonato) {
        this.pontosCampeonato = pontosCampeonato;
    }

    public Times getTime() {
        return time;
    }

    public void setTime(Times time) {
        this.time = time;
    }

}

