package com.shinn.controller;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shinn.chikka.Chikka;
import com.shinn.chikka.model.ChikkaMessage;
import com.shinn.service.model.Apartment;
import com.shinn.service.model.Sms;
import com.shinn.ui.model.Response;

@RestController
@RequestMapping(value="/notify")
public class NotificationController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    Chikka chikka;
    
    @RequestMapping(value = "/notification", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Apartment>> notificationReceived(
            @RequestParam(value = "message_type") String message_type,
            @RequestParam(value = "mobile_number") String mobile_number,
            @RequestParam(value = "shortcode") String shortcode,
            @RequestParam(value = "request_id") String request_id,
            @RequestParam(value = "message") String message,
            @RequestParam(value = "message_id") String messageId,
            @RequestParam(value = "timestamp") String timestamp) {

        logger.info("Message Type: " + message_type);
        logger.info("Mobile Number: " + mobile_number);
        logger.info("shortcode: " + shortcode);
        logger.info("Request ID: " + request_id);
        logger.info("Message: " + message);
        logger.info("Timestamp: " + timestamp);
        
        Response<Sms> resp = new Response<Sms>();
        
        ChikkaMessage chikkaRequest = new ChikkaMessage();
        chikkaRequest.setMessage(message);
        chikkaRequest.setMessageType(message_type);
        chikkaRequest.setMobileNumber(mobile_number);
        chikkaRequest.setShortcode(shortcode);
        chikkaRequest.setRequestId(request_id);
        chikkaRequest.setTimestamp(timestamp);
        chikkaRequest.setMessageId(messageId);

        resp  = chikka.readMessage(chikkaRequest);
        return new ResponseEntity(resp, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/receiver", method = RequestMethod.POST,
            produces = MediaType.ALL_VALUE)
    public ResponseEntity<Response<Apartment>> smsrecieved(
            @RequestParam(value = "message_type") String message_type,
            @RequestParam(value = "mobile_number") String mobile_number,
            @RequestParam(value = "shortcode") String shortcode,
            @RequestParam(value = "request_id") String request_id,
            @RequestParam(value = "message") String message,
            @RequestParam(value = "timestamp") String timestamp) {

        logger.info("Message Type: " + message_type);
        logger.info("Mobile Number: " + mobile_number);
        logger.info("shortcode: " + shortcode);
        logger.info("Request ID: " + request_id);
        logger.info("Message: " + message);
        logger.info("Timestamp: " + timestamp);
        
        Response<Sms> resp = new Response<Sms>();
        
        ChikkaMessage chikkaRequest = new ChikkaMessage();
        chikkaRequest.setMessage(message);
        chikkaRequest.setMessageType(message_type);
        chikkaRequest.setMobileNumber(mobile_number);
        chikkaRequest.setShortcode(shortcode);
        chikkaRequest.setRequestId(request_id);
        chikkaRequest.setTimestamp(timestamp);

        resp = chikka.readMessage(chikkaRequest);
        return new ResponseEntity(resp, HttpStatus.OK);
    }
}
