package com.capelania.service;

import com.capelania.form.EventForm;
import com.capelania.model.Event;
import com.capelania.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService extends DefaultService<Event, EventRepository, EventForm> {

    @Autowired
    public EventService(EventRepository repository) {
        super(repository);
    }
}
