package com.capelania.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.BDDMockito.given;

import com.capelania.model.Mass;
import com.capelania.repository.MassRepository;
import com.capelania.utils.MassUtils;
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
        given(repository.findAllActive()).willReturn(MassUtils.buildMasses());
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

}