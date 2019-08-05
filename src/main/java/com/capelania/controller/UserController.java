package com.capelania.controller;

import com.capelania.form.UserForm;
import com.capelania.model.User;
import com.capelania.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@Valid @RequestBody UserForm form, BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
        service.add(form);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody UserForm form, BindingResult bindingResult) throws BindException {
        if(form.getId() == 0) {
            bindingResult.addError(new ObjectError("id", "validator.invalid.id"));
        }
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
        service.update(form);
    }

    @PreAuthorize("hasRole('ACTUATOR')")
    @GetMapping("/all")
    public List<User> findAll() {
        return service.findAll();
    }

    @PreAuthorize("hasRole('ACTUATOR')")
    @GetMapping("/{id}")
    public User findById(@PathVariable long id) {
        return service.findById(id);
    }
}