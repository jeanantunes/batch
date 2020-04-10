package com.poc.tesouro;

import com.poc.tesouro.model.CoTitulares;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class CoTitularesItemProcessor implements ItemProcessor<CoTitulares, CoTitulares> {
    private static final Logger log = LoggerFactory.getLogger(CoTitularesItemProcessor.class);

    private Set<CoTitulares> seenCoTitularess = new HashSet<CoTitulares>();

    public CoTitulares process(CoTitulares coTitulares) {
        if (seenCoTitularess.contains(coTitulares)) {
            log.info("********** CO_TITLARES (" + coTitulares.getNomeDoCotitular() + ") JÁ EXISTE!!! NÃO REALIZADO!!! **********");
            log.info(String.valueOf("HasSet<>() >>>>> " + seenCoTitularess.size()));
            return null;
        }
        seenCoTitularess.add(coTitulares);
        log.info("INSERINDO (" + coTitulares.getNomeDoCotitular() + " - "
                + coTitulares.getCpfCliente() + ") NO BANCO DE DADOS");
        return coTitulares;

    }

    /*
    @Override
    public User process(final User coTitulares) throws Exception {
        final String nome = coTitulares.getNome().toUpperCase();
        final String data = coTitulares.getData().toUpperCase();
        final Long porcentagem = Long.valueOf(coTitulares.getPorcentagem().toString());

        final User transformedUser = new User(nome, data, porcentagem);

        log.info("Converting (" + coTitulares + ") into (" + transformedUser + ")");

        return transformedUser;
    }
    */
}
