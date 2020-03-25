package com.arobs.project.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

public class UtilDate {

    public static Date addDays(Date date, int noOfDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, noOfDays);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date addMonths(Date date, int noOfMonths) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, noOfMonths);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date getNow() {
        return new Date(System.currentTimeMillis());
    }

    public static Timestamp getNowTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
