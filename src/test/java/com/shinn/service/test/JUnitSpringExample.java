package com.shinn.service.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.sql.Connection;

import javax.sql.DataSource;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.BaseMatcher.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.shinn.configuration.DatabaseConfiguration;
import com.shinn.configuration.WebConfiguration;
import com.shinn.configuration.test.AppConfigTest;
import com.shinn.configuration.test.DbTestConfiguration;
import com.shinn.dao.users.UserDao;
import com.shinn.model.Order;
import com.shinn.service.SampleService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DatabaseConfiguration.class,loader=AnnotationConfigContextLoader.class)
public class JUnitSpringExample {

	
	@Autowired UserDao userDao;
	
	@Autowired Connection conn;
	
	
	@BeforeClass
	public static void setUp() {
		System.out.println("-----> SETUP <-----");
	}
	@Test
	public void testDatasource() {
	    assertNotNull(conn);
	}
	
//	@Test
//	public void testSampleServiceGetAccountDescription() {
//		//	Check if the return description has a 'Description:' string.
//		assertTrue(userDao.getUsernames().size() > 0);
//	}
	
	@Test
	public void testSampleServiceGetAccountCode() {
		//	Check if the return description has a 'Code:' string.
		assertTrue(userDao.getUser(1).getId() == 1);
	}
	
//	@Test
//	public void testSampleServiceCreateNewOrder() {
//		Order newOrder = new Order();
//		newOrder.setSecurityCode("XYZ");
//		newOrder.setDescription("Description");
//		if(newOrder != null) {
//			assertThat(sampleService.createOrder(newOrder), instanceOf(Order.class));
//			assertNotNull("Security isn't null", newOrder.getSecurityCode());
//			assertNotNull("Description isn't not null", newOrder.getDescription());
//		}
//		
//		assertNotNull("New Order is not null", newOrder);
//		
//	}
	
//	@Test
//	public void testSampleServiceGetOrder() {
//		
//		Order existingOrder = sampleService.getOrder(0);
//
//		if(existingOrder != null) {
//			assertThat(sampleService.getOrder(0), instanceOf(Order.class));
//			assertNotNull("Security isn't null", existingOrder.getSecurityCode());
//			assertNotNull("Description isn't null", existingOrder.getDescription());
//		}
//		
//		assertNotNull("Object is not null", existingOrder);
//	}
	
	@AfterClass
	public static void afterTest() {
		System.out.println("-----> DESTROY <-----");
	}
}
