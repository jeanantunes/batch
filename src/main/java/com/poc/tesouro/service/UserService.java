package com.poc.tesouro.service;

import com.poc.tesouro.repository.UserRepository;
import com.poc.tesouro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Service
public class UserService {
/*
    @Autowired
    UserRepository repository;

    public List<User> getAllEmployees()
    {
        List<User> userList = repository.findAll();

        if(userList.size() > 0) {
            return userList;
        } else {
            return new ArrayList<User>();
        }
    }

    public User getEmployeeById(Long id) throws Exception
    {
        Optional<User> user = repository.findById(id);

        if(user.isPresent()) {
            return user.get();
        } else {
            throw new Exception("No user record exist for given id");
        }
    }

    public User createOrUpdateEmployee(User entity) throws Exception
    {
        Optional<User> user = repository.findById(entity.getId());

        if(user.isPresent())
        {
            User newEntity = user.get();
            newEntity.setName(entity.getName());
            newEntity.setDescription(entity.getDescription());

            newEntity = repository.save(newEntity);

            return newEntity;
        } else {
            entity = repository.save(entity);

            return entity;
        }
    }

    public void deleteEmployeeById(Long id) throws Exception
    {
        Optional<User> user = repository.findById(id);

        if(user.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new Exception("No user record exist for given id");
        }
    }
 */
}