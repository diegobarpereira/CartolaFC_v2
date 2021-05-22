package com.diegopereira.cartolafc.liga;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyAtleta {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("apelido")
    @Expose
    private String apelido;
    @SerializedName("pontuacao")
    @Expose
    private Double pontuacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Double pontuacao) {
        this.pontuacao = pontuacao;
    }
}
