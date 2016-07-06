package com.shinn.model;

public class Electric {

	private Integer id;
	
	private Integer aptId;
	
	private Integer roomId;
	
	private String description;
	
	private Integer curReading;
	
	private Integer prevReading;
	
	private String provider;
	
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
	 * @return the roomId
	 */
	public Integer getRoomId() {
		return roomId;
	}

	/**
	 * @param roomId the roomId to set
	 */
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
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
	 * @return the curReading
	 */
	public Integer getCurReading() {
		return curReading;
	}

	/**
	 * @param curReading the curReading to set
	 */
	public void setCurReading(Integer curReading) {
		this.curReading = curReading;
	}

	/**
	 * @return the prevReading
	 */
	public Integer getPrevReading() {
		return prevReading;
	}

	/**
	 * @param prevReading the prevReading to set
	 */
	public void setPrevReading(Integer prevReading) {
		this.prevReading = prevReading;
	}

	/**
	 * @return the provider
	 */
	public String getProvider() {
		return provider;
	}

	/**
	 * @param provider the provider to set
	 */
	public void setProvider(String provider) {
		this.provider = provider;
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
		return "Electric [id=" + id + ", aptId=" + aptId + ", roomId=" + roomId + ", description=" + description
				+ ", curReading=" + curReading + ", prevReading=" + prevReading + ", provider=" + provider + ", amount="
				+ amount + ", status=" + status + "]";
	}
	
	
}
