package com.poc.tesouro;

import com.poc.tesouro.model.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ClienteItemProcessor implements ItemProcessor<Cliente, Cliente> {
    private static final Logger log = LoggerFactory.getLogger(ClienteItemProcessor.class);

    private Set<Cliente> seenClientes = new HashSet<Cliente>();

    public Cliente process(Cliente cliente) {
        if (seenClientes.contains(cliente)) {
            log.info("********** CLIENTE (" + cliente.getNome() + ") JÁ EXISTE!!! NÃO REALIZADO!!! **********");
            log.info(String.valueOf("HasSet<>() >>>>> " + seenClientes.size()));
            return null;
        }
        seenClientes.add(cliente);
        log.info("INSERINDO (" + cliente.getNome() + " - "
                + cliente.getCpf() + " - " + ") NO BANCO DE DADOS");
        //+ cliente.getCliente() + " - " +
        //+ cliente.getListaCoTitulares() + ") NO BANCO DE DADOS");
        return cliente;

    }

    /*
    @Override
    public User process(final User cliente) throws Exception {
        final String nome = cliente.getNome().toUpperCase();
        final String data = cliente.getData().toUpperCase();
        final Long porcentagem = Long.valueOf(cliente.getPorcentagem().toString());

        final User transformedUser = new User(nome, data, porcentagem);

        log.info("Converting (" + cliente + ") into (" + transformedUser + ")");

        return transformedUser;
    }
    */
}
