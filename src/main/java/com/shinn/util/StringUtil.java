package com.shinn.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.LoggerFactory;

import com.shinn.dao.factory.AbstractDaoImpl;

public class StringUtil {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(StringUtil.class);
    
    /**
     * 
     * @param o
     * @return null 
     */
    public static Integer toInteger(String o) {
        if(o!=null) {
            try {
                return Integer.parseInt(o);
            }catch(Exception e) {
                logger.debug("Error in casting",e);
            }
        }
        return null;
    }
    /**
     * dd-MMM-yyyy { 7-Jun-2013 }
     * dd/MM/yyyy  { 07/06/2013 }
     * MMM dd, yyyy { Jun 7, 2013 }
     * E, MMM dd yyyy { Fri, June 7 2013 }
     * EEEE, MMM dd, yyyy HH:mm:ss a {  Friday, Jun 7, 2013 12:10:56 PM }
     * @param dateString
     * @param format
     * @return
     */
    public static Date toDate(String dateString, String format){
        if (dateString == null) {
            return null;
        }
        if (format == null) {
            format = "dd/MM/yyyy";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        try {
            Date date = formatter.parse(dateString);
            System.out.println(date);
            System.out.println(formatter.format(date));
            return date;
        }catch(Exception e) {
            logger.debug("Error in casting",e);
        }
        return null; 
    }

    /**
     * 
     * @param value
     * @return
     */
    public static Double toDouble(String value) {
        if(value == null) {
            return 0d;
        }
        try {
            return Double.parseDouble(value);
        }catch(Exception e) {
            logger.debug("Error in casting",e);
        }
        return 0d;
    }
    /**
     * check if string is empty or null
     * @param o
     * @return
     */
    public static boolean isNullOrEmpty(Object o) {
        if (o == null || o.toString().equals("") || o.toString().length() < 0) {
            return true;
        }
        return false;
    }

}
