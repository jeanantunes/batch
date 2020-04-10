package com.poc.tesouro.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // If our entities use GenerationType.IDENTITY identifier generator, Hibernate will silently disable batch inserts/updates.
    //Since entities in our examples use GenerationType.SEQUENCE identifier generator, Hibernate enables batch operations


    private String nome;
    private String data;
    private Long porcentagem;

    public User() {
    }

    public User(String nome, String data, Long porcentagem) {
        this.nome = nome;
        this.data = data;
        this.porcentagem = porcentagem;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(Long porcentagem) {
        this.porcentagem = porcentagem;
    }
}
