package com.shinn.service.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Room implements Serializable{
	
	private Integer id;
	
	private Integer aptId;
	
	private Integer floor;
	
	private String roomDesc;
	
	private Integer occupied;
	
	private String roomNo;
	
	private String roomName;
	
	private String roomType;
	
	private Integer size;
	
	private String telNo;
	
	private Double rate;
	
	private String status;
	
	private Date lastPayment;
	
	private Integer unpaidMonths;

}
