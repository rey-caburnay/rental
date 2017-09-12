package com.shinn.service.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ElectricCollection implements Serializable {

  private Integer id;
  private String billingNo;
  private Double amount;
  private String status;
  private Double overdue;
  private Date collectionDate;
  private Date dueDate;
  private Double cashReceived;
  private Double cashChange;
}
