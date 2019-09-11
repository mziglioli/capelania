package com.capelania.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.BDDMockito.given;

import com.capelania.model.Mass;
import com.capelania.repository.MassRepository;
import com.capelania.utils.MassUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MassServiceTest {
 
    @MockBean
    private MassRepository repository;
    private MassService service;

    @Before
    public void setup() {
        service = new MassService(repository);
        given(repository.findAllByActive(true)).willReturn(MassUtils.buildMasses());
    }

    @Test
    public void test_findAllActive() {
        List<Mass> masses = service.findAllActive();
        assertFalse(masses.isEmpty());
        assertEquals(4, masses.size());
    }

    @Test
    public void test_findAllOrdered() {
        List<Mass> masses = service.findAllOrdered();
        assertFalse(masses.isEmpty());
        assertEquals("Wednesday", masses.get(0).getDay());
        assertEquals("Thursday", masses.get(1).getDay());
        assertEquals("Sunday", masses.get(2).getDay());
        assertEquals("Sunday", masses.get(3).getDay());
    }

    @Test
    public void test_findAllUpComing() {

        List<Mass> listMass = MassUtils.buildMasses();
        listMass.add(MassUtils.buildMass(5, false, "Wednesday", "9-4", "10:00 am", 60, true));
        listMass.add(MassUtils.buildMass(6, false, "Sunday", "9-8", "10:00 am", 60, true));
        given(repository.findAllByActive(true)).willReturn(listMass);

        //Wednesday
        LocalDate testDate = LocalDate.of(2019, 9, 4);

        List<Mass> masses = service.findAllUpComing(testDate);
        assertFalse(masses.isEmpty());
        assertEquals("Wednesday", masses.get(0).getDay());
        assertEquals("Thursday", masses.get(1).getDay());
        assertEquals("Sunday", masses.get(2).getDay());
        assertEquals("Sunday", masses.get(3).getDay());

        //Fridat
        testDate = LocalDate.of(2019, 9, 6);

        masses = service.findAllUpComing(testDate);
        assertFalse(masses.isEmpty());
        assertEquals("Sunday", masses.get(0).getDay());
        assertEquals("Sunday", masses.get(1).getDay());
        assertEquals("Wednesday", masses.get(2).getDay());
        assertEquals("Thursday", masses.get(3).getDay());
    }

    @Test
    public void test_findAllUpComing_WeeklyOnly() {
        //Wednesday
        LocalDate testDate = LocalDate.of(2019, 9, 4);

        List<Mass> masses = service.findAllUpComing(testDate);
        assertFalse(masses.isEmpty());
        assertEquals("Thursday", masses.get(0).getDay());
        assertEquals("Sunday", masses.get(1).getDay());

        //Friday
        testDate = LocalDate.of(2019, 9, 6);

        masses = service.findAllUpComing(testDate);
        assertFalse(masses.isEmpty());
        assertEquals("Sunday", masses.get(0).getDay());
        assertEquals("Thursday", masses.get(1).getDay());
    }

}