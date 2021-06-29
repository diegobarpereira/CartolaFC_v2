package com.diegopereira.cartolafc.league;

import com.diegopereira.cartolafc.liga.Reservas;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class League {
    @SerializedName("times")
    @Expose
    private List<Times> times = null;



    public List<Times> getTimes() {
        return times;
    }

    public void setTimes(List<Times> times) {
        this.times = times;
    }


}
