package com.diegopereira.cartolafc.parciais;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Atletas implements Comparable<Atletas> {
	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("apelido")
	@Expose
	private String apelido;
	@SerializedName("pontuacao")
	@Expose
	private Double pontuacao;
	@SerializedName("scout")
	@Expose
	private Map<String, Integer> scout;
	@SerializedName("foto")
	@Expose
	private String foto;
	@SerializedName("posicao_id")
	@Expose
	private Integer posicaoId;
	@SerializedName("clube_id")
	@Expose
	private Integer clubeId;

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido( String apelido) {
		this.apelido = apelido;
	}

	public Double getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao( Double pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Map<String, Integer> getScout() {
		return scout;
	}

	public void setScout( Map<String, Integer> scout ) {
		this.scout = scout;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto( String foto) {
		this.foto = foto;
	}

	public Integer getPosicaoId() {
		return posicaoId;
	}

	public void setPosicaoId( Integer posicaoId) {
		this.posicaoId = posicaoId;
	}

	public Integer getClubeId() {
		return clubeId;
	}

	public void setClubeId( Integer clubeId) {
		this.clubeId = clubeId;
	}


	@Override
	public int compareTo( Atletas atletas ) {
		return (int) (this.pontuacao - atletas.getPontuacao());
	}
}