package com.diegopereira.cartolafc.liga;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Teste {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("apelido")
    @Expose
    private String apelido;
    @SerializedName("pontuacao")
    @Expose
    private Double pontuacao;
    @SerializedName("scout")
    @Expose
    private Map<String, Integer> scout;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Map<String, Integer> getScout() {
        return scout;
    }

    public void setScout(Map<String, Integer> scout) {
        this.scout = scout;
    }
}
