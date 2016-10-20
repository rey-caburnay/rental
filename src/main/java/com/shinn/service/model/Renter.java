package com.shinn.service.model;

import java.io.Serializable;
import java.util.List;

public class Renter implements Serializable {
    
    public static final String TABLE_NAME = "mst_renter";
    
    protected Integer id;
    
    protected String lastName;
    
    protected String firstName;
    
    protected String initial;
    
    protected String address;
    
    protected String idPresented;
    
    protected String mobileNo;
    
    protected String telno;
    
    protected String email;
    
    protected String emergencyContact;
    
    protected String status;
    
    private List<Transaction> transactions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdPresented() {
        return idPresented;
    }

    public void setIdPresented(String idPresented) {
        this.idPresented = idPresented;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Renter [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName
                + ", initial=" + initial + ", address=" + address + ", idPresented=" + idPresented
                + ", mobileNo=" + mobileNo + ", telno=" + telno + ", email=" + email
                + ", emergencyContact=" + emergencyContact + ", status=" + status
                + ", transactions=" + transactions + "]";
    }

}
