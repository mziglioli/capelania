package com.capelania.service;

import com.capelania.form.EventForm;
import com.capelania.model.Event;
import com.capelania.repository.EventRepository;
import com.capelania.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService extends DefaultService<Event, EventRepository, EventForm> {

    @Autowired
    public EventService(EventRepository repository) {
        super(repository);
    }

    /**
     * Find the events for 7 days
     * */
    public List<Event> findAllUpComing() {
        return findAllUpComing(LocalDateTime.now());
    }

    public List<Event> findAllUpComing(LocalDateTime today) {
        return findAllActive()
                .stream()
                .filter(e -> isEventDateInRange(today, e))
                .sorted(Comparator.comparing(m -> m.getDate()))
                .collect(Collectors.toList());
    }

    protected boolean isEventDateInRange(LocalDateTime today, Event event) {
        LocalDateTime dbDate = DateUtils.parse(today, event.getDate());
        return DateUtils.isInOneWeekTime(today, dbDate);
    }

}
