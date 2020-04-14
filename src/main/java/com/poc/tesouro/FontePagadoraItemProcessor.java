package com.poc.tesouro;

import com.poc.tesouro.model.FontePagadora;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class FontePagadoraItemProcessor implements ItemProcessor<FontePagadora, FontePagadora> {
    private static final Logger log = LoggerFactory.getLogger(FontePagadoraItemProcessor.class);

    private Set<FontePagadora> seenFontePagadoras = new HashSet<FontePagadora>();

    public FontePagadora process(FontePagadora fontePagadora) {
        if (seenFontePagadoras.contains(fontePagadora)) {
            log.info("********** FONTE_PAGADORA (" + fontePagadora.getNome() +
                    fontePagadora.getCnpj() + " - " + fontePagadora.getSaldos() + ") JÁ EXISTE!!! NÃO REALIZADO!!! **********");
            log.info(String.valueOf("HasSet<>() >>>>> " + seenFontePagadoras.size()));
            return null;
        }
        seenFontePagadoras.add(fontePagadora);
        log.info("INSERINDO (" + fontePagadora.getNome() +
                fontePagadora.getCnpj() + " - " + fontePagadora.getSaldos() + ") NO BANCO DE DADOS");
        return fontePagadora;

    }

    /*
    @Override
    public User process(final User fontePagadora) throws Exception {
        final String nome = fontePagadora.getNome().toUpperCase();
        final String data = fontePagadora.getData().toUpperCase();
        final Long porcentagem = Long.valueOf(fontePagadora.getPorcentagem().toString());

        final User transformedUser = new User(nome, data, porcentagem);

        log.info("Converting (" + fontePagadora + ") into (" + transformedUser + ")");

        return transformedUser;
    }
    */
}
