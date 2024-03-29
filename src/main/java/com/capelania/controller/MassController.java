package com.capelania.controller;

import com.capelania.form.MassForm;
import com.capelania.model.Mass;
import com.capelania.service.MassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ACTUATOR')")
@RequestMapping(value = "/mass")
public class MassController extends DefaultController<MassService, Mass, MassForm> {

    @Autowired
    public MassController(MassService service) {
        super(service);
    }
}