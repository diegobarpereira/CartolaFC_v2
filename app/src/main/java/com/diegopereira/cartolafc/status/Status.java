package com.diegopereira.cartolafc.status;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {
    @SerializedName("rodada_atual")
    @Expose
    private Integer rodadaAtual;
    @SerializedName("status_mercado")
    @Expose
    private Integer statusMercado;
    @SerializedName("esquema_default_id")
    @Expose
    private Integer esquemaDefaultId;
    @SerializedName("cartoleta_inicial")
    @Expose
    private Integer cartoletaInicial;
    @SerializedName("max_ligas_free")
    @Expose
    private Integer maxLigasFree;
    @SerializedName("max_ligas_pro")
    @Expose
    private Integer maxLigasPro;
    @SerializedName("max_ligas_matamata_free")
    @Expose
    private Integer maxLigasMatamataFree;
    @SerializedName("max_ligas_matamata_pro")
    @Expose
    private Integer maxLigasMatamataPro;
    @SerializedName("max_ligas_patrocinadas_free")
    @Expose
    private Integer maxLigasPatrocinadasFree;
    @SerializedName("max_ligas_patrocinadas_pro_num")
    @Expose
    private Integer maxLigasPatrocinadasProNum;
    @SerializedName("game_over")
    @Expose
    private Boolean gameOver;
    @SerializedName("temporada")
    @Expose
    private Integer temporada;
    @SerializedName("reativar")
    @Expose
    private Boolean reativar;
    @SerializedName("exibe_sorteio_pro")
    @Expose
    private Boolean exibeSorteioPro;
    @SerializedName("times_escalados")
    @Expose
    private Integer timesEscalados;
    @SerializedName("fechamento")
    @Expose
    private Fechamento fechamento;
    @SerializedName("mercado_pos_rodada")
    @Expose
    private Boolean mercadoPosRodada;
    @SerializedName("aviso")
    @Expose
    private String aviso;
    @SerializedName("aviso_url")
    @Expose
    private String avisoUrl;
    @SerializedName("limites_competicao")
    @Expose
    private LimitesCompeticao limitesCompeticao;

    public Integer getRodadaAtual() {
        return rodadaAtual;
    }

    public void setRodadaAtual(Integer rodadaAtual) {
        this.rodadaAtual = rodadaAtual;
    }

    public Integer getStatusMercado() {
        return statusMercado;
    }

    public void setStatusMercado(Integer statusMercado) {
        this.statusMercado = statusMercado;
    }

    public Integer getEsquemaDefaultId() {
        return esquemaDefaultId;
    }

    public void setEsquemaDefaultId(Integer esquemaDefaultId) {
        this.esquemaDefaultId = esquemaDefaultId;
    }

    public Integer getCartoletaInicial() {
        return cartoletaInicial;
    }

    public void setCartoletaInicial(Integer cartoletaInicial) {
        this.cartoletaInicial = cartoletaInicial;
    }

    public Integer getMaxLigasFree() {
        return maxLigasFree;
    }

    public void setMaxLigasFree(Integer maxLigasFree) {
        this.maxLigasFree = maxLigasFree;
    }

    public Integer getMaxLigasPro() {
        return maxLigasPro;
    }

    public void setMaxLigasPro(Integer maxLigasPro) {
        this.maxLigasPro = maxLigasPro;
    }

    public Integer getMaxLigasMatamataFree() {
        return maxLigasMatamataFree;
    }

    public void setMaxLigasMatamataFree(Integer maxLigasMatamataFree) {
        this.maxLigasMatamataFree = maxLigasMatamataFree;
    }

    public Integer getMaxLigasMatamataPro() {
        return maxLigasMatamataPro;
    }

    public void setMaxLigasMatamataPro(Integer maxLigasMatamataPro) {
        this.maxLigasMatamataPro = maxLigasMatamataPro;
    }

    public Integer getMaxLigasPatrocinadasFree() {
        return maxLigasPatrocinadasFree;
    }

    public void setMaxLigasPatrocinadasFree(Integer maxLigasPatrocinadasFree) {
        this.maxLigasPatrocinadasFree = maxLigasPatrocinadasFree;
    }

    public Integer getMaxLigasPatrocinadasProNum() {
        return maxLigasPatrocinadasProNum;
    }

    public void setMaxLigasPatrocinadasProNum(Integer maxLigasPatrocinadasProNum) {
        this.maxLigasPatrocinadasProNum = maxLigasPatrocinadasProNum;
    }

    public Boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public Boolean getReativar() {
        return reativar;
    }

    public void setReativar(Boolean reativar) {
        this.reativar = reativar;
    }

    public Boolean getExibeSorteioPro() {
        return exibeSorteioPro;
    }

    public void setExibeSorteioPro(Boolean exibeSorteioPro) {
        this.exibeSorteioPro = exibeSorteioPro;
    }

    public Integer getTimesEscalados() {
        return timesEscalados;
    }

    public void setTimesEscalados(Integer timesEscalados) {
        this.timesEscalados = timesEscalados;
    }

    public Fechamento getFechamento() {
        return fechamento;
    }

    public void setFechamento(Fechamento fechamento) {
        this.fechamento = fechamento;
    }

    public Boolean getMercadoPosRodada() {
        return mercadoPosRodada;
    }

    public void setMercadoPosRodada(Boolean mercadoPosRodada) {
        this.mercadoPosRodada = mercadoPosRodada;
    }

    public String getAviso() {
        return aviso;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }

    public String getAvisoUrl() {
        return avisoUrl;
    }

    public void setAvisoUrl(String avisoUrl) {
        this.avisoUrl = avisoUrl;
    }

    public LimitesCompeticao getLimitesCompeticao() {
        return limitesCompeticao;
    }

    public void setLimitesCompeticao(LimitesCompeticao limitesCompeticao) {
        this.limitesCompeticao = limitesCompeticao;
    }

}

