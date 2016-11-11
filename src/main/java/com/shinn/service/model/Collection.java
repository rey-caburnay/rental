package com.shinn.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Collection implements Serializable {
    
    private Integer id;
    private Integer renterId;
    private Double amountPaid;
    private Double balance;
    private Double deposit;
    private Double change;
    private Double cashReceived;
    private Date txDate;
    private Integer userId;
    private String status;
    private List<Transaction> transactions;
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @return the renterId
     */
    public Integer getRenterId() {
        return renterId;
    }
    /**
     * @param renterId the renterId to set
     */
    public void setRenterId(Integer renterId) {
        this.renterId = renterId;
    }
    /**
     * @return the amountPaid
     */
    public Double getAmountPaid() {
        return amountPaid;
    }
    /**
     * @param amountPaid the amountPaid to set
     */
    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }
    /**
     * @return the balance
     */
    public Double getBalance() {
        return balance;
    }
    /**
     * @param balance the balance to set
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    /**
     * @return the deposit
     */
    public Double getDeposit() {
        return deposit;
    }
    /**
     * @param deposit the deposit to set
     */
    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }
    /**
     * @return the change
     */
    public Double getChange() {
        return change;
    }
    /**
     * @param change the change to set
     */
    public void setChange(Double change) {
        this.change = change;
    }
    /**
     * @return the cashReceived
     */
    public Double getCashReceived() {
        return cashReceived;
    }
    /**
     * @param cashReceived the cashReceived to set
     */
    public void setCashReceived(Double cashReceived) {
        this.cashReceived = cashReceived;
    }
    /**
     * @return the txDate
     */
    public Date getTxDate() {
        return txDate;
    }
    /**
     * @param txDate the txDate to set
     */
    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }
    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @return the transactions
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }
    /**
     * @param transactions the transactions to set
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
