package com.shinn.controller;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shinn.chikka.Chikka;
import com.shinn.chikka.ChikkaConstants;
import com.shinn.chikka.model.ChikkaMessage;
import com.shinn.chikka.model.ChikkaResponse;
import com.shinn.dao.factory.ResultStatus;
import com.shinn.service.model.Renter;
import com.shinn.ui.model.Response;

@RestController
@RequestMapping(value = "/sms")
public class SmsController {
  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SmsController.class);

  @Autowired
  Chikka chikka;

  @RequestMapping(value = "/test", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<ChikkaResponse>> registration() {
    try {
      ChikkaMessage message = new ChikkaMessage();
      message.setMobileNumber("09954645794");
      message.setMessage("this is a test");
      String messageId = message.generateMessageId();
      message.setMessageId(messageId);
      message.setMessageType(ChikkaConstants.SMS_SEND);
      // Response<ChikkaResponse> resp = Response.post(message);
//      chikka.postRequest(message);
      // logger.debug(resp.toString());
      // if(resp.getResponseStatus().equals(ResultStatus.RESULT_OK)){
      // return new ResponseEntity<Response<ChikkaResponse>>(resp, HttpStatus.OK);
      // }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ResponseEntity(HttpStatus.BAD_REQUEST);
  }
  
  @RequestMapping(value = "/test2", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response<ChikkaResponse>> billinform() {
    try {
      ChikkaMessage message = new ChikkaMessage();
      message.setMobileNumber("09954645794");
      message.setMessage("this is a test");
      String messageId = message.generateMessageId();
      message.setMessageId(messageId);
      message.setMessageType(ChikkaConstants.SMS_SEND);
      // Response<ChikkaResponse> resp = Response.post(message);
//      chikka.postRequest(message);
      // logger.debug(resp.toString());
      // if(resp.getResponseStatus().equals(ResultStatus.RESULT_OK)){
      // return new ResponseEntity<Response<ChikkaResponse>>(resp, HttpStatus.OK);
      // }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ResponseEntity(HttpStatus.BAD_REQUEST);
  }

}
