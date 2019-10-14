package com.capelania.controller;

import com.capelania.form.EmailForm;
import com.capelania.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/public/contact")
public class ContactPublicController {

    private EmailService service;

    @Autowired
    ContactPublicController(EmailService service){
        this.service = service;
    }
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@Valid @RequestBody EmailForm form, BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
        service.sendSimpleEmail(form);
    }
}