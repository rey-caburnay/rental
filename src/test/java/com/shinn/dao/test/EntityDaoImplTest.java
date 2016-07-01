package com.shinn.dao.test;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import com.shinn.configuration.WebConfiguration;
import com.shinn.configuration.test.DbTestConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DbTestConfiguration.class,loader=AnnotationConfigContextLoader.class)
public abstract class EntityDaoImplTest {

	@Autowired
	DataSource dataSource;
	
//	@Autowired Connection conn;

	   @BeforeClass
	    public static void setUp() {
	        System.out.println("-----> SETUP <-----");
	    }
	    @Test
	    public void testDatasource() {
	        assertNotNull(dataSource);
	    }
	    
//	    @Test
//	    public void testConnection() {
//	        assertNotNull(dataSource);
//	    }
	

}