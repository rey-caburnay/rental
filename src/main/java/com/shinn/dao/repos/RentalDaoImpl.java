package com.shinn.dao.repos;

import java.util.List;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.model.Transaction;

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

}
