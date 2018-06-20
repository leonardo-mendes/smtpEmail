package com.energyControl.energycontrol.service;

import com.energyControl.energycontrol.domains.User;
import com.energyControl.energycontrol.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll(){
        return repo.findAll();
    }

    public Optional<User> findById(Integer user){
        return repo.findById(user);
    }

    public User insert(User user){
        return repo.save(user);
    }
}
