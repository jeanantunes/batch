package com.poc.tesouro;

import com.poc.tesouro.model.SaldoOuRendimento;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class SaldoOuRendimentoItemProcessor implements ItemProcessor<SaldoOuRendimento, SaldoOuRendimento> {
    private static final Logger log = LoggerFactory.getLogger(SaldoOuRendimentoItemProcessor.class);

    private Set<SaldoOuRendimento> seenSaldoOuRendimentos = new HashSet<SaldoOuRendimento>();

    public SaldoOuRendimento process(SaldoOuRendimento saldoOuRendimento) {
        if (seenSaldoOuRendimentos.contains(saldoOuRendimento)) {
            log.info("********** SALDO_OU_RENDIMENTO (" + saldoOuRendimento.getVrRendto() +
                    saldoOuRendimento.getNmConta() + " - " + saldoOuRendimento.getNumeroDaConta() + ") JÁ EXISTE!!! NÃO REALIZADO!!! **********");
            log.info(String.valueOf("HasSet<>() >>>>> " + seenSaldoOuRendimentos.size()));
            return null;
        }
        seenSaldoOuRendimentos.add(saldoOuRendimento);
        log.info("INSERINDO (" + saldoOuRendimento.getVrRendto() +
                saldoOuRendimento.getNmConta() + " - " + saldoOuRendimento.getNumeroDaConta() + ") NO BANCO DE DADOS");
        return saldoOuRendimento;

    }

    /*
    @Override
    public User process(final User saldoOuRendimento) throws Exception {
        final String nome = saldoOuRendimento.getNome().toUpperCase();
        final String data = saldoOuRendimento.getData().toUpperCase();
        final Long porcentagem = Long.valueOf(saldoOuRendimento.getPorcentagem().toString());

        final User transformedUser = new User(nome, data, porcentagem);

        log.info("Converting (" + saldoOuRendimento + ") into (" + transformedUser + ")");

        return transformedUser;
    }
    */
}
