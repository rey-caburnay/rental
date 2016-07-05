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
import com.shinn.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = TestContext.class, loader = AnnotationConfigContextLoader.class)
public class UserServiceTest {
    
    @Autowired UserService userService;

    
    @Before
    public void setup() {
        
    }
    
    @Test
    public void getUser() {
        Assert.assertTrue(userService.findAllUsers().size()>0);
    }

}
