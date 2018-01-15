package com.shinn.service.model;

import java.io.Serializable;
import java.util.Date;

import com.shinn.ui.model.Bill;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
public class ElectricBill implements Serializable {

  private Integer id;
  private Integer aptId;
  private Integer roomId;
  private String roomNumber;
  private String roomDesc;
  private String apartmentName;
  private String accountNo;
  private Integer previousReading;
  private Integer currentReading;
  private Integer diffReading;
  private String provider;
  private Double amount;
  private String status;
  private Double overdue;
  private String lastBillNo;
  private String billingNo;
  private String meterNo;
  private Date dueDate;
  private Double payment;
  private Double surcharge;
  private Date readingDate;
  private Double totalAmount;
  private String collectionNo;
  
  /** non db fields **/
  private Date generationDate;
  private ElectricProvider electricProvider;
  private Double grossAmount;
  private Bill bill;
  
 
}
