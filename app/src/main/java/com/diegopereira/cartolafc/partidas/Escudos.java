package com.diegopereira.cartolafc.partidas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Escudos {
    @SerializedName("60x60")
    @Expose
    private String _60x60;
    @SerializedName("45x45")
    @Expose
    private String _45x45;
    @SerializedName("30x30")
    @Expose
    private String _30x30;

    public String get_60x60() {
        return _60x60;
    }

    public void set_60x60( String _60x60 ) {
        this._60x60 = _60x60;
    }

    public String get_45x45() {
        return _45x45;
    }

    public void set_45x45( String _45x45 ) {
        this._45x45 = _45x45;
    }

    public String get_30x30() {
        return _30x30;
    }

    public void set_30x30( String _30x30 ) {
        this._30x30 = _30x30;
    }
}
