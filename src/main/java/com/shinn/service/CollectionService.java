package com.shinn.service;

import com.shinn.service.model.Transaction;
import com.shinn.ui.model.BillingForm;
import com.shinn.ui.model.ElectricCollectionForm;
import com.shinn.ui.model.Response;

public interface CollectionService {

  Response<ElectricCollectionForm> saveElectricCollection(ElectricCollectionForm electricCollectionForm) throws Exception;
  Response<BillingForm> createCollection(BillingForm form);
  String createPdf(BillingForm form);

  
}
