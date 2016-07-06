package com.shinn.service;

import com.shinn.model.Response;
import com.shinn.model.Transaction;

public interface TransactionService {
    
    public Response<Transaction> createTx(Transaction tx);

}
