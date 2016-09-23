package com.shinn.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    
    public static String YYYYMMDD_DASH = "yyyy-MM-dd";
    public static String YYYYMMDD_BACKSLASH = "yyyy/MM/dd";
    public static int THIRTYDAYS = 30;
    
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
}
