package com.shinn.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shinn.dao.factory.ResultStatus;
import com.shinn.service.CollectionService;
import com.shinn.service.CollectionServiceImpl;
import com.shinn.ui.model.BillingForm;
import com.shinn.ui.model.CollectionForm;
import com.shinn.ui.model.ElectricCollectionForm;
import com.shinn.ui.model.Response;

@RestController
@RequestMapping(value = "/collection")
public class CollectionController {
  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CollectionController.class);

  @Autowired
  CollectionService collectionService;

  @RequestMapping(value = "/electric", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<ElectricCollectionForm>> collectElectric(
      @RequestBody ElectricCollectionForm electricCollection) {
    logger.info(electricCollection.toString());

    Response<ElectricCollectionForm> resp = new Response<ElectricCollectionForm>();
    try {
      resp = collectionService.saveElectricCollection(electricCollection);
//      String pdfLocation = billingService.createPdf(billingForm);
      //create receipt?
      //send sms?
      resp.setResponseStatus(ResultStatus.RESULT_OK);
      // if (resp.getResponseStatus().equals(ResultStatus.RESULT_OK)) {
      return new ResponseEntity<Response<ElectricCollectionForm>>(resp, HttpStatus.OK);
      // }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
//  @RequestMapping(value = "/getRooms", method = RequestMethod.POST,
//      produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity<Response<CollectionForm>> getRooms(
//      @PathVariable String aptId, @PathVariable String roomId) {
//    logger.info("aptID {} roomId {}", aptId, roomId);
//
//    Response<CollectionForm> resp = new Response<CollectionForm>();
//    try {
//      collectionService.get
//      resp.setResponseStatus(ResultStatus.RESULT_OK);
//      return new ResponseEntity<Response<CollectionForm>>(resp, HttpStatus.OK);
//
//    } catch (Exception e) {
//
//      e.printStackTrace();
//      resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
//      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//  }


}
