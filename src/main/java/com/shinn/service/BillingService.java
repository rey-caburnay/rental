package com.shinn.service;

import com.shinn.service.model.Billing;
import com.shinn.service.model.ElectricBill;
import com.shinn.service.model.Renter;
import com.shinn.service.model.Transaction;
import com.shinn.ui.model.BillingForm;
import com.shinn.ui.model.Response;

public interface BillingService {
  
  public Response<ElectricBill> getElectricBills(Integer aptId);
  public String createPdf(BillingForm billingForm);
  public Response<BillingForm> generateBillings(BillingForm billingForm) throws Exception;
  public Response<Billing> getBilling(Integer aptId, Integer roomId, String billType);
  public Response<Transaction> getRoomBilling(Integer aptId, Integer roomId);
  public Response<Transaction> getRoomBillingForCollection(Integer aptId, Integer roomId);
  public byte[] getPdfContent(String pdfLocation) throws Exception;
  public Response<Transaction> createNewBilling(Renter renter);
  public Response<ElectricBill> getElectricForCollection(Integer aptId, Integer roomId);
  
  public Response<Transaction> sendBillingAlert();
  public Response<ElectricBill> sendElectricBillingAlert();

}
