package com.shinn.service;

import com.shinn.model.Order;

public interface SampleService {

	public String getOrderDescription();
	public String getOrderStringCode();
	public Order getOrder(int id);
	public Order createOrder(Order order);
	
	
}
