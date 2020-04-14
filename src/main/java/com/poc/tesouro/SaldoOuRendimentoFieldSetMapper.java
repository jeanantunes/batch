package com.poc.tesouro;

import com.poc.tesouro.model.SaldoOuRendimento;
import com.poc.tesouro.repository.SaldoOuRendimentoRepository;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;

public class SaldoOuRendimentoFieldSetMapper implements FieldSetMapper<SaldoOuRendimento> {

    @Autowired
    SaldoOuRendimentoRepository rendimentoRepository;

    public SaldoOuRendimento mapFieldSet(FieldSet fieldSet) throws BindException {

        SaldoOuRendimento obj = new SaldoOuRendimento();

        obj.setId(fieldSet.readLong("id"));
        obj.setCategoriaRendto(fieldSet.readRawString("categoriaRendto"));
        obj.setDtApuracao(fieldSet.readDate("dtApuracao", "yyyy-MM-dd"));
        obj.setLegadoOrigem(fieldSet.readRawString("legadoOrigem"));
        obj.setNmConta(fieldSet.readRawString("nmConta"));
        obj.setNumeroDaConta(fieldSet.readInt("numeroDaConta"));
        obj.setProduto(fieldSet.readRawString("produto"));
        obj.setVrRendto(fieldSet.readBigDecimal("vrRendto"));
        obj.setVrSaldoAnterior(fieldSet.readLong("vrSaldoAnterior"));
        obj.setVrSaldoAtual(fieldSet.readLong("vrSaldoAtual"));
        //obj.getTitular().setId(fieldSet.readLong("titular"));

        return obj;
    }

}