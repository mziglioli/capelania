package com.capelania.controller;

import com.capelania.response.DefaultResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000", "http://capelania-s3-1.s3-website.eu-west-2.amazonaws.com"})
@RestController
public class LogoutController {

    @GetMapping("/public/logout/success")
    public DefaultResponse logout(){
        return DefaultResponse.builder().success(true).build();
    }

    @GetMapping("/public/test")
    public String test(){
        return "Test";
    }
}