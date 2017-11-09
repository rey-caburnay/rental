package com.shinn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shinn.dao.factory.ResultStatus;
import com.shinn.service.ReportService;
import com.shinn.service.model.ElectricBill;
import com.shinn.service.model.Renter;
import com.shinn.service.model.Room;
import com.shinn.ui.model.Response;

@RestController
@RequestMapping(value = "/report")
public class ReportController {
  private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

  @Autowired
  ReportService reportService;


  /**
   * get the tenants base on room id
   * 
   * @return
   */
  @RequestMapping(value = "/tenant", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<Renter>> getRentersReport() {
    Response<Renter> resp = reportService.getRentersReport(null);
    logger.debug(resp.toString());
    if (resp.getResponseStatus().equals(ResultStatus.RESULT_OK)) {
      return new ResponseEntity<Response<Renter>>(resp, HttpStatus.OK);
    }
    return new ResponseEntity<Response<Renter>>(HttpStatus.NO_CONTENT);
  }
  
  @RequestMapping(value = "/tenant/{aptId}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<Renter>> getRentersReport(@PathVariable Integer aptId) {
    Response<Renter> resp = reportService.getRentersReport(aptId);
    logger.debug(resp.toString());
    if (resp.getResponseStatus().equals(ResultStatus.RESULT_OK)) {
      return new ResponseEntity<Response<Renter>>(resp, HttpStatus.OK);
    }
    return new ResponseEntity<Response<Renter>>(HttpStatus.NO_CONTENT);
  }
  
  @RequestMapping(value = "/rooms", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<Room>> getRoomsReport() {
    Response<Room> resp = reportService.getRoomsReport(null);
    logger.debug(   resp.toString());
    if (resp.getResponseStatus().equals(ResultStatus.RESULT_OK)) {
      return new ResponseEntity<Response<Room>>(resp, HttpStatus.OK);
    }
    return new ResponseEntity<Response<Room>>(HttpStatus.NO_CONTENT);
  }
  
  @RequestMapping(value = "/rooms/{aptId}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<Room>> getRoomsReport(@PathVariable Integer aptId) {
    Response<Room> resp = reportService.getRoomsReport(aptId);
    logger.debug(resp.toString());
    if (resp.getResponseStatus().equals(ResultStatus.RESULT_OK)) {
      return new ResponseEntity<Response<Room>>(resp, HttpStatus.OK);
    }
    return new ResponseEntity<Response<Room>>(HttpStatus.NO_CONTENT);
  }
  
  @RequestMapping(value = "/electric/{aptId}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<ElectricBill>> getElectricReport(@PathVariable String aptId) {
    logger.debug("aptId:" + aptId);
    Integer id = null;
    try {
      id = Integer.parseInt(aptId);
      
    } catch(Exception e) {
      id = null;
    }
    Response<ElectricBill> resp = reportService.getElectricReport(id);
    if (resp.getResponseStatus().equals(ResultStatus.RESULT_OK)) {
      return new ResponseEntity<Response<ElectricBill>>(resp, HttpStatus.OK);
    }
    return new ResponseEntity<Response<ElectricBill>>(HttpStatus.NO_CONTENT);
  }


}
