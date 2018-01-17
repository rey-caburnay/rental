package com.shinn.service.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Apartment implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 5449566633273416248L;
  private Integer id;
  private String name;
  private String personInCharge;
  private String aptType;
  private String mobileNo;
  private String telNo;
  private String address1;
  private String address2;
  private String status;
  private Date operationStart;
  private Date txDate;
  private String electricAccount;
  private String waterAccount;
  private String electricProvider;
  private String mobileIncharge;
}
