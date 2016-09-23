package com.shinn.dao.repos;

import java.util.List;

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
        // TODO Auto-generated method stub
        return null;
    }

    public List<Transaction> findAll() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    public void saveUpdate(Transaction model) throws Exception {
        String sqlStment = "save-transaction";
        if (model.getId() != null && model.getId() > 0) {
            sqlStment = "update-transaction";
        } 
        executeSaveUpate(sqlStment,
                model.getId(),
                model.getAptId(),
                model.getRoomId(),
                model.getDueDate(),
                model.getTxDate(),
                model.getStartDate(),
                model.getEndDate(),
                model.getDeposit(),
                model.getRenterId(),
                model.getBalance(),
                model.getTxType(),
                model.getProvider(),
                model.getAmount(),
                model.getStatus(),
                model.getUserId(),
                model.getUpdateDate(),
                model.getUpdtCnt());
    }
    /**
     * get transaction /membership by renter's id
     */
	@Override
	public Transaction getTransactionByRenterId(Integer renterId) {
		return getObject("get-transaction-by-renterid", renterId);
	}

}
