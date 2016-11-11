package com.shinn.service;

import com.shinn.service.model.Collection;
import com.shinn.service.model.RenterInfo;
import com.shinn.service.model.Transaction;
import com.shinn.ui.model.CollectionForm;
import com.shinn.ui.model.RegistrationForm;
import com.shinn.ui.model.Response;

public interface TransactionService {
    
    public Response<RegistrationForm> createTx(RegistrationForm tx);
    
    public Response<Transaction> getTxByRenterId(Integer renterId);
    
    public Response<RenterInfo> getRentersInfo(Integer renterId);
    
    public Response<CollectionForm> createCollection(CollectionForm collection);

}
