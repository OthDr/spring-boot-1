package com.example.demo1.controllers;

import com.example.demo1.models.User;
import com.example.demo1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UserController {


    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //GET user
    @GetMapping("/id={id}")
    public Optional<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/all")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/name={name}")
    public List<User> getUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }

    @PostMapping("/create")
    public void createUser(@RequestParam("user") String userJSON) {
        userService.createUser(userJSON);
    }

    //PUT Request to update user
    @PutMapping("/update/id={id}")
    public void putUser(@RequestParam("user") String userJSON, @PathVariable String id) {
        userService.updateUser(userJSON, id);
    }

    //DELETE Request
    @DeleteMapping("/delete/id={id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }


}
