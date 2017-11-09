package com.shinn.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.shinn.service.ReportService;
import com.shinn.service.model.Room;
import com.shinn.ui.model.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestContext.class, loader = AnnotationConfigContextLoader.class)
public class ReportServiceTest {

  @Autowired
  ReportService reportService;
  
  @Test
  public void getRoomsReport() {
   Response<Room> rooms = reportService.getRoomsReport(null);
   System.out.println(rooms.getResult().size());
    
  }
}
