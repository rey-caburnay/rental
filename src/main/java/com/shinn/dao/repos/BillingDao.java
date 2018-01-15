package com.shinn.dao.repos;

import java.util.Date;
import java.util.List;

import com.shinn.dao.factory.GenericDao;
import com.shinn.service.model.Billing;
import com.shinn.service.model.Transaction;

public interface BillingDao extends GenericDao<Billing> {
  
  /**
   * retrieve a billing based on generated date;
   * ensure that only 1 billing will be created per month
   * @param generatedDate
   * @return
   */
  public Billing getBillingThisMonth(Date generatedDate, String billType);
  
  public Billing getLatestBilling(Integer aptId, Integer roomId, String billType);
  
  public Billing getLastHoursGenerated (Integer aptId, Integer roomId, String billType);
  
  public Billing getByBillingNo(String billingNo);
  
  public int getUnpaidBills(Integer aptId, Integer roomId);
}
