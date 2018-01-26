package com.shinn.util;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    
    public static String YYYYMMDD_DASH = "yyyy-MM-dd";
    public static String YYYYMMDD_BACKSLASH = "yyyy/MM/dd";
    public static int THIRTYDAYS = 30;
    public static int FOURTYFIVEDAYS = 30;
    
    /**
     * 
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
    /**
     * 
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date, String format) {
        if (format == null) {
            format = YYYYMMDD_DASH;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    
    /**
     * 
     * daylight savings utilizes calendar objects
     * @param date1
     * @param date2
     * @return
     */
    public static int daysBetween(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        
        calendar1.setTime(date1);
        calendar2.setTime(date2);
        if (calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)) {
            return Math.abs(calendar1.get(Calendar.DAY_OF_YEAR) - calendar2.get(Calendar.DAY_OF_YEAR));
        } else {
            if (calendar2.get(Calendar.YEAR) > calendar1.get(Calendar.YEAR)) {
                //swap them
                Calendar temp = calendar1;
                calendar1 = calendar2;
                calendar2 = temp;
            }
            int extraDays = 0;
            int dayOneOriginalYearDays = calendar1.get(Calendar.DAY_OF_YEAR);
            while (calendar1.get(Calendar.YEAR) > calendar2.get(Calendar.YEAR)) {
                calendar1.add(Calendar.YEAR, -1);
                // getActualMaximum() important for leap years
                extraDays += calendar1.getActualMaximum(Calendar.DAY_OF_YEAR);
            }
            return extraDays - calendar2.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays ;
        }
    }
    
    public static int daysDiff(Date date1, Date date2) {
      Calendar calendar1 = Calendar.getInstance();
      Calendar calendar2 = Calendar.getInstance();
      
      calendar1.setTime(date1);
      calendar2.setTime(date2);
      if (calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)) {
          return calendar1.get(Calendar.DAY_OF_YEAR) - calendar2.get(Calendar.DAY_OF_YEAR);
      } else {
//          if (calendar2.get(Calendar.YEAR) > calendar1.get(Calendar.YEAR)) {
//              //swap them
//              Calendar temp = calendar1;
//              calendar1 = calendar2;
//              calendar2 = temp;
//          }
          int extraDays = 0;
          int dayOneOriginalYearDays = calendar1.get(Calendar.DAY_OF_YEAR);
          while (calendar1.get(Calendar.YEAR) > calendar2.get(Calendar.YEAR)) {
              calendar1.add(Calendar.YEAR, -1);
              // getActualMaximum() important for leap years
              extraDays += calendar1.getActualMaximum(Calendar.DAY_OF_YEAR);
          }
          return extraDays - calendar2.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays ;
      }
  }
 
    
    public static int getNumberOfMonths(Date from , Date to) {
      int month = 0;
      int days = daysBetween(from, to);
      return days / THIRTYDAYS;
    }
    
    /**
     * 
     * @param num
     * @return
     */
    public static String getNameOfMonth(int num) {
        String month = "";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }
    
    public static Date getCurrentDate() {
      return new Date();
    }
}
