package com.diegopereira.cartolafc.groups;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Input {


        @SerializedName("assinante")
        @Expose
        private Boolean assinante;
        @SerializedName("lgpd_removido")
        @Expose
        private Boolean lgpdRemovido;
        @SerializedName("lgpd_quarentena")
        @Expose
        private Boolean lgpdQuarentena;
        @SerializedName("time_id")
        @Expose
        private Integer timeId;
        @SerializedName("foto_perfil")
        @Expose
        private String fotoPerfil;
        @SerializedName("nome")
        @Expose
        private String nome;
        @SerializedName("nome_cartola")
        @Expose
        private String nomeCartola;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("url_escudo_png")
        @Expose
        private String urlEscudoPng;
        @SerializedName("url_escudo_svg")
        @Expose
        private String urlEscudoSvg;
        @SerializedName("facebook_id")
        @Expose
        private Object facebookId;

        public Boolean getAssinante() {
            return assinante;
        }

        public void setAssinante(Boolean assinante) {
            this.assinante = assinante;
        }

        public Boolean getLgpdRemovido() {
            return lgpdRemovido;
        }

        public void setLgpdRemovido(Boolean lgpdRemovido) {
            this.lgpdRemovido = lgpdRemovido;
        }

        public Boolean getLgpdQuarentena() {
            return lgpdQuarentena;
        }

        public void setLgpdQuarentena(Boolean lgpdQuarentena) {
            this.lgpdQuarentena = lgpdQuarentena;
        }

        public Integer getTimeId() {
            return timeId;
        }

        public void setTimeId(Integer timeId) {
            this.timeId = timeId;
        }

        public String getFotoPerfil() {
            return fotoPerfil;
        }

        public void setFotoPerfil(String fotoPerfil) {
            this.fotoPerfil = fotoPerfil;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getNomeCartola() {
            return nomeCartola;
        }

        public void setNomeCartola(String nomeCartola) {
            this.nomeCartola = nomeCartola;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getUrlEscudoPng() {
            return urlEscudoPng;
        }

        public void setUrlEscudoPng(String urlEscudoPng) {
            this.urlEscudoPng = urlEscudoPng;
        }

        public String getUrlEscudoSvg() {
            return urlEscudoSvg;
        }

        public void setUrlEscudoSvg(String urlEscudoSvg) {
            this.urlEscudoSvg = urlEscudoSvg;
        }

        public Object getFacebookId() {
            return facebookId;
        }

        public void setFacebookId(Object facebookId) {
            this.facebookId = facebookId;
        }

    }

