package com.shinn.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    
    private Integer aptId;
    
    private Integer roomId;
    
    private String apartmentName;
    
    private String roomName;
    
    private Double lastPayment;
    
    private Date lastPaymentDate;
    
    private List<Transaction> transactions;

 
}
