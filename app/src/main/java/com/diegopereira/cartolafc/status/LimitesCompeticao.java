package com.diegopereira.cartolafc.status;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LimitesCompeticao {
    @SerializedName("total_confronto_pro")
    @Expose
    private Integer totalConfrontoPro;
    @SerializedName("total_confronto_free")
    @Expose
    private Integer totalConfrontoFree;
    @SerializedName("criacao_confronto_pro")
    @Expose
    private Integer criacaoConfrontoPro;
    @SerializedName("criacao_confronto_free")
    @Expose
    private Integer criacaoConfrontoFree;

    public Integer getTotalConfrontoPro() {
        return totalConfrontoPro;
    }

    public void setTotalConfrontoPro(Integer totalConfrontoPro) {
        this.totalConfrontoPro = totalConfrontoPro;
    }

    public Integer getTotalConfrontoFree() {
        return totalConfrontoFree;
    }

    public void setTotalConfrontoFree(Integer totalConfrontoFree) {
        this.totalConfrontoFree = totalConfrontoFree;
    }

    public Integer getCriacaoConfrontoPro() {
        return criacaoConfrontoPro;
    }

    public void setCriacaoConfrontoPro(Integer criacaoConfrontoPro) {
        this.criacaoConfrontoPro = criacaoConfrontoPro;
    }

    public Integer getCriacaoConfrontoFree() {
        return criacaoConfrontoFree;
    }

    public void setCriacaoConfrontoFree(Integer criacaoConfrontoFree) {
        this.criacaoConfrontoFree = criacaoConfrontoFree;
    }
}
