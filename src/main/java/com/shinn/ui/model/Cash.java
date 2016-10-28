package com.shinn.ui.model;

/**
 * cash
 * @author rc185213
 *
 */
public class Cash {
    
    private String cashRecieved;
    private String amountPaid;
    private String cashChange;
    private String balance;
    private String deposit;
    /**
     * @return the cashRecieved
     */
    public String getCashRecieved() {
        return cashRecieved;
    }
    /**
     * @param cashRecieved the cashRecieved to set
     */
    public void setCashRecieved(String cashRecieved) {
        this.cashRecieved = cashRecieved;
    }
    /**
     * @return the amountPaid
     */
    public String getAmountPaid() {
        return amountPaid;
    }
    /**
     * @param amountPaid the amountPaid to set
     */
    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }
    /**
     * @return the cashChange
     */
    public String getCashChange() {
        return cashChange;
    }
    /**
     * @param cashChange the cashChange to set
     */
    public void setCashChange(String cashChange) {
        this.cashChange = cashChange;
    }
    /**
     * @return the balance
     */
    public String getBalance() {
        return balance;
    }
    /**
     * @param balance the balance to set
     */
    public void setBalance(String balance) {
        this.balance = balance;
    }
    /**
     * @return the deposit
     */
    public String getDeposit() {
        return deposit;
    }
    /**
     * @param deposit the deposit to set
     */
    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Payment [cashRecieved=" + cashRecieved + ", amountPaid=" + amountPaid
                + ", cashChange=" + cashChange + ", balance=" + balance + ", deposit=" + deposit
                + "]";
    }
}
