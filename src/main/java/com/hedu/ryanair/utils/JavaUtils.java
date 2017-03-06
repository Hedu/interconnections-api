package com.hedu.ryanair.utils;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by hedu on 5/03/17.
 */
public class JavaUtils {
    public static LocalDateTime getISOTime(String str) {
        str = str.replace('T', ' ');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dt = LocalDateTime.parse(str, formatter);

        return dt;
    }

    public static  String getISOTime(LocalDateTime localTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formatDateTime = localTime.format(formatter);

        return formatDateTime.replace(' ', 'T');
    }

    public static LocalTime getTime(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse(str, formatter);

        return localTime;
    }
}
