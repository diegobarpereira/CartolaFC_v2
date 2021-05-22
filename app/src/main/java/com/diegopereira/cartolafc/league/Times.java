package com.diegopereira.cartolafc.league;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Times {
    @SerializedName("url_escudo_png")
    @Expose
    private String url_escudo_png;
    @SerializedName("url_escudo_svg")
    @Expose
    private String url_escudo_svg;
    @SerializedName("assinante")
    @Expose
    private String assinante;
    @SerializedName("lgpd_removido")
    @Expose
    private String lgpd_removido;
    @SerializedName("lgpd_quarentena")
    @Expose
    private String lgpd_quarentena;
    @SerializedName("time_id")
    @Expose
    private Integer time_id;
    @SerializedName("foto_perfil")
    @Expose
    private String foto_perfil;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("nome_cartola")
    @Expose
    private String nome_cartola;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("facebook_id")
    @Expose
    private String facebook_id;
    @SerializedName("patrimonio")
    @Expose
    private Double patrimonio;
    @SerializedName("ranking")
    @Expose
    private Ranking ranking;
    @SerializedName("pontos")
    @Expose
    private Pontos pontos;
    @SerializedName("variacao")
    @Expose
    private Variacao variacao;
    @SerializedName("atletas")
    @Expose
    private List<Atleta> atletas;

    public List<Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(List<Atleta> atletas) {
        this.atletas = atletas;
    }

    public String getUrl_escudo_png() {
        return url_escudo_png;
    }

    public void setUrl_escudo_png(String url_escudo_png) {
        this.url_escudo_png = url_escudo_png;
    }

    public String getUrl_escudo_svg() {
        return url_escudo_svg;
    }

    public void setUrl_escudo_svg(String url_escudo_svg) {
        this.url_escudo_svg = url_escudo_svg;
    }

    public String getAssinante() {
        return assinante;
    }

    public void setAssinante(String assinante) {
        this.assinante = assinante;
    }

    public String getLgpd_removido() {
        return lgpd_removido;
    }

    public void setLgpd_removido(String lgpd_removido) {
        this.lgpd_removido = lgpd_removido;
    }

    public String getLgpd_quarentena() {
        return lgpd_quarentena;
    }

    public void setLgpd_quarentena(String lgpd_quarentena) {
        this.lgpd_quarentena = lgpd_quarentena;
    }

    public Integer getTime_id() {
        return time_id;
    }

    public void setTime_id(Integer time_id) {
        this.time_id = time_id;
    }

    public String getFoto_perfil() {
        return foto_perfil;
    }

    public void setFoto_perfil(String foto_perfil) {
        this.foto_perfil = foto_perfil;
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

    public String getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public Double getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Double patrimonio) {
        this.patrimonio = patrimonio;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    public Pontos getPontos() {
        return pontos;
    }

    public void setPontos(Pontos pontos) {
        this.pontos = pontos;
    }

    public Variacao getVariacao() {
        return variacao;
    }

    public void setVariacao(Variacao variacao) {
        this.variacao = variacao;
    }
}
