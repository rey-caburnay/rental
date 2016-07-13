package com.shinn.service.model;

import java.io.Serializable;

public class Expense implements Serializable{

	private Integer id;
	
	private Integer aptId;
	
	private String description;
	
	private String expType;
	
	private Double amount;
	
	private String status;

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
	 * @return the aptId
	 */
	public Integer getAptId() {
		return aptId;
	}

	/**
	 * @param aptId the aptId to set
	 */
	public void setAptId(Integer aptId) {
		this.aptId = aptId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the expType
	 */
	public String getExpType() {
		return expType;
	}

	/**
	 * @param expType the expType to set
	 */
	public void setExpType(String expType) {
		this.expType = expType;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Expense [id=" + id + ", aptId=" + aptId + ", description=" + description + ", expType=" + expType
				+ ", amount=" + amount + ", status=" + status + "]";
	}
	
	
}
