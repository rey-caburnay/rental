package com.shinn.controller.test;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.web.WebDelegatingSmartContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.shinn.configuration.SpringWebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = WebDelegatingSmartContextLoader.class, classes = {SpringWebConfig.class})
@WebAppConfiguration
public abstract class AbstractContrllerTest {

    @Resource
    private WebApplicationContext webApplicationContext;
 
    private MockMvc mockMvc;
    
    
 
    @Before
    public void setUp() {
        setMockMvc(MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build());
    }
    
    @After 
    public void tearDown() {
        
    }

    public MockMvc getMockMvc() {
        return mockMvc;
    }

    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
    
}
