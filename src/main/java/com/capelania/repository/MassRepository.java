package com.capelania.repository;

import com.capelania.model.Mass;
import java.util.List;

public interface MassRepository extends DefaultRepository<Mass> {

    List<Mass> findAllByActiveAndWeekly(boolean active, boolean weekly);
}