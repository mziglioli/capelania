package com.capelania.controller;

import com.capelania.response.DefaultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

    @GetMapping("/public/logout/success")
    public DefaultResponse logout(){
        return DefaultResponse.builder().success(true).build();
    }
}