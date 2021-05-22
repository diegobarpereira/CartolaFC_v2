package com.diegopereira.cartolafc.partidas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Partida {

    @SerializedName("partida_id")
    @Expose
    private Integer partidaId;
    @SerializedName("clube_casa_id")
    @Expose
    private Integer clubeCasaId;
    @SerializedName("clube_casa_posicao")
    @Expose
    private Integer clubeCasaPosicao;
    @SerializedName("clube_visitante_id")
    @Expose
    private Integer clubeVisitanteId;
    @SerializedName("aproveitamento_mandante")
    @Expose
    private List<String> aproveitamentoMandante = null;
    @SerializedName("aproveitamento_visitante")
    @Expose
    private List<String> aproveitamentoVisitante = null;
    @SerializedName("clube_visitante_posicao")
    @Expose
    private Integer clubeVisitantePosicao;
    @SerializedName("partida_data")
    @Expose
    private String partidaData;
    @SerializedName("local")
    @Expose
    private String local;
    @SerializedName("valida")
    @Expose
    private Boolean valida;
    @SerializedName("placar_oficial_mandante")
    @Expose
    private Integer placarOficialMandante;
    @SerializedName("placar_oficial_visitante")
    @Expose
    private Integer placarOficialVisitante;
    @SerializedName("url_confronto")
    @Expose
    private String urlConfronto;
    @SerializedName("url_transmissao")
    @Expose
    private String urlTransmissao;

    public Integer getPartidaId() {
        return partidaId;
    }

    public void setPartidaId(Integer partidaId) {
        this.partidaId = partidaId;
    }

    public Partida withPartidaId(Integer partidaId) {
        this.partidaId = partidaId;
        return this;
    }

    public Integer getClubeCasaId() {
        return clubeCasaId;
    }

    public void setClubeCasaId(Integer clubeCasaId) {
        this.clubeCasaId = clubeCasaId;
    }

    public Partida withClubeCasaId(Integer clubeCasaId) {
        this.clubeCasaId = clubeCasaId;
        return this;
    }

    public Integer getClubeCasaPosicao() {
        return clubeCasaPosicao;
    }

    public void setClubeCasaPosicao(Integer clubeCasaPosicao) {
        this.clubeCasaPosicao = clubeCasaPosicao;
    }

    public Partida withClubeCasaPosicao(Integer clubeCasaPosicao) {
        this.clubeCasaPosicao = clubeCasaPosicao;
        return this;
    }

    public Integer getClubeVisitanteId() {
        return clubeVisitanteId;
    }

    public void setClubeVisitanteId(Integer clubeVisitanteId) {
        this.clubeVisitanteId = clubeVisitanteId;
    }

    public Partida withClubeVisitanteId(Integer clubeVisitanteId) {
        this.clubeVisitanteId = clubeVisitanteId;
        return this;
    }

    public List<String> getAproveitamentoMandante() {
        return aproveitamentoMandante;
    }

    public void setAproveitamentoMandante(List<String> aproveitamentoMandante) {
        this.aproveitamentoMandante = aproveitamentoMandante;
    }

    public Partida withAproveitamentoMandante(List<String> aproveitamentoMandante) {
        this.aproveitamentoMandante = aproveitamentoMandante;
        return this;
    }

    public List<String> getAproveitamentoVisitante() {
        return aproveitamentoVisitante;
    }

    public void setAproveitamentoVisitante(List<String> aproveitamentoVisitante) {
        this.aproveitamentoVisitante = aproveitamentoVisitante;
    }

    public Partida withAproveitamentoVisitante(List<String> aproveitamentoVisitante) {
        this.aproveitamentoVisitante = aproveitamentoVisitante;
        return this;
    }

    public Integer getClubeVisitantePosicao() {
        return clubeVisitantePosicao;
    }

    public void setClubeVisitantePosicao(Integer clubeVisitantePosicao) {
        this.clubeVisitantePosicao = clubeVisitantePosicao;
    }

    public Partida withClubeVisitantePosicao(Integer clubeVisitantePosicao) {
        this.clubeVisitantePosicao = clubeVisitantePosicao;
        return this;
    }

    public String getPartidaData() {
        return partidaData;
    }

    public void setPartidaData(String partidaData) {
        this.partidaData = partidaData;
    }

    public Partida withPartidaData(String partidaData) {
        this.partidaData = partidaData;
        return this;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Partida withLocal(String local) {
        this.local = local;
        return this;
    }

    public Boolean getValida() {
        return valida;
    }

    public void setValida(Boolean valida) {
        this.valida = valida;
    }

    public Partida withValida(Boolean valida) {
        this.valida = valida;
        return this;
    }

    public Integer getPlacarOficialMandante() {
        return placarOficialMandante;
    }

    public void setPlacarOficialMandante(Integer placarOficialMandante) {
        this.placarOficialMandante = placarOficialMandante;
    }
/*
    public Partida withPlacarOficialMandante(Object placarOficialMandante) {
        this.placarOficialMandante = placarOficialMandante;
        return this;
    }
*/
    public Integer getPlacarOficialVisitante() {
        return placarOficialVisitante;
    }

    public void setPlacarOficialVisitante(Integer placarOficialVisitante) {
        this.placarOficialVisitante = placarOficialVisitante;
    }
/*
    public Partida withPlacarOficialVisitante(Object placarOficialVisitante) {
        this.placarOficialVisitante = placarOficialVisitante;
        return this;
    }
*/
    public String getUrlConfronto() {
        return urlConfronto;
    }

    public void setUrlConfronto(String urlConfronto) {
        this.urlConfronto = urlConfronto;
    }

    public Partida withUrlConfronto(String urlConfronto) {
        this.urlConfronto = urlConfronto;
        return this;
    }

    public String getUrlTransmissao() {
        return urlTransmissao;
    }

    public void setUrlTransmissao(String urlTransmissao) {
        this.urlTransmissao = urlTransmissao;
    }

    public Partida withUrlTransmissao(String urlTransmissao) {
        this.urlTransmissao = urlTransmissao;
        return this;
    }

    @Override
    public String toString() {
        return "Partida [partida_Id =" + partidaId + "]";
    }

}