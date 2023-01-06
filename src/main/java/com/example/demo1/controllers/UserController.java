package com.example.demo1.controllers;

import com.example.demo1.entity.User;
import com.example.demo1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getUserByName(@PathVariable String name) {
        List<User> users = userService.getUserByName(name);
        if (!users.isEmpty()){
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/create")
    public void createUser(@RequestBody String user) {
        userService.createUser(user);
    }

    //PUT Request to update user
    @PutMapping("/update/id={id}")
    public void putUser(@RequestBody String userJSON, @PathVariable String id) {
        userService.updateUser(userJSON, id);
    }

    //DELETE Request
    @DeleteMapping("/delete/id={id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }


}
