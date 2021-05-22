package com.diegopereira.cartolafc.parciais;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Scout{

	@SerializedName("FS")
	@Expose
	private String fS;

	@SerializedName("PI")
	@Expose
	private String pI;

	@SerializedName("FC")
	@Expose
	private String fC;

	@SerializedName("DS")
	@Expose
	private String dS;

	@SerializedName("CA")
	@Expose
	private String cA;

	@SerializedName("DE")
	@Expose
	private String dE;

	@SerializedName("GS")
	@Expose
	private String gS;

	@SerializedName("FF")
	@Expose
	private String fF;

	@SerializedName("I")
	@Expose
	private String I;

	@SerializedName("FD")
	@Expose
	private String fD;

	@SerializedName("A")
	@Expose
	private String A;

	@SerializedName("FT")
	@Expose
	private String fT;

	@SerializedName("SG")
	@Expose
	private String sG;

	@SerializedName("CV")
	@Expose
	private String cV;

	@SerializedName("G")
	@Expose
	private String G;

	@SerializedName("PP")
	@Expose
	private String pP;

	@SerializedName("DP")
	@Expose
	private String dP;

	@SerializedName("PS")
	@Expose
	private String pS;

	@SerializedName("PC")
	@Expose
	private String pC;

	public Scout() {
		fS = null;
	}

	public void setFS( String fS){
		this.fS = fS;
	}

	public String getFS(){
		return fS;
	}

	public void setPI( String pI){
		this.pI = pI;
	}

	public String getPI(){
		return pI;
	}

	public void setFC( String fC){
		this.fC = fC;
	}

	public String getFC(){
		return fC;
	}

	public void setDS( String dS){
		this.dS = dS;
	}

	public String getDS(){
		return dS;
	}

	public void setCA( String cA){
		this.cA = cA;
	}

	public String getCA(){
		return cA;
	}

	public void setGS( String gS){
		this.gS = gS;
	}

	public String getGS(){
		return gS;
	}

	public void setFF( String fF){
		this.fF = fF;
	}

	public String getFF(){
		return fF;
	}

	public void setI( String I){
		this.I = I;
	}

	public String getI(){
		return I;
	}

	public void setFD( String fD){
		this.fD = fD;
	}

	public String getFD(){
		return fD;
	}

	public void setA( String A){
		this.A = A;
	}

	public String getA(){
		return A;
	}

	public void setFT( String fT){
		this.fT = fT;
	}

	public String getFT(){
		return fT;
	}

	public void setSG( String sG){
		this.sG = sG;
	}

	public String getSG(){
		return sG;
	}

	public void setCV( String cV){
		this.cV = cV;
	}

	public String getCV(){
		return cV;
	}

	public void setG( String G){
		this.G = G;
	}

	public String getG(){
		return G;
	}

	public void setPP( String pP){
		this.pP = pP;
	}

	public String getPP(){
		return pP;
	}

	public void setDP( String dP){
		this.dP = dP;
	}

	public String getDP(){
		return dP;
	}

	public String getdE() {
		return dE;
	}

	public void setdE( String dE ) {
		this.dE = dE;
	}

	public String getpS() {
		return pS;
	}

	public void setpS( String pS ) {
		this.pS = pS;
	}

	public String getpC() {
		return pC;
	}

	public void setpC( String pC ) {
		this.pC = pC;
	}
}