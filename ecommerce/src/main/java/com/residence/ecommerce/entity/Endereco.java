package com.residence.ecommerce.entity;

public class Endereco {
    private String Cep;
    private String Complemento;

    public String getCep() {
        return Cep;
    }

    public void setCep(String cep) {
        Cep = cep;
    }

    public String getComplemento() {
        return Complemento;
    }

    public void setComplemento(String complemento) {
        Complemento = complemento;
    }

    public Endereco(String cep, String complemento) {
        Cep = cep;
        Complemento = complemento;
    }
}
