package com.shinn.service;

import com.shinn.service.model.ElectricBill;
import com.shinn.ui.model.BillingForm;
import com.shinn.ui.model.Response;

public interface BillingService {
  
  public Response<ElectricBill> getElectricBills(Integer aptId);
  public String createPdf(BillingForm billingForm);
  public Response<BillingForm> generateBillings(BillingForm billingForm) throws Exception;

}