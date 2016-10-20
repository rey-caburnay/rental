package com.shinn.service.model;

import java.io.Serializable;
import java.util.Date;

public class RenterInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer txId;
    private Integer aptId;
    private Integer roomId;
    private String aptName;
    private String roomName;
    private String rentType;
    private String paymentType;
    private String electric;
    private String water;
    private Date dueDate;
    private Date startDate;
    private Date endDate;
    private Double deposit;
    private Double balance;
    private Double amount;
    private Double roomRate;
    private String roomDesc;
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

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
     * @return the aptName
     */
    public String getAptName() {
        return aptName;
    }

    /**
     * @param aptName the aptName to set
     */
    public void setAptName(String aptName) {
        this.aptName = aptName;
    }

    /**
     * @return the roomName
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * @param roomName the roomName to set
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * @return the rentType
     */
    public String getRentType() {
        return rentType;
    }

    /**
     * @param rentType the rentType to set
     */
    public void setRentType(String rentType) {
        this.rentType = rentType;
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
     * @return the electric
     */
    public String getElectric() {
        return electric;
    }

    /**
     * @param electric the electric to set
     */
    public void setElectric(String electric) {
        this.electric = electric;
    }

    /**
     * @return the water
     */
    public String getWater() {
        return water;
    }

    /**
     * @param water the water to set
     */
    public void setWater(String water) {
        this.water = water;
    }

    /**
     * @return the dueDate
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
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
     * @return the roomRate
     */
    public Double getRoomRate() {
        return roomRate;
    }

    /**
     * @param roomRate the roomRate to set
     */
    public void setRoomRate(Double roomRate) {
        this.roomRate = roomRate;
    }

    /**
     * @return the mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * @return the roomDesc
     */
    public String getRoomDesc() {
        return roomDesc;
    }

    /**
     * @param roomDesc the roomDesc to set
     */
    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RenterInfo [id=" + id + ", txId=" + txId + ", aptId=" + aptId + ", roomId=" + roomId
                + ", aptName=" + aptName + ", roomName=" + roomName + ", rentType=" + rentType
                + ", paymentType=" + paymentType + ", electric=" + electric + ", water=" + water
                + ", dueDate=" + dueDate + ", startDate=" + startDate + ", endDate=" + endDate
                + ", deposit=" + deposit + ", balance=" + balance + ", amount=" + amount
                + ", roomRate=" + roomRate + "]";
    }

}
