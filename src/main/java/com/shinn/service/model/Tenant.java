package com.shinn.service.model;

import java.io.Serializable;
import java.util.List;

import lombok.ToString;

@ToString
public class Tenant implements Serializable {
	
	private int aptId;
	private int roomId;
	private List<RenterInfo> tenants;
	/**
	 * @return the aptId
	 */
	public int getAptId() {
		return aptId;
	}
	/**
	 * @param aptId the aptId to set
	 */
	public void setAptId(int aptId) {
		this.aptId = aptId;
	}
	/**
	 * @return the roomId
	 */
	public int getRoomId() {
		return roomId;
	}
	/**
	 * @param roomId the roomId to set
	 */
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	/**
	 * @return the tenants
	 */
	public List<RenterInfo> getTenants() {
		return tenants;
	}
	/**
	 * @param tenants the tenants to set
	 */
	public void setTenants(List<RenterInfo> tenants) {
		this.tenants = tenants;
	}
	
}
