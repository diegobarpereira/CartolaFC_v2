package com.diegopereira.cartolafc.league;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class Parciais implements Comparable<Parciais> {
    @SerializedName("url_escudo_png")
    @Expose
    private String url_escudo_png;


    @SerializedName("time_id")
    @Expose
    private Integer time_id;

    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("nome_cartola")
    @Expose
    private String nome_cartola;
    @SerializedName("slug")
    @Expose
    private String slug;

    @SerializedName("patrimonio")
    @Expose
    private Double patrimonio;
    @SerializedName("ranking")
    @Expose
    private Integer ranking;
    @SerializedName("pontosParciais")
    @Expose
    private Double pontosParciais;
    @SerializedName("pontosCampeonato")
    @Expose
    private Double pontosCampeonato;
    @SerializedName("variacao")
    @Expose
    private Integer variacao;

    public String getUrl_escudo_png() {
        return url_escudo_png;
    }

    public void setUrl_escudo_png(String url_escudo_png) {
        this.url_escudo_png = url_escudo_png;
    }

    public Integer getTime_id() {
        return time_id;
    }

    public void setTime_id(Integer time_id) {
        this.time_id = time_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome_cartola() {
        return nome_cartola;
    }

    public void setNome_cartola(String nome_cartola) {
        this.nome_cartola = nome_cartola;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Double getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Double patrimonio) {
        this.patrimonio = patrimonio;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Double getPontosParciais() {
        return pontosParciais;
    }

    public void setPontosParciais(Double pontosParciais) {
        this.pontosParciais = pontosParciais;
    }

    public Double getPontosCampeonato() {
        return pontosCampeonato;
    }

    public void setPontosCampeonato(Double pontosCampeonato) {
        this.pontosCampeonato = pontosCampeonato;
    }

    public Integer getVariacao() {
        return variacao;
    }

    public void setVariacao(Integer variacao) {
        this.variacao = variacao;
    }


    @Override
    public int compareTo(Parciais o) {
        return Double.compare(getPontosCampeonato(), o.getPontosCampeonato());
    }

}
