package com.shinn.ui.model;

import java.io.Serializable;

/**
 * Registration model for ui 
 * @author rc185213
 *
 */
public class RegistrationForm implements Serializable {
    
    String aptId = null;
    String roomId =  null;
    String renterId = null;
    String renterLastName = null;
    String renterFirstName = null;
    String renterMI = null;
    String mobileno = null;
    String telno = null;
    String idPresented = null;
    String address = null;
    String dueDate = null;
    String txDate = null;
    String startDate = null;
    String endDate = null;
    String deposit = null;
    String balance = null;
    String amount = null;
    String txType = null;
    String provider = null;
    String status = null;
    String userId = null;
    
    public String getRoomId() {
        return roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public String getRenterId() {
        return renterId;
    }
    public void setRenterId(String renterId) {
        this.renterId = renterId;
    }
    public String getRenterLastName() {
        return renterLastName;
    }
    public void setRenterLastName(String renterLastName) {
        this.renterLastName = renterLastName;
    }
    public String getRenterFirstName() {
        return renterFirstName;
    }
    public void setRenterFirstName(String renterFirstName) {
        this.renterFirstName = renterFirstName;
    }
    public String getRenterMI() {
        return renterMI;
    }
    public void setRenterMI(String renterMI) {
        this.renterMI = renterMI;
    }
    public String getDueDate() {
        return dueDate;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    public String getTxDate() {
        return txDate;
    }
    public void setTxDate(String txDate) {
        this.txDate = txDate;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getDeposit() {
        return deposit;
    }
    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }
    public String getBalance() {
        return balance;
    }
    public void setBalance(String balance) {
        this.balance = balance;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getTxType() {
        return txType;
    }
    public void setTxType(String txType) {
        this.txType = txType;
    }
    public String getProvider() {
        return provider;
    }
    public void setProvider(String provider) {
        this.provider = provider;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAptId() {
        return aptId;
    }
    public void setAptId(String aptId) {
        this.aptId = aptId;
    }
    public String getMobileno() {
        return mobileno;
    }
    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }
    public String getTelno() {
        return telno;
    }
    public void setTelno(String telno) {
        this.telno = telno;
    }
    public String getIdPresented() {
        return idPresented;
    }
    public void setIdPresented(String idPresented) {
        this.idPresented = idPresented;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return "Registration [aptId=" + aptId + ", roomId=" + roomId + ", renterId=" + renterId + ", renterLastName="
                + renterLastName + ", renterFirstName=" + renterFirstName + ", renterMI=" + renterMI + ", mobileno="
                + mobileno + ", telno=" + telno + ", idPresented=" + idPresented + ", address=" + address + ", dueDate="
                + dueDate + ", txDate=" + txDate + ", startDate=" + startDate + ", endDate=" + endDate + ", deposit="
                + deposit + ", balance=" + balance + ", amount=" + amount + ", txType=" + txType + ", provider="
                + provider + ", status=" + status + ", userId=" + userId + "]";
    }
   
    
    

}
