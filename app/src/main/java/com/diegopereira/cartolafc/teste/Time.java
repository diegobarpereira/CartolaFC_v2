package com.diegopereira.cartolafc.teste;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Time {

    @SerializedName("assinante")
    @Expose
    private Boolean assinante;
    @SerializedName("cadastro_completo")
    @Expose
    private Boolean cadastroCompleto;
    @SerializedName("simplificado")
    @Expose
    private Boolean simplificado;
    @SerializedName("clube_id")
    @Expose
    private Integer clubeId;
    @SerializedName("esquema_id")
    @Expose
    private Integer esquemaId;
    @SerializedName("tipo_adorno")
    @Expose
    private Integer tipoAdorno;
    @SerializedName("tipo_camisa")
    @Expose
    private Integer tipoCamisa;
    @SerializedName("tipo_escudo")
    @Expose
    private Integer tipoEscudo;
    @SerializedName("tipo_estampa_camisa")
    @Expose
    private Integer tipoEstampaCamisa;
    @SerializedName("tipo_estampa_escudo")
    @Expose
    private Integer tipoEstampaEscudo;
    @SerializedName("patrocinador1_id")
    @Expose
    private Integer patrocinador1Id;
    @SerializedName("patrocinador2_id")
    @Expose
    private Integer patrocinador2Id;
    @SerializedName("time_id")
    @Expose
    private Integer timeId;
    @SerializedName("cor_borda_escudo")
    @Expose
    private String corBordaEscudo;
    @SerializedName("cor_camisa")
    @Expose
    private String corCamisa;
    @SerializedName("cor_fundo_escudo")
    @Expose
    private String corFundoEscudo;
    @SerializedName("foto_perfil")
    @Expose
    private String fotoPerfil;
    @SerializedName("globo_id")
    @Expose
    private String globoId;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("nome_cartola")
    @Expose
    private String nomeCartola;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("url_camisa_png")
    @Expose
    private String urlCamisaPng;
    @SerializedName("url_camisa_svg")
    @Expose
    private String urlCamisaSvg;
    @SerializedName("url_escudo_png")
    @Expose
    private String urlEscudoPng;
    @SerializedName("url_escudo_svg")
    @Expose
    private String urlEscudoSvg;
    @SerializedName("facebook_id")
    @Expose
    private Long facebookId;
    @SerializedName("rodada_time_id")
    @Expose
    private Integer rodadaTimeId;
    @SerializedName("cor_primaria_estampa_camisa")
    @Expose
    private String corPrimariaEstampaCamisa;
    @SerializedName("cor_primaria_estampa_escudo")
    @Expose
    private String corPrimariaEstampaEscudo;
    @SerializedName("cor_secundaria_estampa_camisa")
    @Expose
    private String corSecundariaEstampaCamisa;
    @SerializedName("cor_secundaria_estampa_escudo")
    @Expose
    private String corSecundariaEstampaEscudo;
    @SerializedName("sorteio_pro_num")
    @Expose
    private Object sorteioProNum;
    @SerializedName("temporada_inicial")
    @Expose
    private Integer temporadaInicial;

    public Boolean getAssinante() {
        return assinante;
    }

    public void setAssinante(Boolean assinante) {
        this.assinante = assinante;
    }

    public Boolean getCadastroCompleto() {
        return cadastroCompleto;
    }

    public void setCadastroCompleto(Boolean cadastroCompleto) {
        this.cadastroCompleto = cadastroCompleto;
    }

    public Boolean getSimplificado() {
        return simplificado;
    }

    public void setSimplificado(Boolean simplificado) {
        this.simplificado = simplificado;
    }

    public Integer getClubeId() {
        return clubeId;
    }

    public void setClubeId(Integer clubeId) {
        this.clubeId = clubeId;
    }

    public Integer getEsquemaId() {
        return esquemaId;
    }

    public void setEsquemaId(Integer esquemaId) {
        this.esquemaId = esquemaId;
    }

    public Integer getTipoAdorno() {
        return tipoAdorno;
    }

    public void setTipoAdorno(Integer tipoAdorno) {
        this.tipoAdorno = tipoAdorno;
    }

    public Integer getTipoCamisa() {
        return tipoCamisa;
    }

    public void setTipoCamisa(Integer tipoCamisa) {
        this.tipoCamisa = tipoCamisa;
    }

    public Integer getTipoEscudo() {
        return tipoEscudo;
    }

    public void setTipoEscudo(Integer tipoEscudo) {
        this.tipoEscudo = tipoEscudo;
    }

    public Integer getTipoEstampaCamisa() {
        return tipoEstampaCamisa;
    }

    public void setTipoEstampaCamisa(Integer tipoEstampaCamisa) {
        this.tipoEstampaCamisa = tipoEstampaCamisa;
    }

    public Integer getTipoEstampaEscudo() {
        return tipoEstampaEscudo;
    }

    public void setTipoEstampaEscudo(Integer tipoEstampaEscudo) {
        this.tipoEstampaEscudo = tipoEstampaEscudo;
    }

    public Integer getPatrocinador1Id() {
        return patrocinador1Id;
    }

    public void setPatrocinador1Id(Integer patrocinador1Id) {
        this.patrocinador1Id = patrocinador1Id;
    }

    public Integer getPatrocinador2Id() {
        return patrocinador2Id;
    }

    public void setPatrocinador2Id(Integer patrocinador2Id) {
        this.patrocinador2Id = patrocinador2Id;
    }

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

    public String getCorBordaEscudo() {
        return corBordaEscudo;
    }

    public void setCorBordaEscudo(String corBordaEscudo) {
        this.corBordaEscudo = corBordaEscudo;
    }

    public String getCorCamisa() {
        return corCamisa;
    }

    public void setCorCamisa(String corCamisa) {
        this.corCamisa = corCamisa;
    }

    public String getCorFundoEscudo() {
        return corFundoEscudo;
    }

    public void setCorFundoEscudo(String corFundoEscudo) {
        this.corFundoEscudo = corFundoEscudo;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getGloboId() {
        return globoId;
    }

    public void setGloboId(String globoId) {
        this.globoId = globoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeCartola() {
        return nomeCartola;
    }

    public void setNomeCartola(String nomeCartola) {
        this.nomeCartola = nomeCartola;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUrlCamisaPng() {
        return urlCamisaPng;
    }

    public void setUrlCamisaPng(String urlCamisaPng) {
        this.urlCamisaPng = urlCamisaPng;
    }

    public String getUrlCamisaSvg() {
        return urlCamisaSvg;
    }

    public void setUrlCamisaSvg(String urlCamisaSvg) {
        this.urlCamisaSvg = urlCamisaSvg;
    }

    public String getUrlEscudoPng() {
        return urlEscudoPng;
    }

    public void setUrlEscudoPng(String urlEscudoPng) {
        this.urlEscudoPng = urlEscudoPng;
    }

    public String getUrlEscudoSvg() {
        return urlEscudoSvg;
    }

    public void setUrlEscudoSvg(String urlEscudoSvg) {
        this.urlEscudoSvg = urlEscudoSvg;
    }

    public Long getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(Long facebookId) {
        this.facebookId = facebookId;
    }

    public Integer getRodadaTimeId() {
        return rodadaTimeId;
    }

    public void setRodadaTimeId(Integer rodadaTimeId) {
        this.rodadaTimeId = rodadaTimeId;
    }

    public String getCorPrimariaEstampaCamisa() {
        return corPrimariaEstampaCamisa;
    }

    public void setCorPrimariaEstampaCamisa(String corPrimariaEstampaCamisa) {
        this.corPrimariaEstampaCamisa = corPrimariaEstampaCamisa;
    }

    public String getCorPrimariaEstampaEscudo() {
        return corPrimariaEstampaEscudo;
    }

    public void setCorPrimariaEstampaEscudo(String corPrimariaEstampaEscudo) {
        this.corPrimariaEstampaEscudo = corPrimariaEstampaEscudo;
    }

    public String getCorSecundariaEstampaCamisa() {
        return corSecundariaEstampaCamisa;
    }

    public void setCorSecundariaEstampaCamisa(String corSecundariaEstampaCamisa) {
        this.corSecundariaEstampaCamisa = corSecundariaEstampaCamisa;
    }

    public String getCorSecundariaEstampaEscudo() {
        return corSecundariaEstampaEscudo;
    }

    public void setCorSecundariaEstampaEscudo(String corSecundariaEstampaEscudo) {
        this.corSecundariaEstampaEscudo = corSecundariaEstampaEscudo;
    }

    public Object getSorteioProNum() {
        return sorteioProNum;
    }

    public void setSorteioProNum(Object sorteioProNum) {
        this.sorteioProNum = sorteioProNum;
    }

    public Integer getTemporadaInicial() {
        return temporadaInicial;
    }

    public void setTemporadaInicial(Integer temporadaInicial) {
        this.temporadaInicial = temporadaInicial;
    }

}