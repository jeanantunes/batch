package com.poc.tesouro.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class SaldoOuRendimentoDTO implements Serializable {

    private Long id;

    private Set<FontePagadoraDTO> fontesPagadoras; //FK

    private ClienteDTO titular = new ClienteDTO(); //FK

    private List<ClienteDTO> coTitulares; //FK

    private String nmConta; //Para NCC sempre fixo Conta corrente

    private Integer numeroDaConta;

    //(18,2)
    private Long vrSaldoAnterior; // Vr = Valor do fim do penultimo ano base

    //(18,2)
    private Long vrSaldoAtual; // Vr = valor fim do ano base do informe PF

    private LocalDateTime dtApuracao; //Data de qual o dia refencia do legado foi extraido (a ver. final vai ser dia 31/12)

    private String legadoOrigem; //texto livre pra identificar legado origem

    private String produto; //texto livre pra indentificar produto NCC sempre conta corrente

    private String categoriaRendto; //para poder apresentar no bloco correspondente

    private BigDecimal vrRendto; //Valor do rendimento, NCC sempre null

    public SaldoOuRendimentoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteDTO getTitular() {
        return titular;
    }

    public void setTitular(ClienteDTO titular) {
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

    public LocalDateTime getDtApuracao() {
        return dtApuracao;
    }

    public void setDtApuracao(LocalDateTime dtApuracao) {
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

    public Set<FontePagadoraDTO> getFontesPagadoras() {
        return fontesPagadoras;
    }

    public void setFontesPagadoras(Set<FontePagadoraDTO> fontesPagadoras) {
        this.fontesPagadoras = fontesPagadoras;
    }

    public List<ClienteDTO> getCoTitulares() {
        return coTitulares;
    }

    public void setCoTitulares(List<ClienteDTO> coTitulares) {
        this.coTitulares = coTitulares;
    }

    public BigDecimal getVrRendto() {
        return vrRendto;
    }

    public void setVrRendto(BigDecimal vrRendto) {
        this.vrRendto = vrRendto;
    }
}