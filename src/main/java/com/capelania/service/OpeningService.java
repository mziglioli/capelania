package com.capelania.service;

import com.capelania.form.OpeningForm;
import com.capelania.model.Opening;
import com.capelania.repository.OpeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpeningService extends DefaultService<Opening, OpeningRepository, OpeningForm> {

    @Autowired
    public OpeningService(OpeningRepository repository) {
        super(repository);
    }

}
