package com.capelania.controller;

import com.capelania.form.EventForm;
import com.capelania.model.Event;
import com.capelania.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/public/event")
public class EventPublicController extends DefaultPublicController<EventService, Event, EventForm> {

    @Autowired
    EventPublicController(EventService service){
        super(service);
    }

    @GetMapping("/upcoming")
    public List<Event> findAllUpComing(){
        return service.findAllUpComing();
    }
}