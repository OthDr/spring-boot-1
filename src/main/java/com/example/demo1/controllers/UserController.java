package com.example.demo1.controllers;

import com.example.demo1.database.UsersDataAccess;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UsersDataAccess usersDataAccess;


    //GET user
    @GetMapping("/id={id}")
    public Optional<User> getUserById(@PathVariable String id) {
        return usersDataAccess.findById(Integer.parseInt(id));
    }

    @GetMapping("/all")
    public List<User> getUsers() {
        return usersDataAccess.findAll();
    }

    @GetMapping("/name={name}")
    public List<User> getUserByName(@PathVariable String name) {
        return usersDataAccess.getByFirstName(name);
    }

    @PostMapping("/create")
    public void createUser(@RequestParam("user") String userJSON) {

        System.out.println("POST(/create) performed");
        User user ;
        try {
            if (userJSON != null) {
                user = new User(new ObjectMapper().readValue(userJSON, User.class).getFirstname(),
                        new ObjectMapper().readValue(userJSON, User.class).getLastname()
                );
                System.out.println("Request Body: " + userJSON);
                usersDataAccess.save(user);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    //PUT Request to update user
    @PutMapping("/update/id={id}")
    public void putUser(@RequestParam("user") String userJSON, @PathVariable String id) {
        User user ;
        if (userJSON != null) {
            try {
                user = new User(Integer.parseInt(id),
                        new ObjectMapper().readValue(userJSON, User.class).getFirstname(),
                        new ObjectMapper().readValue(userJSON, User.class).getLastname()
                );
                System.out.println("UPDATED user with ID: " + user.getId());
                usersDataAccess.save(user);

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //DELETE Request
    @DeleteMapping("/delete/id={id}")
    public void deleteUser(@PathVariable String id) {
        System.out.println("DELETED user with ID: " + id);
        usersDataAccess.deleteById(Integer.parseInt(id));
    }


}
