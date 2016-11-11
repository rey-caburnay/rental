package com.shinn.ui.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.shinn.service.model.Transaction;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * model to be shared by ui
 * @author rc185213
 *
 */
@lombok.ToString
public class CollectionForm implements Serializable {
    
    private int id;
    private int renterId;
    private String txDate;
    private String status;
    private String lastname;
    private String firstname;
    private String email;
    private String mobileno;
    private String address;
    private String additionalInfo;
    private String paymentType;
    private String userId;
    private Date collectionDate;
    private List<Transaction> transactions;
    private Cash cash;
    private Credit credit;
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return the renterId
     */
    public int getRenterId() {
        return renterId;
    }
    /**
     * @param renterId the renterId to set
     */
    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }
    /**
     * @return the txDate
     */
    public String getTxDate() {
        return txDate;
    }
    /**
     * @param txDate the txDate to set
     */
    public void setTxDate(String txDate) {
        this.txDate = txDate;
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
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }
    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }
    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the mobileno
     */
    public String getMobileno() {
        return mobileno;
    }
    /**
     * @param mobileno the mobileno to set
     */
    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }
    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }
    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * @return the additionalInfo
     */
    public String getAdditionalInfo() {
        return additionalInfo;
    }
    /**
     * @param additionalInfo the additionalInfo to set
     */
    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
    /**
     * @return the paymentType
     */
    public String getPaymentType() {
        return paymentType;
    }
    /**
     * @param paymentType the paymentType to set
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
    /**
     * @return the cash
     */
    public Cash getCash() {
        return cash;
    }
    /**
     * @param cash the cash to set
     */
    public void setCash(Cash cash) {
        this.cash = cash;
    }
    /**
     * @return the credit
     */
    public Credit getCredit() {
        return credit;
    }
    /**
     * @param credit the credit to set
     */
    public void setCredit(Credit credit) {
        this.credit = credit;
    }
    /**
     * @return the collectionDate
     */
    public Date getCollectionDate() {
        return collectionDate;
    }
    /**
     * @param collectionDate the collectionDate to set
     */
    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }
}
