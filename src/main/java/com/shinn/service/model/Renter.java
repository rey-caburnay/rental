package com.shinn.service.model;

import java.io.Serializable;

public class Renter implements Serializable {
    
    public static final String TABLE_NAME = "mst_renter";
    
    private Integer id;
    
    private String lastName;
    
    private String firstName;
    
    private String initial;
    
    private String address;
    
    private String idPresented;
    
    private String mobileNo;
    
    private String telno;
    
    private String email;
    
    private String emergencyContact;
    
    private String status;

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

    @Override
    public String toString() {
        return "Renter [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", initial=" + initial
                + ", address=" + address + ", idPresented=" + idPresented + ", mobileNo=" + mobileNo + ", telno="
                + telno + ", email=" + email + ", emergencyContact=" + emergencyContact + ", status=" + status + "]";
    }

   
    

}
