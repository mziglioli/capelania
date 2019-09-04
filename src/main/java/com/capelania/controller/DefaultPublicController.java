package com.capelania.controller;

import com.capelania.form.DefaultForm;
import com.capelania.model.EntityJpa;
import com.capelania.service.DefaultService;
import java.util.List;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class DefaultPublicController<S extends DefaultService, E extends EntityJpa, F extends DefaultForm> {

    protected S service;

    @Autowired
    public DefaultPublicController(S service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<E> findAllActive(){
        return service.findAllActive();
    }
}