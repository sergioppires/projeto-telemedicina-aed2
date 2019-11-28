package com.ufabc.telemedicina.domains;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageStatus {

    @JsonProperty("tamanhoAntigo")
    String tamAntigo;

    @JsonProperty("tamanhoNovo")
    String tamNovo;

    @JsonProperty("tamanhoCompressao")
    String reducao;

    public String getTamAntigo() {
        return tamAntigo;
    }

    public void setTamAntigo(String tamAntigo) {
        this.tamAntigo = tamAntigo;
    }

    public String getTamNovo() {
        return tamNovo;
    }

    public void setTamNovo(String tamNovo) {
        this.tamNovo = tamNovo;
    }

    public String getReducao() {
        return reducao;
    }

    public void setReducao(String reducao) {
        this.reducao = reducao;
    }
}
