package com.poc.tesouro.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "fontePagadora")
public class FontePagadora implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cnpj;

    @OneToMany(mappedBy = "fontePagadora")
    private List<SaldoOuRendimento> saldos;

    public FontePagadora() {
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

    public List<SaldoOuRendimento> getSaldos() {
        return saldos;
    }

    public void setSaldos(List<SaldoOuRendimento> saldos) {
        this.saldos = saldos;
    }
}
