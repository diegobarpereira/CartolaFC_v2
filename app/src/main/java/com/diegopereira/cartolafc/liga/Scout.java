package com.diegopereira.cartolafc.liga;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Scout {

    @SerializedName("FC")
    @Expose
    private Integer fC;
    @SerializedName("FF")
    @Expose
    private Integer fF;
    @SerializedName("FS")
    @Expose
    private Integer fS;
    @SerializedName("PI")
    @Expose
    private Integer pI;
    @SerializedName("A")
    @Expose
    private Integer a;
    @SerializedName("DS")
    @Expose
    private Integer dS;
    @SerializedName("SG")
    @Expose
    private Integer sG;
    @SerializedName("FD")
    @Expose
    private Integer fD;
    @SerializedName("G")
    @Expose
    private Integer g;
    @SerializedName("GS")
    @Expose
    private Integer gS;
    @SerializedName("CA")
    @Expose
    private Integer cA;
    @SerializedName("I")
    @Expose
    private Integer i;

    public Integer getFC() {
        return fC;
    }

    public void setFC(Integer fC) {
        this.fC = fC;
    }

    public Integer getFF() {
        return fF;
    }

    public void setFF(Integer fF) {
        this.fF = fF;
    }

    public Integer getFS() {
        return fS;
    }

    public void setFS(Integer fS) {
        this.fS = fS;
    }

    public Integer getPI() {
        return pI;
    }

    public void setPI(Integer pI) {
        this.pI = pI;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getDS() {
        return dS;
    }

    public void setDS(Integer dS) {
        this.dS = dS;
    }

    public Integer getSG() {
        return sG;
    }

    public void setSG(Integer sG) {
        this.sG = sG;
    }

    public Integer getFD() {
        return fD;
    }

    public void setFD(Integer fD) {
        this.fD = fD;
    }

    public Integer getG() {
        return g;
    }

    public void setG(Integer g) {
        this.g = g;
    }

    public Integer getGS() {
        return gS;
    }

    public void setGS(Integer gS) {
        this.gS = gS;
    }

    public Integer getCA() {
        return cA;
    }

    public void setCA(Integer cA) {
        this.cA = cA;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

}