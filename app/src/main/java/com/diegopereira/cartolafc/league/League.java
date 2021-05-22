package com.diegopereira.cartolafc.league;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class League {
    @SerializedName("times")
    @Expose
    private List<Times> times = null;

    @SerializedName("atletas")
    @Expose
    private List<Atleta> atletas;

    public List<Times> getTimes() {
        return times;
    }

    public void setTimes(List<Times> times) {
        this.times = times;
    }

    public List<Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(List<Atleta> atletas) {
        this.atletas = atletas;
    }
}
