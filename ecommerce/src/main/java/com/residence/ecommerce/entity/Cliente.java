package com.residence.ecommerce.entity;

import jakarta.persistence.Entity;

@Entity
public class Cliente {
    private String NomeCompleto;
    private String Cpf;
    private String Telefone;
    private String Email;
    private Endereco endereco;

    public String getNomeCompleto() {
        return NomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        NomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cliente(String nomeCompleto, String cpf, String telefone, String email, Endereco endereco) {
        NomeCompleto = nomeCompleto;
        Cpf = cpf;
        Telefone = telefone;
        Email = email;
        this.endereco = endereco;
    }
}
