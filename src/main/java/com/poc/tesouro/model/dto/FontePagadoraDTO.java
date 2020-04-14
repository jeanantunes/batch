package com.poc.tesouro.model.dto;

import java.io.Serializable;
import java.util.Set;

public class FontePagadoraDTO implements Serializable {

    private Long id;

    private String nome;
    private String cnpj;

    private Set<SaldoOuRendimentoDTO> saldos;

    public FontePagadoraDTO() {
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Set<SaldoOuRendimentoDTO> getSaldos() {
        return saldos;
    }

    public void setSaldos(Set<SaldoOuRendimentoDTO> saldos) {
        this.saldos = saldos;
    }
}
