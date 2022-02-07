package com.project.ParkingApp.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DateUtils {



    public static String getCurrentDateString(){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }




    public static Long getCurrentTimeInMillis(){
        Calendar cal = Calendar.getInstance();

        return cal.getTimeInMillis();

    }


}
