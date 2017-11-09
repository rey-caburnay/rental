package com.shinn.service.model;

import java.io.Serializable;
import java.util.Date;

import com.shinn.util.DateUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@ToString
@Getter
@Setter
public class Transaction implements Serializable{
    public static final String TABLE_NAME = "tx_rental";
	
	private Integer id;
	private Integer aptId;
	private String aptName;
	private Integer roomId;
	private Integer renterId;
	private Date dueDate;
	private Date txDate;
	private Date startDate;
	private Date endDate;
	private Double deposit;
	private Double balance;
	private Double amount;
	private String txType;
	private String provider;
	private String status;
	private String paymentStatus;
	private Integer userId;
	private Integer updtCnt;
	private Date updatedDate;
	private Room room;
	private Double amountPaid;
	private boolean isFullPaid;
	private Expense electric;
	private Expense water;
	private Integer unpaidBill;
	private Double amountPayable;
	private String billingNo;
	private Renter renter;
	
}
