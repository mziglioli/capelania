package com.capelania.controller;

import com.capelania.form.UserForm;
import com.capelania.model.User;
import com.capelania.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(value = "/user")
public class UserController extends DefaultController<UserService, User, UserForm> {

    @Autowired
    public UserController(UserService service) {
        super(service);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/auth")
    public User getAuthUser() {
        return service.getAuthenticatedUser();
    }
}