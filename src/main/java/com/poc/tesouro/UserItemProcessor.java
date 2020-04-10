package com.poc.tesouro;

import com.poc.tesouro.model.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class UserItemProcessor implements ItemProcessor<User, User> {
    private static final Logger log = LoggerFactory.getLogger(UserItemProcessor.class);

    private Set<User> seenUsers = new HashSet<User>();

    public User process(User user) {
        if (seenUsers.contains(user)) {
            log.info("********** USUARIO (" + user.getNome() + ") JÁ EXISTE!!! NÃO REALIZADO!!! **********");
            log.info(String.valueOf("HasSet<>() >>>>> " + seenUsers.size()));
            return null;
        }
        seenUsers.add(user);
        log.info("INSERINDO (" + user.getNome() + " - "
                + user.getData() + " - " +
                +user.getPorcentagem() + ") NO BANCO DE DADOS");
        return user;

    }

    /*
    @Override
    public User process(final User user) throws Exception {
        final String nome = user.getNome().toUpperCase();
        final String data = user.getData().toUpperCase();
        final Long porcentagem = Long.valueOf(user.getPorcentagem().toString());

        final User transformedUser = new User(nome, data, porcentagem);

        log.info("Converting (" + user + ") into (" + transformedUser + ")");

        return transformedUser;
    }
    */
}
