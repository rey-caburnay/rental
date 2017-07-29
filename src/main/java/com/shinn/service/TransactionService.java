package com.shinn.service;

import com.shinn.service.model.Collection;
import com.shinn.service.model.ElectricBill;
import com.shinn.service.model.Renter;
import com.shinn.service.model.RenterInfo;
import com.shinn.service.model.Transaction;
import com.shinn.ui.model.CollectionForm;
import com.shinn.ui.model.RegistrationForm;
import com.shinn.ui.model.Response;
/**
 * transaction service is responsbile for
 * all types of transaction manipulations (CREATE,UPDATE,DELETE)
 * @author rbkshinn
 *
 */
public interface TransactionService {
    /**
     * 
     * @param tx
     * @return
     */
    public Response<RegistrationForm> createTx(RegistrationForm tx);
    /**
     * 
     * @param renter
     * @return
     */
    public Response<Renter> registration(Renter renter);
    /**
     * 
     * @param renterId
     * @return
     */
    public Response<Transaction> getTxByRenterId(Integer renterId);
    /**
     * 
     * @param renterId
     * @return
     */
    public Response<RenterInfo> getRentersInfo(Integer renterId);
    /**
     * 
     * @param collection
     * @return
     */
    public Response<CollectionForm> createCollection(CollectionForm collection);


}
