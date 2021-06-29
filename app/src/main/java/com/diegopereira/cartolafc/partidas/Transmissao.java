package com.diegopereira.cartolafc.partidas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transmissao {
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("url")
    @Expose
    private String url;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
