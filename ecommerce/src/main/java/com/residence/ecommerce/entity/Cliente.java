package com.residence.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String NomeCompleto;

    @Column(nullable = false,unique = true)
    private String Cpf;

    @Column(nullable = false,unique = true)
    private String Email;

    private String Telefone;

    // Dados que virão do ViaCEP salvos direto no Cliente
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
}
