package com.poc.tesouro.model.dto;


import java.io.Serializable;
import java.util.Set;

public class ClienteDTO implements Serializable {

    private Long id;

    private String nome;

    private String cpf;

    private Set<SaldoOuRendimentoDTO> saldosRendimentos;

    private Set<SaldoOuRendimentoDTO> saldosRendimentoCotitular;

    public ClienteDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<SaldoOuRendimentoDTO> getSaldosRendimentos() {
        return saldosRendimentos;
    }

    public void setSaldosRendimentos(Set<SaldoOuRendimentoDTO> saldosRendimentos) {
        this.saldosRendimentos = saldosRendimentos;
    }

    public Set<SaldoOuRendimentoDTO> getSaldosRendimentoCotitular() {
        return saldosRendimentoCotitular;
    }

    public void setSaldosRendimentoCotitular(Set<SaldoOuRendimentoDTO> saldosRendimentoCotitular) {
        this.saldosRendimentoCotitular = saldosRendimentoCotitular;
    }
}
