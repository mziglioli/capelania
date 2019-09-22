package com.capelania.utils;

import com.capelania.model.Event;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private final static DateTimeFormatter DATE_TIME_FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final static DateTimeFormatter DATE_FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Parse LocalDateTime to string
     * @param dateTime
     * @return string in the format "yyyy-MM-dd HH:mm"}
     * */
    public static String parse(LocalDateTime dateTime) {
        try{
            if (dateTime == null) {
                return LocalDateTime.now().format(DATE_TIME_FORMATER);
            }
            return dateTime.format(DATE_TIME_FORMATER);
        }catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Parse LocalDateTime.now() to string
     * @return string in the format "yyyy-MM-dd HH:mm"}
     * */
    public static String parse() {
        return parse(LocalDateTime.now());
    }

    /**
     * Parse string to {@link LocalDateTime}
     * @param now localDateTime
     * @param dateTime need to be in a format "yyyy-MM-dd HH:mm"
     * @return LocalDateTime for the datetime or from now
     * */
    public static LocalDateTime parse(LocalDateTime now, String dateTime) {
        try{
            if (isBlank(dateTime)) {
                return now;
            }
            return LocalDateTime.parse(dateTime, DATE_TIME_FORMATER);
        }catch (Exception e) {
            e.printStackTrace();
            return now;
        }
    }

    /**
     * Parse string to {@link LocalDateTime}
     * @param dateTime need to be in a format "yyyy-MM-dd HH:mm"
     * @return LocalDateTime for the datetime or from {@link LocalDateTime#now()}
     * */
    public static LocalDateTime parse(String dateTime) {
        return parse(LocalDateTime.now(), dateTime);
    }

    /**
     * Parse string to {@link LocalDate}
     * @param today
     * @param dbDate need to be in a format "MM-dd"
     * @return LocalDateTime for the datetime or from today
     * */
    public static LocalDate parseDbDate(LocalDate today, String dbDate) {
        try{
            if (isBlank(dbDate)) {
                return today;
            }
            String date = today.getYear() + "-" + dbDate;
            return LocalDate.parse(date, DATE_FORMATER);
        }catch (Exception e) {
            e.printStackTrace();
            return today;
        }
    }

    public static boolean isInTime(LocalDate today, LocalDate date, int qtdeDays) {
        if(today != null && date != null) {
            return (date.isAfter(today) || date.isEqual(today)) && date.isBefore(today.plusDays(qtdeDays));
        }
        return false;
    }

    public static boolean isInOneWeekTime(LocalDate today, LocalDate date) {
        return isInTime(today, date, 7);
    }

    public static boolean isInOneWeekTime(LocalDateTime today, LocalDateTime date) {
        return isInTime(today.toLocalDate(), date.toLocalDate(), 7);
    }

    protected boolean isStringDateInRange(LocalDate today, String date) {
        LocalDate dbDate = DateUtils.parseDbDate(today, date);
        return DateUtils.isInOneWeekTime(today, dbDate);
    }

}
