package com.shinn.service;

import java.util.List;

import com.shinn.service.model.ElectricBill;
import com.shinn.service.model.RenterInfo;
import com.shinn.service.model.Sms;
import com.shinn.ui.model.BillingForm;
import com.shinn.ui.model.Response;

public interface SmsService {

  public Response<Sms> sendMessage(String message, String mobile);
  
  /**
   * 
   * @param message
   * @return
   */
  public Response<Sms> sendBillingMessages(List<RenterInfo> tenants);
  public Response<Sms> sendBillingMessages(BillingForm billingForm);
  public Response<Sms> sendElectricBillingMessage(String messageType, ElectricBill bill);

  public Response<Sms> sendElectricBillingMessage(BillingForm billing);
}
