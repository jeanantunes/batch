package com.poc.tesouro.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "saldoOuRendimento")
public class SaldoOuRendimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private FontePagadora fontePagadora; //FK

    @ManyToOne
    private Cliente titular; //FK

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "cotitulares",
            joinColumns = @JoinColumn(name = "idsaldoourendimento"),
            inverseJoinColumns = @JoinColumn(name = "idcotitular"))
    private List<Cliente> coTitulares; //FK

    private String nmConta; //Para NCC sempre fixo Conta corrente

    private Integer numeroDaConta;

    //(18,2)
    private Long vrSaldoAnterior; // Vr = Valor do fim do penultimo ano base

    //(18,2)
    private Long vrSaldoAtual; // Vr = valor fim do ano base do informe PF

    private Date dtApuracao; //Data de qual o dia refencia do legado foi extraido (a ver. final vai ser dia 31/12)

    private String legadoOrigem; //texto livre pra identificar legado origem

    private String produto; //texto livre pra indentificar produto NCC sempre conta corrente

    private String categoriaRendto; //para poder apresentar no bloco correspondente

    //(18,2)
    @Column(precision = 18, scale = 2)
    private BigDecimal vrRendto; //Valor do rendimento, NCC sempre null

    public SaldoOuRendimento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public String getNmConta() {
        return nmConta;
    }

    public void setNmConta(String nmConta) {
        this.nmConta = nmConta;
    }

    public Integer getNumeroDaConta() {
        return numeroDaConta;
    }

    public void setNumeroDaConta(Integer numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }

    public Long getVrSaldoAnterior() {
        return vrSaldoAnterior;
    }

    public void setVrSaldoAnterior(Long vrSaldoAnterior) {
        this.vrSaldoAnterior = vrSaldoAnterior;
    }

    public Long getVrSaldoAtual() {
        return vrSaldoAtual;
    }

    public void setVrSaldoAtual(Long vrSaldoAtual) {
        this.vrSaldoAtual = vrSaldoAtual;
    }

    public Date getDtApuracao() {
        return dtApuracao;
    }

    public void setDtApuracao(Date dtApuracao) {
        this.dtApuracao = dtApuracao;
    }

    public String getLegadoOrigem() {
        return legadoOrigem;
    }

    public void setLegadoOrigem(String legadoOrigem) {
        this.legadoOrigem = legadoOrigem;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getCategoriaRendto() {
        return categoriaRendto;
    }

    public void setCategoriaRendto(String categoriaRendto) {
        this.categoriaRendto = categoriaRendto;
    }

    public FontePagadora getFontePagadora() {
        return fontePagadora;
    }

    public void setFontePagadora(FontePagadora fontePagadora) {
        this.fontePagadora = fontePagadora;
    }

    public List<Cliente> getCoTitulares() {
        return coTitulares;
    }

    public void setCoTitulares(List<Cliente> coTitulares) {
        this.coTitulares = coTitulares;
    }

    public BigDecimal getVrRendto() {
        return vrRendto;
    }

    public void setVrRendto(BigDecimal vrRendto) {
        this.vrRendto = vrRendto;
    }
}
