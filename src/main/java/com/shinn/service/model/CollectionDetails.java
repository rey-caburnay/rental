package com.shinn.service.model;

import java.io.Serializable;
import java.util.Date;

import lombok.ToString;

@ToString
public class CollectionDetails implements Serializable{
    private Integer id;
    private Integer collectionId;
    private Integer aptId;
    private Integer roomId;
    private Integer txId;
    private Double amountPaid;
    private Double balance;
    private Double deposit;
    private Date txDate;
    private String status;
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
     * @return the collectionId
     */
    public Integer getCollectionId() {
        return collectionId;
    }
    /**
     * @param collectionId the collectionId to set
     */
    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }
    /**
     * @return the aptId
     */
    public Integer getAptId() {
        return aptId;
    }
    /**
     * @param aptId the aptId to set
     */
    public void setAptId(Integer aptId) {
        this.aptId = aptId;
    }
    /**
     * @return the roomId
     */
    public Integer getRoomId() {
        return roomId;
    }
    /**
     * @param roomId the roomId to set
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
    /**
     * @return the txId
     */
    public Integer getTxId() {
        return txId;
    }
    /**
     * @param txId the txId to set
     */
    public void setTxId(Integer txId) {
        this.txId = txId;
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
}
