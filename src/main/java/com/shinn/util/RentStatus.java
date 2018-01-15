package com.shinn.util;

public class RentStatus {
    
    public static final String ACTIVE = "active";
    public static final String PAID = "paid"; 
    public static final String UNPAID = "unpaid";
    public static final String PARTIAL = "partial";
    public static final String BALANCE = "balance";
    public static final String OCCUPIED = "occupied";
    public static final String VACANT = "vacant";
    public static final String UNBILLED_MESSAGE = "Unpaid month:";
    public static final int NOTIFY_BEFORE_DUE = 3;
    public static final int NOTIFY_ON_DUE = 0;
    public static final String DUE_DATE_MESSAGE = "DUE DATE";
    public static final String  BEFORE_DUE_MESSAGE = "BEFORE DUE";
    public static final String RECEIPT_RENT_MESSAGE = "RENT RECEIPT";
    public static final String ELECTRIC_BILL_MESSAGE = "ELECTRIC BILL";
    public static final String WELCOME_MESSAGE = "WELCOME";
    public static final String RENT_ALERT_MESSAGE = "RENT ALERT"; 
    
    public static final String BILL_RENT = "property";
    public static final String BILL_ELECTRIC = "electric";
    
    public static final String SMS_FAILED = "failed";
    public static final String SMS_SUCCESS = "success";
    
    public static enum BillingType {
      PROPERTY("property"),
      WATER("water"),
      ELECTRIC("electric");
      
      private String value;
      BillingType(String value) {
        this.value = value;
      }
      
      public String getValue() {
        return this.value;
      }
    }
}
