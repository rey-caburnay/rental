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

}
