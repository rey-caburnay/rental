package com.shinn.dao.repos;

import com.shinn.dao.factory.GenericDao;
import com.shinn.service.model.Transaction;

public interface RentalDao extends GenericDao<Transaction>{
	
	public Transaction getTransactionByRenterId(Integer renterId);

}
