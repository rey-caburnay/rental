package com.shinn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinn.dao.factory.ResultStatus;
import com.shinn.dao.repos.RentalDao;
import com.shinn.model.Response;
import com.shinn.model.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    RentalDao rentalDao;
    
    
    /**
     * 
     */
    public Response<Transaction> createTx(Transaction tx)  {
        Response<Transaction> resp = new Response<Transaction>();
        try {
            tx.setId(rentalDao.getCurrentKey(Transaction.TABLE_NAME));
            rentalDao.saveUpdate(tx);
            resp.setResponseStatus(ResultStatus.RESULT_OK);
            resp.setModel(tx);
        } catch(Exception e) {
            resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
            resp.setErrorMsg(e.getMessage());
        }
        return resp;
    }

}
