package com.shinn.service;

import com.shinn.ui.model.RegistrationForm;
import com.shinn.ui.model.Response;

public interface TransactionService {
    
    public Response<RegistrationForm> createTx(RegistrationForm tx);

}
