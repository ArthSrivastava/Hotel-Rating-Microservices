package com.userservice.userservice.services.impl;

import com.userservice.userservice.entities.User;
import com.userservice.userservice.exceptions.ResourceNotFoundException;
import com.userservice.userservice.repositories.UserRepo;
import com.userservice.userservice.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User createUser(User user) {
        String id = UUID.randomUUID().toString();
        user.setId(id);
        return userRepo.save(user);
    }

    @Override
    public User getUserById(String id) {
        return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
