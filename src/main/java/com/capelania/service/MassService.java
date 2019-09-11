package com.capelania.service;

import com.capelania.form.MassForm;
import com.capelania.model.Mass;
import com.capelania.repository.MassRepository;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.capelania.utils.DateUtils;
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

    /**
     * Find the Mass for 7 days
     * */
    public List<Mass> findAllUpComing() {
        return findAllUpComing(LocalDate.now());
    }

    public List<Mass> findAllUpComing(LocalDate today) {
        return findAllActive()
                .stream()
                .filter(m -> m.isWeekly() || isMassDateInRange(today, m))
                .sorted(Comparator.comparing(m -> m.getDayValue(today)))
                .collect(Collectors.toList());
    }

    protected boolean isMassDateInRange(LocalDate today, Mass mass) {
        LocalDate dbDate = DateUtils.parseDbDate(today, mass.getDate());
        return DateUtils.isInOneWeekTime(today, dbDate);
    }
}
