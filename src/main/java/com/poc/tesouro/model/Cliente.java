package com.poc.tesouro.model;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    @OneToMany(mappedBy = "titular", targetEntity = SaldoOuRendimento.class, fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<SaldoOuRendimento> saldosRendimentos;

    @ManyToMany(mappedBy = "coTitulares", fetch = FetchType.LAZY)
    private Set<SaldoOuRendimento> saldosRendimentoCotitular;

    public Cliente() {
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

    public List<SaldoOuRendimento> getSaldosRendimentos() {
        return saldosRendimentos;
    }

    public void setSaldosRendimentos(List<SaldoOuRendimento> saldosRendimentos) {
        this.saldosRendimentos = saldosRendimentos;
    }

    public Set<SaldoOuRendimento> getSaldosRendimentoCotitular() {
        return saldosRendimentoCotitular;
    }

    public void setSaldosRendimentoCotitular(Set<SaldoOuRendimento> saldosRendimentoCotitular) {
        this.saldosRendimentoCotitular = saldosRendimentoCotitular;
    }
}
