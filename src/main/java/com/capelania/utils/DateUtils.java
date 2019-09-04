package com.capelania.utils;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private final static DateTimeFormatter DATE_FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Parse LocalDateTime to string
     * @param dateTime
     * @return string in the format "yyyy-MM-dd HH:mm"}
     * */
    public static String parse(LocalDateTime dateTime) {
        try{
            if (dateTime == null) {
                return LocalDateTime.now().format(DATE_FORMATER);
            }
            return dateTime.format(DATE_FORMATER);
        }catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Parse string to {@link LocalDateTime}
     * @param dateTime need to be in a format "yyyy-MM-dd HH:mm"
     * @return LocalDateTime for the datetime or from {@link LocalDateTime#now()}
     * */
    public static LocalDateTime parse(String dateTime) {
        try{
            if (isBlank(dateTime)) {
                return LocalDateTime.now();
            }
            return LocalDateTime.parse(dateTime, DATE_FORMATER);
        }catch (Exception e) {
            e.printStackTrace();
            return LocalDateTime.now();
        }
    }
}
