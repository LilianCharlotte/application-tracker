package com.github.liliancharlotte.service;

import com.github.liliancharlotte.model.User;
import com.github.liliancharlotte.model.UserRequest;
import com.github.liliancharlotte.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final IdService idService;

    @Autowired
    public UserService(UserRepo userRepo, IdService idService) {
        this.userRepo = userRepo;
        this.idService = idService;
    }

    public List<User> list() {
        return userRepo.findAll();
    }

    public User createUser(UserRequest userRequest, String id) {
        return new User(id, userRequest.name(), new ArrayList<>());
    }

    public User addUser(UserRequest userRequest) {
        String generatedUserId = idService.generateId();
        User user = createUser(userRequest, generatedUserId);
        return userRepo.save(user);
    }

}