package com.envision.automation.framework.reusables;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class JavaUtils {

    public static void getCurrentDate(){
        Date date = new Date();
        System.out.println(date);
    }

    public static void getCurrentDate(String format){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String newFormattedDate =sdf.format(date);
        System.out.println(newFormattedDate);
    }


    public static void main(String[] args) {
        JavaUtils.getCurrentDate("MM/dd/yyyy hh:mm:ss");
        LocalDateTime date =JavaUtils.getFutureDate(5,2,5);
        System.out.println(getMonthNameFromDate(date));

    }

    public static String getDate(){ //MM/DD/YYYY
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.getMonthValue() +"/" + now.getDayOfMonth() + "/" + now.getYear();
        System.out.println(formattedDate);
        return formattedDate;
    }


    public static LocalDateTime getFutureDate(int day, int month, int year){
        LocalDateTime  now = LocalDateTime.now();
        now = now.plusDays(day).plusMonths(month).plusYears(year);
        return now;
    }


    public static String getPastDate(int day, int month, int year){
        LocalDateTime  now = LocalDateTime.now();
        now = now.minusDays(day).minusMonths(month).minusYears(year);
        String formattedDate = now.getMonthValue() +"/" + now.getDayOfMonth() + "/" + now.getYear();
        return formattedDate;
    }

    public static int getDayFromDate(LocalDateTime dateTime ){
        return dateTime.getDayOfMonth();
    }


    public static String getMonthNameFromDate(LocalDateTime dateTime){
        String date =dateTime.getMonth().toString(); //S EPTEMBER //uppercase //S EPTEMBER.toLowercase()
        String firstDateWord =date.substring(0,1); //S
        String secondDateWord = date.substring(1);//EPTEMBER
        secondDateWord = secondDateWord.toLowerCase(); //eptember
        date = firstDateWord+secondDateWord; // S + eptember = September
        System.out.println(date);
        return date;
    }

    public static int getYearFromDate(LocalDateTime dateTime ){
        return dateTime.getYear();
    }


}
