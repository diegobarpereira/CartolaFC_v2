package com.diegopereira.cartolafc.liga;

    import java.util.List;
    import com.google.gson.annotations.Expose;
    import com.google.gson.annotations.SerializedName;

    public class Players {

        @SerializedName("atletas")
        @Expose
        private List<Atleta> atletas = null;
        /*@SerializedName("clubes")
        @Expose
        private Clubes clubes;*/
        @SerializedName("posicoes")
        @Expose
        private Posicoes posicoes;
        /*@SerializedName("status")
        @Expose
        private Status status;*/
        @SerializedName("esquema_id")
        @Expose
        private Integer esquemaId;
        @SerializedName("rodada_atual")
        @Expose
        private Integer rodadaAtual;

        @SerializedName("mensagem")
        @Expose
        private String mensagem;
        @SerializedName("patrimonio")
        @Expose
        private Double patrimonio;
        @SerializedName("valor_time")
        @Expose
        private Integer valorTime;
        @SerializedName("capitao_id")
        @Expose
        private Integer capitaoId;
        @SerializedName("pontos")
        @Expose
        private Double pontos;
        @SerializedName("pontos_campeonato")
        @Expose
        private Double pontosCampeonato;
        @SerializedName("time")
        @Expose
        private Time time;

        public List<Atleta> getAtletas() {
            return atletas;
        }

        public void setAtletas(List<Atleta> atletas) {
            this.atletas = atletas;
        }

        /*public Clubes getClubes() {
            return clubes;
        }

        public void setClubes(Clubes clubes) {
            this.clubes = clubes;
        }
*/
        public Posicoes getPosicoes() {
            return posicoes;
        }

        public void setPosicoes(Posicoes posicoes) {
            this.posicoes = posicoes;
        }
/*
        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }
*/
        public Integer getEsquemaId() {
            return esquemaId;
        }

        public void setEsquemaId(Integer esquemaId) {
            this.esquemaId = esquemaId;
        }

        public Integer getRodadaAtual() {
            return rodadaAtual;
        }

        public void setRodadaAtual(Integer rodadaAtual) {
            this.rodadaAtual = rodadaAtual;
        }

        public String getMensagem() {
            return mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }

        public Double getPatrimonio() {
            return patrimonio;
        }

        public void setPatrimonio(Double patrimonio) {
            this.patrimonio = patrimonio;
        }

        public Integer getValorTime() {
            return valorTime;
        }

        public void setValorTime(Integer valorTime) {
            this.valorTime = valorTime;
        }

        public Integer getCapitaoId() {
            return capitaoId;
        }

        public void setCapitaoId(Integer capitaoId) {
            this.capitaoId = capitaoId;
        }

        public Double getPontos() {
            return pontos;
        }

        public void setPontos(Double pontos) {
            this.pontos = pontos;
        }

        public Double getPontosCampeonato() {
            return pontosCampeonato;
        }

        public void setPontosCampeonato(Double pontosCampeonato) {
            this.pontosCampeonato = pontosCampeonato;
        }

        public Time getTime() {
            return time;
        }

        public void setTime(Time time) {
            this.time = time;
        }

    }

