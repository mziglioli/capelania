package com.capelania.controller;

import com.capelania.form.OpeningForm;
import com.capelania.model.Opening;
import com.capelania.service.OpeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/public/opening")
public class OpeningPublicController extends DefaultPublicController<OpeningService, Opening, OpeningForm> {

    @Autowired
    OpeningPublicController(OpeningService service){
        super(service);
    }
}