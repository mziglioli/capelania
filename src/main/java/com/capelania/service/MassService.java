package com.capelania.service;

import com.capelania.form.MassForm;
import com.capelania.model.Mass;
import com.capelania.repository.MassRepository;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MassService extends DefaultService<Mass, MassRepository, MassForm> {

    @Autowired
    public MassService(MassRepository repository) {
        super(repository);
    }

    public List<Mass> findAllWeekly() {
        return repository.findAllByActiveAndWeekly(true, true);
    }

    public List<Mass> findAllSpecial() {
        return repository.findAllByActiveAndWeekly(true, false);
    }

    public List<Mass> findAllOrdered() {
        return findAllActive()
            .stream()
            .sorted(Comparator.comparing(Mass::getDayValue))
            .collect(Collectors.toList());
    }

    //TODO
    public List<Mass> findAllUpComing() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
//        from 1 (Monday) to 7 (Sunday).
        int dayValue = dayOfWeek.getValue();

        return findAllActive();
    }
}
