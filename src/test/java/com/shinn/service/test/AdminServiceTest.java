package com.shinn.service.test;

import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebDelegatingSmartContextLoader;
import org.springframework.web.context.WebApplicationContext;
import com.shinn.configuration.SpringWebConfig;
import com.shinn.configuration.test.TestContext;
import com.shinn.service.AdminService;
import com.shinn.service.UserService;
import com.shinn.service.model.RenterInfo;
import com.shinn.ui.model.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContext.class, loader = AnnotationConfigContextLoader.class)
public class AdminServiceTest {
    @Autowired
    AdminService adminService;

    @Before
    public void setup() {
    }

    @Test
    public void getRenters() {
//        Assert.assertTrue(adminService.getRenters());
        Response<RenterInfo> resp = adminService.getRenters();
        for(int i = 0; i < resp.getResult().size(); i++) {
            System.out.println(resp.getResult().get(i).toString());
        }
        Assert.assertTrue(resp.getResult().size() > 0);
    }
}
