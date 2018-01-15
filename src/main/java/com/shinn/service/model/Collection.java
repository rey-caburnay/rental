package com.shinn.service.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    private String billingNo;
    private String collectionNo;
    

}
