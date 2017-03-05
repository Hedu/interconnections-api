package com.hedu.ryanair.utils;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by hedu on 5/03/17.
 */
public class JavaUtils {
    public static LocalDate getISOTime(String str) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm");
        LocalDate dt = LocalDate.parse(str, formatter);

        return dt;
    }
}
