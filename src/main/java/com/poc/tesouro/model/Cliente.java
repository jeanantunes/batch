package com.poc.tesouro.model;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
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
    private Set<SaldoOuRendimento> cliente;

    @ManyToMany(mappedBy = "coTitulares")
    private Set<SaldoOuRendimento> listaCoTitulares;

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

    public Set<SaldoOuRendimento> getCliente() {
        return cliente;
    }

    public void setCliente(Set<SaldoOuRendimento> cliente) {
        this.cliente = cliente;
    }

    public Set<SaldoOuRendimento> getListaCoTitulares() {
        return listaCoTitulares;
    }

    public void setListaCoTitulares(Set<SaldoOuRendimento> listaCoTitulares) {
        this.listaCoTitulares = listaCoTitulares;
    }
}
