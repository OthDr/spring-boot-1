package com.example.demo1.services;

import com.example.demo1.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Optional<User> getUserById(String id);

    List<User> getUsers();

    List<User> getUserByName(String name);

    void createUser(String userJSON);

    void updateUser(String userJSON, String id);

    void deleteUser(String id);
}
