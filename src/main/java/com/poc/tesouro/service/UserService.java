package com.poc.tesouro.service;

import com.poc.tesouro.model.User;
import com.poc.tesouro.repository.UserRepository;
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
public class UserService {

    @Autowired
    UserRepository repository;

    public List<User> getAllUsers() {
        List<User> userList = repository.findAll();

        if (userList.size() > 0) {
            return userList;
        } else {
            return new ArrayList<User>();
        }
    }

    public ResponseEntity getUserById(Long id) throws Exception {
        Optional<User> user = repository.findById(id);

        if (user.isPresent()) {
            return user.map(record -> ResponseEntity.ok().body(record))
                    .orElse(ResponseEntity.notFound().build());
        } else {
            throw new Exception("No user record exist for given id");
        }
    }

    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 100, maxDelay = 500)) //Retryable in Service
    public ResponseEntity createOrUpdateUser(User entity) throws Exception {
        Optional<User> user = repository.findById(entity.getId());

        if (user.isPresent()) {
            User newEntity = user.get();
            newEntity.setNome(entity.getNome());
            newEntity.setData(entity.getData());
            newEntity.setPorcentagem(entity.getPorcentagem());

            newEntity = repository.save(newEntity);

            return ResponseEntity.ok().build();
        } else {
            entity = repository.save(entity);

            return ResponseEntity.ok().build();
        }
    }

    public ResponseEntity<String> deleteUserById(Long id) throws Exception {
        ResponseEntity<String> user = repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return new ResponseEntity<String>("Usuário deletado com sucesso!!!", HttpStatus.OK);
                }).orElse(new ResponseEntity<>("Usuário não encontrado!!!", HttpStatus.NOT_FOUND));

        if (user.getStatusCode().is2xxSuccessful()) {
            //repository.deleteById(id);
            new ResponseEntity<>("{}", HttpStatus.OK);
        } else {
            new ResponseEntity<String>("Usuário não encontrado com id = ", HttpStatus.NOT_FOUND);
        }
        return user;
    }
}