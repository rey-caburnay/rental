package com.shinn.service.model;

import java.io.Serializable;
import java.util.Date;

import lombok.ToString;

@ToString
public class RenterInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer txId;
    private Integer aptId;
    private Integer roomId;
    private String paymentType;
    private Date dueDate;
    private Date startDate;
    private Date endDate;
    private Double deposit;
    private Double balance;
    private Double amount;
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private String initial;
    private String email;
    private String phone;
    private String address;
    private String status;
    private String paymentStatus;
    private String remarks;
    
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
     * @return the amountPaid
     */
    public Double getAmount() {
      return amount;
    }
    /**
     * @param amountPaid the amountPaid to set
     */
    public void setAmount(Double amount) {
      this.amount = amount;
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
     * @return the firstName
     */
    public String getFirstName() {
      return firstName;
    }
    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }
    /**
     * @return the lastName
     */
    public String getLastName() {
      return lastName;
    }
    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
      this.lastName = lastName;
    }
    /**
     * @return the initial
     */
    public String getInitial() {
      return initial;
    }
    /**
     * @param initial the initial to set
     */
    public void setInitial(String initial) {
      this.initial = initial;
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
     * @return the phone
     */
    public String getPhone() {
      return phone;
    }
    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
      this.phone = phone;
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
     * @return the paymentStatus
     */
    public String getPaymentStatus() {
      return paymentStatus;
    }
    /**
     * @param paymentStatus the paymentStatus to set
     */
    public void setPaymentStatus(String paymentStatus) {
      this.paymentStatus = paymentStatus;
    }
    /**
     * @return the remarks
     */
    public String getRemarks() {
      return remarks;
    }
    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {
      this.remarks = remarks;
    }
    
}
