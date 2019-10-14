package com.capelania.enums;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum DayValue {

    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);
    public int value;

    public static int getValue(String day) {
        return Stream.of(DayValue.values())
            .filter(dayValue -> StringUtils.equalsIgnoreCase(dayValue.name(), day))
            .findFirst()
            .orElse(DayValue.SUNDAY).getValue();
    }

    public static int getValue(String day, int dayOfTheWeek) {
        int dayValue = getValue(day);

        if (dayValue < dayOfTheWeek) {
            return dayValue + 10;
        }

        return dayValue;
    }

    public static int getValueFromToday(String day, LocalDate localDate) {
        return getValue(day, localDate.getDayOfWeek().getValue());
    }

    public static int getValueFromToday(String day) {
        return getValueFromToday(day, LocalDate.now());
    }

    public static String getDayName(String day){
        Optional<DayValue> op = Stream.of(DayValue.values())
                .filter(dayValue -> StringUtils.equalsIgnoreCase(dayValue.name(), day))
                .findFirst();
        return op.isPresent() ? op.get().name() : "";
    }

}
