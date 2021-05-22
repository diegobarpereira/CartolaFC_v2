package com.diegopereira.cartolafc.jogadores;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Atleta {
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("apelido")
    @Expose
    private String apelido;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("atleta_id")
    @Expose
    private String atletaId;
    @SerializedName("rodada_id")
    @Expose
    private String rodadaId;
    @SerializedName("clube_id")
    @Expose
    private String clubeId;
    @SerializedName("posicao_id")
    @Expose
    private String posicaoId;
    @SerializedName("status_id")
    @Expose
    private String statusId;
    @SerializedName("pontos_num")
    @Expose
    private Double pontosNum;
    @SerializedName("preco_num")
    @Expose
    private Double precoNum;
    @SerializedName("variacao_num")
    @Expose
    private String variacaoNum;
    @SerializedName("media_num")
    @Expose
    private Double mediaNum;
    @SerializedName("jogos_num")
    @Expose
    private String jogosNum;
    @SerializedName("scout")
    @Expose
    private Scout scout;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getAtletaId() {
        return atletaId;
    }

    public void setAtletaId(String atletaId) {
        this.atletaId = atletaId;
    }

    public String getRodadaId() {
        return rodadaId;
    }

    public void setRodadaId(String rodadaId) {
        this.rodadaId = rodadaId;
    }

    public String getClubeId() {
        return clubeId;
    }

    public void setClubeId(String clubeId) {
        this.clubeId = clubeId;
    }

    public String getPosicaoId() {
        return posicaoId;
    }

    public void setPosicaoId(String posicaoId) {
        this.posicaoId = posicaoId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public Double getPontosNum() {
        return pontosNum;
    }

    public void setPontosNum(Double pontosNum) {
        this.pontosNum = pontosNum;
    }

    public Double getPrecoNum() {
        return precoNum;
    }

    public void setPrecoNum(Double precoNum) {
        this.precoNum = precoNum;
    }

    public String getVariacaoNum() {
        return variacaoNum;
    }

    public void setVariacaoNum(String variacaoNum) {
        this.variacaoNum = variacaoNum;
    }

    public Double getMediaNum() {
        return mediaNum;
    }

    public void setMediaNum(Double mediaNum) {
        this.mediaNum = mediaNum;
    }

    public String getJogosNum() {
        return jogosNum;
    }

    public void setJogosNum(String jogosNum) {
        this.jogosNum = jogosNum;
    }

    public Scout getScout() {
        return scout;
    }

    public void setScout(Scout scout) {
        this.scout = scout;
    }

}