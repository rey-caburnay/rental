package com.shinn.service.model;

import java.io.Serializable;
import java.util.Date;

public class Collection implements Serializable {
    
    private Integer id;
    private Integer txId;
    private Integer renterId;
    private Integer roomId;
    private Double amountPaid;
    private Double balance;
    private Double deposit;
    private Date txDate;
    private Integer userId;
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
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Collection [id=" + id + ", txId=" + txId + ", renterId=" + renterId + ", roomId=" + roomId
                + ", amountPaid=" + amountPaid + ", balance=" + balance + ", deposit=" + deposit + ", txDate=" + txDate
                + ", userId=" + userId + "]";
    }

}
