package com.diegopereira.cartolafc.teste;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestePontos extends TimePontos {



    @SerializedName("pontosrod")
    @Expose
    private Double pontosrod;



    public Double getPontosrod() {
        return pontosrod;
    }

    public void setPontosrod( Double pontosrod ) {
        this.pontosrod = pontosrod;
    }



}
