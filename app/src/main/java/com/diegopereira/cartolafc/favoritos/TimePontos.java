package com.diegopereira.cartolafc.favoritos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TimePontos  {


    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("pontos")
    @Expose
    private Double pontos;
    @SerializedName("pontosrod")
    @Expose
    private Double pontosrod;
    @SerializedName("ultima")
    @Expose
    private Double ultima;
    @SerializedName("parciais")
    @Expose
    private Double parciais;
    @SerializedName("atletas")
    @Expose
    private List<Atletas> atletas;
    @SerializedName("capitao_id")
    @Expose
    private Integer capitaoId;
    @SerializedName("url_escudo_png")
    @Expose
    private String urlEscudoPng;
    @SerializedName("patrimonio")
    @Expose
    private Double patrimonio;
    @SerializedName("time_id")
    @Expose
    private Integer timeId;

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Double getParciais() {
        return parciais;
    }

    public void setParciais(Double parciais) {
        this.parciais = parciais;
    }

    public Double getPontosrod() {
        return pontosrod;
    }

    public void setPontosrod( Double pontosrod ) {
        this.pontosrod = pontosrod;
    }

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

    public Double getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Double patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getUrlEscudoPng() {
        return urlEscudoPng;
    }

    public void setUrlEscudoPng(String urlEscudoPng) {
        this.urlEscudoPng = urlEscudoPng;
    }

    public Integer getCapitaoId() {
        return capitaoId;
    }

    public void setCapitaoId(Integer capitaoId) {
        this.capitaoId = capitaoId;
    }

    public List<Atletas> getAtletas() {
        return atletas;
    }

    public void setAtletas( List<Atletas> atletas ) {
        this.atletas = atletas;
    }

    public Double getUltima() {
        return ultima;
    }

    public void setUltima(Double ultima) {
        this.ultima = ultima;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPontos() {
        return pontos;
    }

    public void setPontos(Double pontos) {
        this.pontos = pontos;
    }



}
