package com.capelania.utils;

import com.capelania.model.Mass;
import java.util.ArrayList;
import java.util.List;

public class MassUtils {

    public static List<Mass> buildMasses() {
        List<Mass> masses = new ArrayList<>();
        masses.add(buildWeeklyMass(1, "Sunday", "11:00 am"));
        masses.add(buildWeeklyMass(2, "Thursday", "7:00 pm"));
        masses.add(buildEasterMass(3));
        masses.add(buildChristmassMass(4));
        return masses;
    }

    public static Mass buildWeeklyMass(long id, String day, String start) {
        return buildMass(id, true, day, "", start, 60, true);
    }
    public static Mass buildEasterMass(long id) {
        return buildMass(id, false, "Sunday","12/04", "10:00 am", 60, true);
    }
    public static Mass buildChristmassMass(long id) {
        return buildMass(id, false, "Wednesday", "25/12", "10:00 am", 60, true);
    }

    public static Mass buildMass(long id, boolean weekly, String day, String date, String start, int duration, boolean active) {
        Mass mass = new Mass();
        mass.setId(id);
        mass.setTitle("title: " + id);
        mass.setWeekly(weekly);
        mass.setDay(day);
        mass.setDate(date);
        mass.setStart(start);
        mass.setDuration(duration);
        mass.setDescription("description: " + id);
        mass.setActive(active);
        return mass;
    }
}
