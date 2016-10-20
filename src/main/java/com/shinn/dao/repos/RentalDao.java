package com.shinn.dao.repos;

import java.util.List;

import com.shinn.dao.factory.GenericDao;
import com.shinn.service.model.Transaction;

public interface RentalDao extends GenericDao<Transaction>{
	
    /**
     * 
     * @param renterId
     * @return
     */
	public List<Transaction> getTransactionByRenterId(Integer renterId);
	/**
	 * 
	 * @param renterId
	 * @param status
	 * @return
	 */
	public List<Transaction> getTransactionByRenterId(Integer renterId, String status);

}
