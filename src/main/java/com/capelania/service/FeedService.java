package com.capelania.service;

import com.capelania.form.FeedForm;
import com.capelania.model.Feed;
import com.capelania.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedService extends DefaultService<Feed, FeedRepository, FeedForm> {

    @Autowired
    public FeedService(FeedRepository repository) {
        super(repository);
    }
}
