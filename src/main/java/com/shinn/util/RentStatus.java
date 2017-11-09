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
    public static String DUE_DATE_MESSAGE = "Please pay your rent for the month of {month} amounting:{amount} to avoid neccessary actions. from-Caburany Apartmentz";
    public static String BEFORE_DUE_MESSAGE = "This is to inform you that your rental fee(s) will due on {duedate} with total amount of {amount}"
            + " Please pay on before due date. from -Caburany Apartmentz";
    public static String RECEIPT_RENT_MESSAGE = "Thank you for paying your rent with the amount of {amount}. Reference # {reference} from Caburnay Apartmentz";
    public static String RECEIPT_GENERIC_PAYMENT_MESSAGE = "Thank you for paying your rent. This will also serve as your receipt. from Caburnay Apartmentz";

    public static String ELECTRIC_BILL_MESSAGE = "Please pay your electric bill for the month of {month} amount: {amount} to avoid disconnection. from Caburnay Apartmentz.";
    
    public static final String BILL_RENT = "property";
    public static final String BILL_ELECTRIC = "electric";
    
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
