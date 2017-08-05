package com.shinn.dao.repos;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.service.model.Billing;

@Repository
public class BillingDaoImpl extends AbstractDaoImpl<Billing> implements BillingDao {

  public BillingDaoImpl() throws Exception {
    super();
    setClazz(Billing.class);
  }

  @Override
  public Billing getById(Integer id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Billing> findAll() throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int saveUpdate(Billing model) throws Exception {
   return executeSaveUpate("save-billing", model);
  }

  @Override
  public Billing getBillingThisMonth(Date generatedDate) {
    return getObject("billing-findby-generatedDate", generatedDate);
  }

  @Override
  public Billing getLatestBilling(Integer aptId, Integer roomId) {
    return getObject("billing-finby-latest");
  }
  
}
