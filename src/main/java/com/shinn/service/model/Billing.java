package com.shinn.service.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Billing implements Serializable {
  
  private Integer id;
  private Integer aptId;
  private Integer roomId;
  private Integer currentReading;
  private Integer previousReading;
  private Integer diffReading;
  private Double amount;
  private Double overdue;
  private Date readingDate;
  private Date dueDate;
  private Date generationDate;
  private String billType;
  private String billingNo;
  private String provider;

}
