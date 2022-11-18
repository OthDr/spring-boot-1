package com.example.demo1.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo1.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/user")
public class UserController {


    //GET user
    @GetMapping("/getuser/{id}")
    public String getUser(@PathVariable String id) {

        System.out.println("GET performed");
        User user1 = new User(1, "oth");
        User user2 = new User(2, "moh");
        User user3 = new User(3, "kader");
        User user4 = new User(4, "amine");
        ArrayList<User> users = new ArrayList<User>();

        users.add(user1);users.add(user2);users.add(user3);users.add(user4);

        // Database Query example: find a user by the id provided in request's parameter
        User userX = users.stream().filter(element -> element.getId() == Integer.parseInt(id)).findAny().orElse(null);
        try {
            return new ObjectMapper().writeValueAsString(userX);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



    //POST Request
    @PostMapping("/create")
    public void createUser(@RequestParam("user") String userJSON) {

        System.out.println("POST(/create) performed");

        User user = null;
        try {
            if(userJSON != null){
                user = new ObjectMapper().readValue(userJSON, User.class);
                System.out.println("Request Body: "+ userJSON);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    //DELETE Request
    @DeleteMapping("/delete")
    public User deleteUser(@PathVariable String id){
        System.out.println("User with ID: "+ id);
        return null;
    }



}
