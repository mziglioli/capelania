package com.capelania.controller;

import com.capelania.model.User;
import com.capelania.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private UserService userService;

    @Autowired
    public TestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test(){
        return "Test";
    }

    @GetMapping("/public/users")
    public List<User> users(){
        return userService.findAll();
    }
}