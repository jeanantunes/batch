package com.poc.tesouro.service;

import com.poc.tesouro.model.CoTitulares;
import com.poc.tesouro.repository.CoTitularesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoTitularesService {

    @Autowired
    CoTitularesRepository repository;

    public List<CoTitulares> getAllCoTitularess() {
        List<CoTitulares> userList = repository.findAll();

        if (userList.size() > 0) {
            return userList;
        } else {
            return new ArrayList<CoTitulares>();
        }
    }

    public ResponseEntity getCoTitularesById(Long id) throws Exception {
        Optional<CoTitulares> user = repository.findById(id);

        if (user.isPresent()) {
            return user.map(record -> ResponseEntity.ok().body(record))
                    .orElse(ResponseEntity.notFound().build());
        } else {
            throw new Exception("CoTitulares não localizado!!");
            //new ResponseEntity<>("CoTitulares não localizado!!!", HttpStatus.NOT_FOUND)
        }
    }

    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 100, maxDelay = 500)) //Retryable in Service
    public ResponseEntity createOrUpdateCoTitulares(CoTitulares entity) throws Exception {
        Optional<CoTitulares> user = repository.findById(entity.getId());

        if (user.isPresent()) {
            CoTitulares newEntity = user.get();
            newEntity.setNomeDoCotitular(entity.getNomeDoCotitular());
            newEntity.setCpfCliente(entity.getCpfCliente());
            newEntity = repository.save(newEntity);

            return ResponseEntity.ok().build();
        } else {
            entity = repository.save(entity);

            return ResponseEntity.ok().build();
        }
    }

    public ResponseEntity<String> deleteCoTitularesById(Long id) throws Exception {
        ResponseEntity<String> user = repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return new ResponseEntity<String>("CoTitulares deletado com sucesso!!!", HttpStatus.OK);
                }).orElse(new ResponseEntity<>("CoTitulares não localizado!!!", HttpStatus.NOT_FOUND));

        if (user.getStatusCode().is2xxSuccessful()) {
            //repository.deleteById(id);
            new ResponseEntity<>("{}", HttpStatus.OK);
        } else {
            new ResponseEntity<String>("CoTitulares não encontrado com id = ", HttpStatus.NOT_FOUND);
        }
        return user;
    }
}