package com.capelania.service;

import com.capelania.form.OpeningForm;
import com.capelania.model.Opening;
import com.capelania.repository.OpeningRepository;
import com.capelania.response.OpeningDefaultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpeningService extends DefaultService<Opening, OpeningRepository, OpeningForm> {

    @Autowired
    public OpeningService(OpeningRepository repository) {
        super(repository);
    }

    public OpeningDefaultResponse getOpeniningHours() {
        List<Opening> openings = repository.findAllByActive(true);
        return new OpeningDefaultResponse(openings);
    }
}
