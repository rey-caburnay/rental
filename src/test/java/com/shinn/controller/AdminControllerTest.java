package com.shinn.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import static org.mockito.Mockito.atLeastOnce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import com.shinn.configuration.WebConfiguration;
import com.shinn.configuration.test.TestContext;
import com.shinn.model.User;
import com.shinn.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestContext.class, WebConfiguration.class})
@WebAppConfiguration
public class AdminControllerTest {

    private MockMvc mockMvc;
    
    @Autowired
    UserService userServiceMock;
    
    @Autowired
    org.springframework.web.context.WebApplicationContext webApplicationContext;
    

    @Before
    public void setup() {
        Mockito.reset(userServiceMock);
      
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }
    @Mock
    MessageSource message;

//    @InjectMocks
//    AdminController adminController;



    @Test
    public void listEmployees() {
//        ResponseEntity<List<User>> response = adminController.listAllUsers();
//        System.out.println(response.getBody().size());
//        Assert.assertTrue(response.getBody().size() > 0);
//        org.junit.Assert.assertTrue(adminController.listAllUsers());
//        Assert.assertNotNull(model.get("employee"));
//        Assert.assertFalse((Boolean) model.get("edit"));
//        Assert.assertEquals(((Employee) model.get("employee")).getId(), 0);
    }
    @Test
    public void testHome() throws Exception {

        mockMvc.perform(get("/user/"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("id", 1));
//            .andExpect(forwardedUrl("WEB-INF/pages/index.jsp"));

    }

    @Test
    public void testPersistenceStatus() throws Exception {

//        mockMvc.perform(get("/roundtrip"))
//            .andExpect(status().isOk())
//            .andExpect(forwardedUrl("WEB-INF/pages/roundtrip.jsp"))
//            .andExpect(model().attributeExists("RoundTrip"));

    }

}
