package com.shinn.ui.model;

public class Credit extends Cash {
    
    private String cardNumber;
    private String ccv;
    private String expirationDate;
    private String month;
    private String year;
    /**
     * @return the cardNumber
     */
    public String getCardNumber() {
        return cardNumber;
    }
    /**
     * @param cardNumber the cardNumber to set
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    /**
     * @return the ccv
     */
    public String getCcv() {
        return ccv;
    }
    /**
     * @param ccv the ccv to set
     */
    public void setCcv(String ccv) {
        this.ccv = ccv;
    }
    /**
     * @return the expirationDate
     */
    public String getExpirationDate() {
        return expirationDate;
    }
    /**
     * @param expirationDate the expirationDate to set
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }
    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }
    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }
    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Credit [cardNumber=" + cardNumber + ", ccv=" + ccv + ", expirationDate="
                + expirationDate + ", month=" + month + ", year=" + year + "]";
    }
}
