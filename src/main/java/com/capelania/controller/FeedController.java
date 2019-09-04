package com.capelania.controller;

import com.capelania.form.FeedForm;
import com.capelania.model.Feed;
import com.capelania.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ACTUATOR')")
@RequestMapping(value = "/feed")
public class FeedController extends DefaultController<FeedService, Feed, FeedForm> {

    @Autowired
    public FeedController(FeedService service) {
        super(service);
    }
}