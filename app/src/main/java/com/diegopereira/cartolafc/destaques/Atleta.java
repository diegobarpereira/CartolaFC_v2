package com.diegopereira.cartolafc.destaques;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Atleta {
    @SerializedName("atleta_id")
    @Expose
    private Integer atletaId;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("apelido")
    @Expose
    private String apelido;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("preco_editorial")
    @Expose
    private Double precoEditorial;

    public Integer getAtletaId() {
        return atletaId;
    }

    public void setAtletaId(Integer atletaId) {
        this.atletaId = atletaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Double getPrecoEditorial() {
        return precoEditorial;
    }

    public void setPrecoEditorial(Double precoEditorial) {
        this.precoEditorial = precoEditorial;
    }

}
