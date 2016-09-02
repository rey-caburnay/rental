package com.shinn.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import com.shinn.configuration.test.TestContext;
import com.shinn.service.TransactionService;
import com.shinn.service.model.Collection;
import com.shinn.service.model.RenterInfo;
import com.shinn.ui.model.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContext.class, loader = AnnotationConfigContextLoader.class)
public class TransactionServiceTest {
    
    @Autowired
    TransactionService transactionService;
    
    @Test
    public void getRenters() {
//        Assert.assertTrue(adminService.getRenters());
        Response<RenterInfo> resp = transactionService.getRentersInfo(1);
        for(int i = 0; i < resp.getResult().size(); i++) {
            System.out.println(resp.getResult().get(i).toString());
        }
        Assert.assertTrue(resp.getResult().size() > 0);
    }
    
    @Test
    public void createCollection() {
        Collection collection = new Collection();
        collection.setTxId(1);
        collection.setAptId(1);
        collection.setRoomId(1);
        collection.setAmountPaid(2000d);
        collection.setBalance(0d);
        collection.setDeposit(0d);
        collection.setCashReceived(2000d);
        collection.setChange(500d);
        collection.setUserId(1);
        Response<Collection> resp = transactionService.createCollection(collection);
        Assert.assertNotNull(resp.getModel());
    }

}
