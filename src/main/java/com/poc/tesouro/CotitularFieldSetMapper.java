package com.poc.tesouro;

import com.poc.tesouro.model.Cliente;
import com.poc.tesouro.model.SaldoOuRendimento;
import com.poc.tesouro.repository.ClienteRepository;
import com.poc.tesouro.repository.SaldoOuRendimentoRepository;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CotitularFieldSetMapper implements FieldSetMapper<SaldoOuRendimento> {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    SaldoOuRendimentoRepository saldoOuRendimentoRepository;

    public SaldoOuRendimento mapFieldSet(FieldSet fieldSet) throws BindException {

        Optional<Cliente> findCoTitular = clienteRepository.findById(fieldSet.readLong("idcotitular"));
        Optional<SaldoOuRendimento> findSaldoOuRendimento = saldoOuRendimentoRepository.findById(fieldSet.readLong("idsaldoourendimento"));
        if (!findCoTitular.isPresent() || !findSaldoOuRendimento.isPresent())
            throw new RuntimeException("O literal é null");

        Cliente cotitular = findCoTitular.get();
        SaldoOuRendimento saldoOuRendimento = findSaldoOuRendimento.get();

        List<Cliente> clienteList = saldoOuRendimento.getCoTitulares();
        if (clienteList == null)
            clienteList = new ArrayList<>();
        clienteList.add(cotitular);
        saldoOuRendimento.setCoTitulares(clienteList);

        return saldoOuRendimento;
    }

}