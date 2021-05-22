package com.diegopereira.cartolafc.classificacao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Classificacao {
    @SerializedName("tabela")
    @Expose
    private List<Tabela> tabela = null;

    public List<Tabela> getTabela() {
        return tabela;
    }

    public void setTabela(List<Tabela> tabela) {
        this.tabela = tabela;
    }
}
