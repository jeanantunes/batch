package com.poc.tesouro.service;

import com.poc.tesouro.model.Cliente;
import com.poc.tesouro.repository.ClienteRepository;
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
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    public List<Cliente> getAllClientes() {
        List<Cliente> userList = repository.findAll();

        if (userList.size() > 0) {
            return userList;
        } else {
            return new ArrayList<Cliente>();
        }
    }

    public ResponseEntity getClienteById(Long id) throws Exception {
        Optional<Cliente> user = repository.findById(id);

        if (user.isPresent()) {
            return user.map(record -> ResponseEntity.ok().body(record))
                    .orElse(ResponseEntity.notFound().build());
        } else {
            throw new Exception("Cliente não localizado!!");
            //new ResponseEntity<>("Cliente não localizado!!!", HttpStatus.NOT_FOUND)
        }
    }

    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 100, maxDelay = 500)) //Retryable in Service
    public ResponseEntity createOrUpdateCliente(Cliente entity) throws Exception {
        Optional<Cliente> user = repository.findById(entity.getId());

        if (user.isPresent()) {
            Cliente newEntity = user.get();
            newEntity.setNome(entity.getNome());
            newEntity.setCpf(entity.getCpf());
            newEntity.setCliente(entity.getCliente());
            newEntity.setListaCoTitulares(entity.getListaCoTitulares());

            newEntity = repository.save(newEntity);

            return ResponseEntity.ok().build();
        } else {
            entity = repository.save(entity);

            return ResponseEntity.ok().build();
        }
    }

    public ResponseEntity<String> deleteClienteById(Long id) throws Exception {
        ResponseEntity<String> user = repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return new ResponseEntity<String>("Cliente deletado com sucesso!!!", HttpStatus.OK);
                }).orElse(new ResponseEntity<>("Cliente não localizado!!!", HttpStatus.NOT_FOUND));

        if (user.getStatusCode().is2xxSuccessful()) {
            //repository.deleteById(id);
            new ResponseEntity<>("{}", HttpStatus.OK);
        } else {
            new ResponseEntity<String>("Cliente não encontrado com id = ", HttpStatus.NOT_FOUND);
        }
        return user;
    }
}