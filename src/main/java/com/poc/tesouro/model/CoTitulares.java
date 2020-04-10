package com.poc.tesouro.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "coTitulares")
public class CoTitulares implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeDoCotitular;

    @ManyToOne
    @JoinColumn(name = "cpf", nullable = false)
    private Cliente cpfCliente; //FK - referencia dos dados do cliente na tabela clientes


    public CoTitulares() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDoCotitular() {
        return nomeDoCotitular;
    }

    public void setNomeDoCotitular(String nomeDoCotitular) {
        this.nomeDoCotitular = nomeDoCotitular;
    }

    public Cliente getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(Cliente cpfCliente) {
        this.cpfCliente = cpfCliente;
    }
}
