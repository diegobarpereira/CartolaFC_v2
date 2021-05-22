package com.diegopereira.cartolafc.jogadores;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Posicoes {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("abreviacao")
    @Expose
    private String abreviacao;

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao( String abreviacao ) {
        this.abreviacao = abreviacao;
    }
}
