package com.poc.tesouro;

import com.poc.tesouro.model.Cliente;
import com.poc.tesouro.model.FontePagadora;
import com.poc.tesouro.model.SaldoOuRendimento;
import com.poc.tesouro.repository.ClienteRepository;
import com.poc.tesouro.repository.FontePagadoraRepository;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.util.Optional;

@Component
public class SaldoOuRendimentoFieldSetMapper implements FieldSetMapper<SaldoOuRendimento> {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    FontePagadoraRepository fontePagadoraRepository;

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

        Optional<Cliente> titular = clienteRepository.findById(fieldSet.readLong("titular"));
        Optional<FontePagadora> fontePagadora = fontePagadoraRepository.findById(fieldSet.readLong(("fontePagadora")));
        if (!titular.isPresent() || !fontePagadora.isPresent())
            throw new RuntimeException("O literal é null");
        obj.setTitular(titular.get());
        obj.setFontePagadora(fontePagadora.get());

        return obj;
    }

}