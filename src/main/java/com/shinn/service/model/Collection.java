package com.shinn.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Collection implements Serializable {
    
    private Integer id;
    private Integer txId;
    private Integer renterId;
    private Integer roomId;
    private Integer aptId;
    private Double amountPaid;
    private Double balance;
    private Double deposit;
    private Double cashChange;
    private String payment;
    private Double cashReceived;
    private Date txDate;
    private Integer userId;
    private String status;
    private Double currentRoomRate;
    private Date dueDate;
//    private List<Transaction> transactions;
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
     * @return the cashChange
     */
    public Double getCashChange() {
      return cashChange;
    }
    /**
     * @param cashChange the cashChange to set
     */
    public void setCashChange(Double cashChange) {
      this.cashChange = cashChange;
    }
    /**
     * @return the payment
     */
    public String getPayment() {
      return payment;
    }
    /**
     * @param payment the payment to set
     */
    public void setPayment(String payment) {
      this.payment = payment;
    }
    /**
     * @return the currentRoomRate
     */
    public Double getCurrentRoomRate() {
      return currentRoomRate;
    }
    /**
     * @param currentRoomRate the currentRoomRate to set
     */
    public void setCurrentRoomRate(Double currentRoomRate) {
      this.currentRoomRate = currentRoomRate;
    }
    /**
     * @return the dueDate
     */
    public Date getDueDate() {
      return dueDate;
    }
    /**
     * payment for the duedate
     * @param dueDate the dueDate to set
     */
    public void setDueDate(Date dueDate) {
      this.dueDate = dueDate;
    }
    
}
