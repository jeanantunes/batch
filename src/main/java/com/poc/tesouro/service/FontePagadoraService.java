package com.poc.tesouro.service;

import com.poc.tesouro.model.FontePagadora;
import com.poc.tesouro.repository.FontePagadoraRepository;
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
public class FontePagadoraService {

    @Autowired
    FontePagadoraRepository repository;

    public List<FontePagadora> getAllFontePagadoras() {
        List<FontePagadora> userList = repository.findAll();

        if (userList.size() > 0) {
            return userList;
        } else {
            return new ArrayList<FontePagadora>();
        }
    }

    public ResponseEntity getFontePagadoraById(Long id) throws Exception {
        Optional<FontePagadora> user = repository.findById(id);

        if (user.isPresent()) {
            return user.map(record -> ResponseEntity.ok().body(record))
                    .orElse(ResponseEntity.notFound().build());
        } else {
            throw new Exception("FontePagadora não localizado!!");
            //new ResponseEntity<>("FontePagadora não localizado!!!", HttpStatus.NOT_FOUND)
        }
    }

    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 100, maxDelay = 500)) //Retryable in Service
    public ResponseEntity createOrUpdateFontePagadora(FontePagadora entity) throws Exception {
        Optional<FontePagadora> user = repository.findById(entity.getId());

        if (user.isPresent()) {
            FontePagadora newEntity = user.get();
            newEntity.setNome(entity.getNome());
            newEntity.setCnpj(entity.getCnpj());
            newEntity = repository.save(newEntity);

            return ResponseEntity.ok().build();
        } else {
            entity = repository.save(entity);

            return ResponseEntity.ok().build();
        }
    }

    public ResponseEntity<String> deleteFontePagadoraById(Long id) throws Exception {
        ResponseEntity<String> user = repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return new ResponseEntity<String>("FontePagadora deletado com sucesso!!!", HttpStatus.OK);
                }).orElse(new ResponseEntity<>("FontePagadora não localizado!!!", HttpStatus.NOT_FOUND));

        if (user.getStatusCode().is2xxSuccessful()) {
            //repository.deleteById(id);
            new ResponseEntity<>("{}", HttpStatus.OK);
        } else {
            new ResponseEntity<String>("FontePagadora não encontrado com id = ", HttpStatus.NOT_FOUND);
        }
        return user;
    }
}