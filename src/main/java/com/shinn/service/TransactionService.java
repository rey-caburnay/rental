package com.shinn.service;

import com.shinn.ui.model.Registration;
import com.shinn.ui.model.Response;

public interface TransactionService {
    
    public Response<Registration> createTx(Registration tx);

}
