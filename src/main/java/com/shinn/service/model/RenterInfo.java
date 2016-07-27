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
     * @return the aptName
     */
    public String getAptName() {
        return aptName;
    }

    /**
     * @param aptName the aptName to set
     */
    public void setAptName(String aptName) {
        this.aptName = aptName;
    }

    /**
     * @return the roomName
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * @param roomName the roomName to set
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * @return the rentType
     */
    public String getRentType() {
        return rentType;
    }

    /**
     * @param rentType the rentType to set
     */
    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    /**
     * @return the paymentType
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * @param paymentType the paymentType to set
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the electric
     */
    public String getElectric() {
        return electric;
    }

    /**
     * @param electric the electric to set
     */
    public void setElectric(String electric) {
        this.electric = electric;
    }

    /**
     * @return the water
     */
    public String getWater() {
        return water;
    }

    /**
     * @param water the water to set
     */
    public void setWater(String water) {
        this.water = water;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RenterInfo [aptId=" + aptId + ", roomId=" + roomId + ", name=" + name + ", aptName=" + aptName
                + ", roomName=" + roomName + ", rentType=" + rentType + ", paymentType=" + paymentType + ", amount="
                + amount + ", electric=" + electric + ", water=" + water + "]";
    }
	
	
}
