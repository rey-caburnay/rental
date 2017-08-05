package com.shinn.dao.repos;

import java.util.Date;

import com.shinn.dao.factory.GenericDao;
import com.shinn.service.model.Billing;

public interface BillingDao extends GenericDao<Billing> {
  
  /**
   * retrieve a billing based on generated date;
   * ensure that only 1 billing will be created per month
   * @param generatedDate
   * @return
   */
  public Billing getBillingThisMonth(Date generatedDate);
  
  public Billing getLatestBilling(Integer aptId, Integer roomId);

}
