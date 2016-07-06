package com.shinn.controller.test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.web.WebDelegatingSmartContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.shinn.configuration.SpringWebConfig;
import com.shinn.model.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = WebDelegatingSmartContextLoader.class, classes = {SpringWebConfig.class})
@WebAppConfiguration
public class AjaxControllerTest {
    @Resource
    public WebApplicationContext webApplicationContext;
 
    private MockMvc mockMvc;
    
    
 
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }
    
    @Test
    public void testUsers() throws Exception {
        mockMvc.perform(get("/user/"))
        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk());
//                        .andExpect(model().attribute("user", ""))
//                        .andExpect(view().name("peopleList"));

        
    }

    /**
     * @return the webApplicationContext
     */
    public WebApplicationContext getWebApplicationContext() {
        return webApplicationContext;
    }

    /**
     * @param webApplicationContext the webApplicationContext to set
     */
    public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    /**
     * @return the mockMvc
     */
    public MockMvc getMockMvc() {
        return mockMvc;
    }

    /**
     * @param mockMvc the mockMvc to set
     */
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
    
    
    

}
