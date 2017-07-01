package com.shinn.dao.repos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.Transaction;


@Repository
public class RentalDaoImpl extends AbstractDaoImpl<Transaction> implements RentalDao{

    public RentalDaoImpl() throws Exception {
        super();
       setClazz(Transaction.class);
    }

    public Transaction getById(Integer id) throws Exception {
    	  return getObject("get-rental", id);
    }

    public List<Transaction> findAll() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    public int saveUpdate(Transaction model) throws Exception {
        String sqlStment = "save-transaction";
        if (model.getId() != null && model.getId() > 0) {
            sqlStment = "update-transaction";
        } 
        return executeSaveUpate(sqlStment, model);
                
    }
    /**
     * get transaction /membership by renter's id
     */
	@Override
	public List<Transaction> getTransactionByRenterId(Integer renterId) {
		return getListResult("get-transaction-by-renterid", renterId);
	}

    @Override
    public List<Transaction> getTransactionByRenterId(Integer renterId, String status) {
        return getListResult("get-transaction-by-renterid-status", renterId, status);
    }

}
