package com.shinn.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shinn.dao.factory.ResultStatus;
import com.shinn.service.AdminService;
import com.shinn.service.BillingService;
import com.shinn.service.CollectionService;
import com.shinn.service.SmsService;
import com.shinn.service.model.Billing;
import com.shinn.service.model.ElectricBill;
import com.shinn.service.model.ElectricProvider;
import com.shinn.service.model.Transaction;
import com.shinn.ui.model.BillingForm;
import com.shinn.ui.model.Response;
import com.shinn.util.DateUtil;
import com.shinn.util.RentStatus;

@RestController
@RequestMapping(value = "/bill")
public class BillingController {

  private static final Logger logger = LoggerFactory.getLogger(BillingController.class);

  @Autowired
  AdminService adminService;
  @Autowired
  BillingService billingService;
  @Autowired
  SmsService smsService;
  @Autowired
  CollectionService collectionService;


  /**
   * get the tenants base on room id
   * 
   * @return
   */
  @RequestMapping(value = "/electric/{aptId}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<ElectricBill>> getElectricBills(@PathVariable Integer aptId) {
    logger.info("aptId:" + aptId);
    Response<ElectricBill> resp = billingService.getElectricBills(aptId);
    logger.debug(resp.toString());
    if (resp.getResponseStatus().equals(ResultStatus.RESULT_OK)) {
      return new ResponseEntity<Response<ElectricBill>>(resp, HttpStatus.OK);
    }
    return new ResponseEntity<Response<ElectricBill>>(HttpStatus.NO_CONTENT);
  }

  /**
   * 
   * @param aptId
   * @param roomId
   * @return
   */
  @RequestMapping(value = "/electric/{aptId}/{roomId}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<Billing>> getBilling(@PathVariable Integer aptId,
      @PathVariable Integer roomId) {
    logger.info("getBilling:" + aptId + "," + roomId);
    Response<Billing> resp = billingService.getBilling(aptId, roomId, RentStatus.BILL_ELECTRIC);
    logger.debug(resp.toString());
    if (resp.getResponseStatus().equals(ResultStatus.RESULT_OK)) {
      return new ResponseEntity<Response<Billing>>(resp, HttpStatus.OK);
    }
    return new ResponseEntity<Response<Billing>>(HttpStatus.NO_CONTENT);
  }

  /**
   * get room billings
   * 
   * @param aptId
   * @param roomId
   * @return
   */
  @RequestMapping(value = "/room/{aptId}/{roomId}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<Transaction>> getRoomBilling(@PathVariable String aptId,
      @PathVariable String roomId) {
    logger.info("getRoomBilling:" + aptId + "roomId:" + roomId);
    Integer id = null;
    Integer rId = null;
    try {
      id = Integer.parseInt(aptId);
    } catch (Exception e) {

    }
    try {
      rId = Integer.parseInt(roomId);
    } catch (Exception e) {
      rId = null;
    }
    Response<Transaction> resp = billingService.getRoomBilling(id, rId);
    if (resp.getResponseStatus().equals(ResultStatus.RESULT_OK)) {
      return new ResponseEntity<Response<Transaction>>(resp, HttpStatus.OK);
    }
    return new ResponseEntity<Response<Transaction>>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/room", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<Transaction>> getRoomBillings() {

    Integer id = null;
    Integer rId = null;
    Response<Transaction> resp = billingService.getRoomBilling(id, rId);
    if (resp.getResponseStatus().equals(ResultStatus.RESULT_OK)) {
      return new ResponseEntity<Response<Transaction>>(resp, HttpStatus.OK);
    }
    return new ResponseEntity<Response<Transaction>>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/room/{aptId}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<Transaction>> getRoomByApartment(@PathVariable String aptId) {

    Integer id = null;
    Integer rId = null;
    try {
      id = Integer.parseInt(aptId);
    } catch (Exception e) {

    }
    Response<Transaction> resp = billingService.getRoomBilling(id, rId);
    if (resp.getResponseStatus().equals(ResultStatus.RESULT_OK)) {
      return new ResponseEntity<Response<Transaction>>(resp, HttpStatus.OK);
    }
    return new ResponseEntity<Response<Transaction>>(HttpStatus.NO_CONTENT);
  }

  /**
   * get room billings
   * 
   * @param aptId
   * @param roomId
   * @return
   */
  @RequestMapping(value = "/forcollection/{aptId}/{roomId}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<Transaction>> getBillForCollection(@PathVariable String aptId,
      @PathVariable String roomId) {
    logger.info("getRoomBilling:" + aptId + "roomId:" + roomId);
    Integer id = null;
    Integer rId = null;
    id = Integer.parseInt(aptId);
    rId = Integer.parseInt(roomId);
    Response<Transaction> resp = billingService.getRoomBillingForCollection(id, rId);
    if (resp.getResponseStatus().equals(ResultStatus.RESULT_OK)) {
      return new ResponseEntity<Response<Transaction>>(resp, HttpStatus.OK);
    }
    return new ResponseEntity<Response<Transaction>>(HttpStatus.NO_CONTENT);
  }
  
  /**
   * get the electric billings for collection
   * @param aptId
   * @param roomId
   * @return
   */
  @RequestMapping(value = "/electriccollection/{aptId}/{roomId}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<ElectricBill>> getElectricForCollection(@PathVariable String aptId,
      @PathVariable String roomId) {
    logger.info("getElectricForCollection:" + aptId + "roomId:" + roomId);
    Integer id = null;
    Integer rId = null;
    id = Integer.parseInt(aptId);
    rId = Integer.parseInt(roomId);
    Response<ElectricBill> resp = billingService.getElectricForCollection(id, rId);
    if (resp.getResponseStatus().equals(ResultStatus.RESULT_OK)) {
      return new ResponseEntity<Response<ElectricBill>>(resp, HttpStatus.OK);
    }
    return new ResponseEntity<Response<ElectricBill>>(HttpStatus.NO_CONTENT);
  }



  /**
   * get the tenants base on room id
   * 
   * @return
   */
  @RequestMapping(value = "/provider/{aptId}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<ElectricProvider>> getElectricProvider(
      @PathVariable String provider) {
    logger.info("provider:" + provider);
    Response<ElectricProvider> resp = adminService.getElectricProvider(provider);
    logger.debug(resp.toString());
    if (resp.getResponseStatus().equals(ResultStatus.RESULT_OK)) {
      return new ResponseEntity<Response<ElectricProvider>>(resp, HttpStatus.OK);
    }
    return new ResponseEntity<Response<ElectricProvider>>(HttpStatus.NO_CONTENT);
  }

  /**
   * get the tenants base on room id
   * 
   * @return
   */
  @RequestMapping(value = "/generate", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<BillingForm>> generateBilling(
      @RequestBody BillingForm billingForm) {
    logger.info(billingForm.toString());

    Response<BillingForm> resp = new Response<BillingForm>();
    try {
      resp = billingService.generateBillings(billingForm);

      if (resp.getResponseStatus().equals(ResultStatus.RESULT_OK)) {
        smsService.sendBillingMessages(billingForm);
      }

      return new ResponseEntity<Response<BillingForm>>(resp, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
      return new ResponseEntity<Response<BillingForm>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(value = "/pdf", method = RequestMethod.POST)
  public ResponseEntity<byte[]> showPdf(@RequestBody BillingForm billingForm) {
    // createPdf(domain, model);
    String pdfLocation = "";
    
    if (billingForm.getReceiptType().equals("collection")) {
      pdfLocation = collectionService.createPdf(billingForm);
    } else {
      pdfLocation = billingService.createPdf(billingForm);
    }

    Path path = Paths.get(pdfLocation);
    byte[] pdfContents = null;
    try {
      pdfContents = Files.readAllBytes(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.parseMediaType("application/pdf"));
    String filename = "billing-" + DateUtil.getCurrentDate() + ".pdf";
    headers.setContentDispositionFormData(filename, filename);
    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
    ResponseEntity<byte[]> response =
        new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
    File file = new File(pdfLocation);
    file.delete();
    return response;

  }

  protected void streamReport(HttpServletResponse response, byte[] data, String name)
      throws IOException {

    response.setContentType("application/pdf");
    response.setHeader("Content-disposition", "attachment; filename=" + name);
    response.setContentLength(data.length);

    response.getOutputStream().write(data);
    response.getOutputStream().flush();
  }


}
