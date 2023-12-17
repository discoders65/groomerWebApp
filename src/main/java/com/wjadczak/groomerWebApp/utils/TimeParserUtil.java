package com.wjadczak.groomerWebApp.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeParserUtil {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static LocalDateTime parseDateTime(String dateTime){
        return LocalDateTime.parse(dateTime, formatter);
    }

}
