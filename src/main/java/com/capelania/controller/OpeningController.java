package com.capelania.controller;

import com.capelania.form.OpeningForm;
import com.capelania.model.Opening;
import com.capelania.service.OpeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ACTUATOR')")
@RequestMapping(value = "/opening")
public class OpeningController extends DefaultController<OpeningService, Opening, OpeningForm> {

    @Autowired
    public OpeningController(OpeningService service) {
        super(service);
    }
}