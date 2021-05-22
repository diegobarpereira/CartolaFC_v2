package com.diegopereira.cartolafc.ligaauth;

import com.diegopereira.cartolafc.partidas.Partida;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AuthLiga {

    @SerializedName("ligas")
    @Expose
    private List<Ligas> ligas = null;

    public List<Ligas> getLigas() {
        return ligas;
    }

    public void setLigas( List<Ligas> ligas ) {
        this.ligas = ligas;
    }
}
