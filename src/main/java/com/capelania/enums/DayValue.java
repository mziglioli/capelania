package com.capelania.enums;

import java.util.List;
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
}
