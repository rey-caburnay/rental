package com.shinn.util;

public class RentStatus {
    
    public static final String ACTIVE = "active";
    public static final String PAID = "paid"; 
    public static final String OCCUPIED = "occupied";
    public static final int NOTIFY_BEFORE_DUE = 3;
    public static final int NOTIFY_ON_DUE = 0;
    public static String DUE_DATE_MESSAGE = "Please pay your rent for the month of {month} amounting:{amount} to avoid neccessary actions.";
    public static String BEFORE_DUE_MESSAGE = "This is to inform you that your rental fee(s) will due on {duedate} with total amount of {amount} "
            + "Please pay on before due date.";

}
