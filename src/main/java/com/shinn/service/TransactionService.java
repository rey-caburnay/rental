package com.shinn.service;

import com.shinn.service.model.Transaction;
import com.shinn.ui.model.RegistrationForm;
import com.shinn.ui.model.Response;

public interface TransactionService {
    
    public Response<RegistrationForm> createTx(RegistrationForm tx);
    
    public Response<Transaction> getTxByRenterId(Integer renterId);

}
