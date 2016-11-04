package com.shinn.service.model;

import java.io.Serializable;

import lombok.ToString;
@ToString
public class Expense implements Serializable{

	private Integer id;
	
	private Integer aptId;
	
	private Integer roomId;
	
	private String description;
	
	private String expType;
	
	private Double amount;
	   
    private Integer currentReading;
    
    private Integer previousReading;
    
    private String provider;
        
    private Double previousAmount;
    	
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
     * @return the currentReading
     */
    public Integer getCurrentReading() {
        return currentReading;
    }

    /**
     * @param currentReading the currentReading to set
     */
    public void setCurrentReading(Integer currentReading) {
        this.currentReading = currentReading;
    }

    /**
     * @return the previousReading
     */
    public Integer getPreviousReading() {
        return previousReading;
    }

    /**
     * @param previousReading the previousReading to set
     */
    public void setPreviousReading(Integer previousReading) {
        this.previousReading = previousReading;
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
     * @return the previousAmount
     */
    public Double getPreviousAmount() {
        return previousAmount;
    }

    /**
     * @param previousAmount the previousAmount to set
     */
    public void setPreviousAmount(Double previousAmount) {
        this.previousAmount = previousAmount;
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
}
