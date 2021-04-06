package ru.laskin.myWebApp.utils;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class TimeUtils {
    public String timestampToDate(Timestamp timestamp){
        Date date = new Date(timestamp.getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        String localDate = localDateTime.toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String localTime = localDateTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
        return localDate + " " + localTime;
    }
}
