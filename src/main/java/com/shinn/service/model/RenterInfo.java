package com.shinn.service.model;

import java.io.Serializable;

public class RenterInfo extends Renter implements Serializable {
	
	
	private Integer aptId;
	
	private Integer roomId;
	
	private String name;
	
	private String aptName;
	
	private String roomName;
	
	private String rentType;
	
	private String paymentType;
	
	private String amount;

	private String electric;
	
	private String water;
}
