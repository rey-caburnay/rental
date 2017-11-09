package com.shinn.ui.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.shinn.service.model.ElectricBill;
import com.shinn.service.model.ElectricProvider;
import com.shinn.service.model.Transaction;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class BillingForm implements Serializable {
  
  private String userId;
  private String aptId;
  private Double totalAmount;
  private Double totalOverdue;
  private List<ElectricBill> rooms;
  private List<Transaction> bills;
  private Date readingDate;
  private String accountNumber;
  private ElectricProvider electricProvider;
  private String billingNo;
  private byte[] pdf;
  private String billingType;
}
