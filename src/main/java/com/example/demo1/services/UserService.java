package com.example.demo1.services;

import com.example.demo1.repository.UsersDataAccess;
import com.example.demo1.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    UsersDataAccess usersDataAccess;

    @Autowired
    public UserService(UsersDataAccess usersDataAccess) {
        this.usersDataAccess = usersDataAccess;
    }

    public Optional<User> getUserById(String id) {
        return usersDataAccess.findById(Integer.parseInt(id));
    }

    public List<User> getUsers() {
        return usersDataAccess.findAll();
    }


    public List<User> getUserByName(String name) {
        return usersDataAccess.getByFirstName(name);
    }

    public void createUser(String userJSON) {

        System.out.println("POST(/create) performed");
        User user;
        try {
            if (userJSON != null) {
//                user = new User(new ObjectMapper().readValue(userJSON, User.class).getFirstname(),
//                        new ObjectMapper().readValue(userJSON, User.class).getLastname()
//                );

                // using Lombok
                user = User.builder()
                        .firstname(new ObjectMapper().readValue(userJSON, User.class).getFirstname())
                        .lastname(new ObjectMapper().readValue(userJSON, User.class).getLastname())
                        .build();

                System.out.println("Request Body: " + userJSON);
                usersDataAccess.save(user);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(String userJSON, String id) {
        User user;
        if (userJSON != null) {
            try {
                user = User.builder()
                        .id(Integer.parseInt(id))
                        .firstname(new ObjectMapper().readValue(userJSON, User.class).getFirstname())
                        .lastname(new ObjectMapper().readValue(userJSON, User.class).getLastname())
                        .build();
                System.out.println("UPDATED user with ID: " + user.getId());
                usersDataAccess.save(user);

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteUser(String id) {
        System.out.println("DELETED user with ID: " + id);
        usersDataAccess.deleteById(Integer.parseInt(id));
    }
}
