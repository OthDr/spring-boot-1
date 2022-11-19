package com.example.demo1.controllers;

import com.example.demo1.database.UsersDataAccess;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UsersDataAccess usersDataAccess;


    //GET user
    @GetMapping("/getuser/{id}")
    public String getUser(@PathVariable String id) {

        System.out.println("GET performed");
        User user1 = new User(1, "oth", "dr");
        User user2 = new User(2, "moh", "dr");
        User user3 = new User(3, "kader", "dr");
        User user4 = new User(4, "amine", "ldj");
        ArrayList<User> users = new ArrayList<User>();

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        // Database Query example: find a user by the id provided in request's parameter
        User userX = users.stream().filter(element -> element.getId() == Integer.parseInt(id)).findAny().orElse(null);
        try {
            return new ObjectMapper().writeValueAsString(userX);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/allusers")
    public List<User> getUsers() {
        return usersDataAccess.getAllUsers();
    }


    //POST Request
    @PostMapping("/create")
    public void createUser(@RequestParam("user") String userJSON) {

        System.out.println("POST(/create) performed");

        User user = null;
        try {
            if (userJSON != null) {
                user = new User(new ObjectMapper().readValue(userJSON, User.class).getFirstname(),
                        new ObjectMapper().readValue(userJSON, User.class).getLastname()
                );
                System.out.println("Request Body: " + userJSON);
                usersDataAccess.insertUser(user);

            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    //PUT Request to update user
    @PutMapping("/update/{id}")
    public int putUser(@RequestParam("user") String userJSON, @PathVariable String id) {
        User user = null;
        if (userJSON != null) {
            try {
                user = new User(Integer.parseInt(id),
                        new ObjectMapper().readValue(userJSON, User.class).getFirstname(),
                        new ObjectMapper().readValue(userJSON, User.class).getLastname()
                );
                System.out.println("UPDATED user with ID: " + user.getId());

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return usersDataAccess.updateUser(user);
    }

    //DELETE Request
    @DeleteMapping("/delete/{id}")
    public int deleteUser(@PathVariable String id) {
        System.out.println("DELETED user with ID: " + id);
        return usersDataAccess.deleteUser(Integer.parseInt(id));
    }


}
