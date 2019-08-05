package com.capelania.service;

import com.capelania.form.MassForm;
import com.capelania.model.Mass;
import com.capelania.repository.MassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MassService extends DefaultService<Mass, MassRepository, MassForm> {

    @Autowired
    public MassService(MassRepository repository) {
        super(repository);
    }
}
