package com.shinn.service.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.shinn.configuration.test.TestContext;
import com.shinn.service.BillingService;
import com.shinn.service.TransactionService;
import com.shinn.service.model.ElectricBill;
import com.shinn.ui.model.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContext.class, loader = AnnotationConfigContextLoader.class)
public class BillingServiceTest {
  
  @Autowired
  BillingService billingService;
  
  @Test
  public void getBillings() {
    Response<ElectricBill> response = billingService.getElectricBills(1);
    for (ElectricBill bill : response.getResult()) {
      System.out.println(bill.getId());
    }
    assertNotNull(response.getResult());
  }

}
