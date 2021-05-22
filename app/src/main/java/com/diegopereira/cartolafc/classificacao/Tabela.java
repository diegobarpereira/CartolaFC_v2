package com.diegopereira.cartolafc.classificacao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tabela {
    @SerializedName("posicao")
    @Expose
    private Integer posicao;
    @SerializedName("pontos")
    @Expose
    private Integer pontos;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("jogos")
    @Expose
    private Integer jogos;
    @SerializedName("vitorias")
    @Expose
    private Integer vitorias;
    @SerializedName("empates")
    @Expose
    private Integer empates;
    @SerializedName("derrotas")
    @Expose
    private Integer derrotas;
    @SerializedName("gols_pro")
    @Expose
    private Integer golsPro;
    @SerializedName("gols_contra")
    @Expose
    private Integer golsContra;
    @SerializedName("saldo_gols")
    @Expose
    private Integer saldoGols;
    @SerializedName("aproveitamento")
    @Expose
    private Double aproveitamento;
    @SerializedName("variacao_posicao")
    @Expose
    private Integer variacaoPosicao;
    @SerializedName("ultimos_jogos")
    @Expose
    private List<String> ultimosJogos = null;

    public Integer getPosicao() {
        return posicao;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getJogos() {
        return jogos;
    }

    public void setJogos(Integer jogos) {
        this.jogos = jogos;
    }

    public Integer getVitorias() {
        return vitorias;
    }

    public void setVitorias(Integer vitorias) {
        this.vitorias = vitorias;
    }

    public Integer getEmpates() {
        return empates;
    }

    public void setEmpates(Integer empates) {
        this.empates = empates;
    }

    public Integer getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(Integer derrotas) {
        this.derrotas = derrotas;
    }

    public Integer getGolsPro() {
        return golsPro;
    }

    public void setGolsPro(Integer golsPro) {
        this.golsPro = golsPro;
    }

    public Integer getGolsContra() {
        return golsContra;
    }

    public void setGolsContra(Integer golsContra) {
        this.golsContra = golsContra;
    }

    public Integer getSaldoGols() {
        return saldoGols;
    }

    public void setSaldoGols(Integer saldoGols) {
        this.saldoGols = saldoGols;
    }

    public Double getAproveitamento() {
        return aproveitamento;
    }

    public void setAproveitamento(Double aproveitamento) {
        this.aproveitamento = aproveitamento;
    }

    public Integer getVariacaoPosicao() {
        return variacaoPosicao;
    }

    public void setVariacaoPosicao(Integer variacaoPosicao) {
        this.variacaoPosicao = variacaoPosicao;
    }

    public List<String> getUltimosJogos() {
        return ultimosJogos;
    }

    public void setUltimosJogos( List<String> ultimosJogos) {
        this.ultimosJogos = ultimosJogos;
    }

}
