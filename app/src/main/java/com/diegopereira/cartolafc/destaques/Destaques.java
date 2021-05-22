package com.diegopereira.cartolafc.destaques;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Destaques {
    @SerializedName("Atleta")
    @Expose
    private Atleta atleta;
    @SerializedName("escalacoes")
    @Expose
    private Integer escalacoes;
    @SerializedName("clube")
    @Expose
    private String clube;
    @SerializedName("clube_nome")
    @Expose
    private String clubeNome;
    @SerializedName("clube_id")
    @Expose
    private Integer clubeId;
    @SerializedName("escudo_clube")
    @Expose
    private String escudoClube;
    @SerializedName("posicao")
    @Expose
    private String posicao;
    @SerializedName("posicao_abreviacao")
    @Expose
    private String posicaoAbreviacao;

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }

    public Integer getEscalacoes() {
        return escalacoes;
    }

    public void setEscalacoes(Integer escalacoes) {
        this.escalacoes = escalacoes;
    }

    public String getClube() {
        return clube;
    }

    public void setClube(String clube) {
        this.clube = clube;
    }

    public String getClubeNome() {
        return clubeNome;
    }

    public void setClubeNome(String clubeNome) {
        this.clubeNome = clubeNome;
    }

    public Integer getClubeId() {
        return clubeId;
    }

    public void setClubeId(Integer clubeId) {
        this.clubeId = clubeId;
    }

    public String getEscudoClube() {
        return escudoClube;
    }

    public void setEscudoClube(String escudoClube) {
        this.escudoClube = escudoClube;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getPosicaoAbreviacao() {
        return posicaoAbreviacao;
    }

    public void setPosicaoAbreviacao(String posicaoAbreviacao) {
        this.posicaoAbreviacao = posicaoAbreviacao;
    }

}
