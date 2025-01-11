package com.psps.gcPOC.controller;

import com.psps.gcPOC.model.User;
import com.psps.gcPOC.service.KafkaProducerService;
import com.psps.gcPOC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);


        kafkaProducerService.sendMessage("user-topic", "User created with email: " + savedUser.getEmail());

        return savedUser;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/check")
    public String getEnnathaAcchu() {
        return "Dei Work aitu ta iruku. Vera endpoint check pannu!";
    }
}

