package com.diegopereira.cartolafc.partidas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clubes {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("abreviacao")
    @Expose
    private String abreviacao;
    @SerializedName("nome_fantasia")
    @Expose
    private String nome_fantasia;
    @SerializedName("escudos")
    @Expose
    private Escudos escudos;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
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

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia( String nome_fantasia ) {
        this.nome_fantasia = nome_fantasia;
    }

    public Escudos getEscudos() {
        return escudos;
    }

    public void setEscudos( Escudos escudos ) {
        this.escudos = escudos;
    }
}
