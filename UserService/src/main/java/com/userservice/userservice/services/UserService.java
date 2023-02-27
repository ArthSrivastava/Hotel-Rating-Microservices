package com.userservice.userservice.services;

import com.userservice.userservice.entities.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(String id);
    List<User> getAllUsers();
}
