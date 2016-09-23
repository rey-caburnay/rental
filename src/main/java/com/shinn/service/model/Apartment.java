package com.shinn.service.model;

import java.io.Serializable;
import java.util.Date;

public class Apartment implements Serializable {
	
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

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the personInCharge
	 */
	public String getPersonInCharge() {
		return personInCharge;
	}

	/**
	 * @param personInCharge the personInCharge to set
	 */
	public void setPersonInCharge(String personInCharge) {
		this.personInCharge = personInCharge;
	}

	/**
	 * @return the aptType
	 */
	public String getAptType() {
		return aptType;
	}

	/**
	 * @param aptType the aptType to set
	 */
	public void setAptType(String aptType) {
		this.aptType = aptType;
	}

	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * @return the telNo
	 */
	public String getTelNo() {
		return telNo;
	}

	/**
	 * @param telNo the telNo to set
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

    public Date getOperationStart() {
        return operationStart;
    }

    public void setOperationStart(Date operationStart) {
        this.operationStart = operationStart;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

	
	

}
