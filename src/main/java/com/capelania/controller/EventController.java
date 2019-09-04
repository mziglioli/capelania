package com.capelania.controller;

import com.capelania.form.EventForm;
import com.capelania.model.Event;
import com.capelania.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ACTUATOR')")
@RequestMapping(value = "/event")
public class EventController extends DefaultController<EventService, Event, EventForm> {

    @Autowired
    public EventController(EventService service) {
        super(service);
    }
}