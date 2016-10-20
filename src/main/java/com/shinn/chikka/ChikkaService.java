package com.shinn.chikka;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.security.auth.callback.ChoiceCallback;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shinn.chikka.model.ChikkaMessage;
import com.shinn.chikka.model.ChikkaResponse;
import com.shinn.controller.NotificationController;
import com.shinn.dao.factory.ResultStatus;
import com.shinn.dao.repos.SmsDao;
import com.shinn.service.model.Sms;
import com.shinn.ui.model.Response;
import com.shinn.util.StringUtil;

@Component
public class ChikkaService implements Chikka {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ChikkaService.class);
    

    @Autowired
    SmsDao smsDao;
    
    @Override
    public Response<ChikkaResponse> sendMessage(ChikkaMessage message) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ChikkaConstants.CHIKKA_REQUEST)
                .queryParam("message_type", message.getMessageType())
                .queryParam("mobile_number", message.getMobileNumber())
                .queryParam("shortcode", message.getShortcode())
                .queryParam("message_id", message.getMessageId())
                .queryParam("message", message.getMessage())
                .queryParam("client_id",ChikkaConstants.CLIENT_ID)
                .queryParam("secret_key",ChikkaConstants.SECRET_KEY);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(), 
                HttpMethod.POST, 
                entity, 
                String.class);
        ObjectMapper mapper = new ObjectMapper();
        Response<ChikkaResponse> resp = new Response<ChikkaResponse>();
        try {
            ChikkaResponse chikkaResponse =  mapper.readValue(response.getBody(), ChikkaResponse.class);
            logger.info(response.toString());
            if (chikkaResponse.getStatus() == ChikkaConstants.RESPONSE_ACCEPTED) {
               resp.setModel(chikkaResponse);
            } else {
                resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
       return resp;
    }

    @Override
    public Response<Sms> readMessage(ChikkaMessage message) {
        Response<Sms> resp = new Response<Sms>();
        if(!StringUtil.isNullOrEmpty(message.getMessageType()) 
           && message.getMessageType().toUpperCase() == ChikkaConstants.SMS_INCOMING) {
            resp = this.processSms(message);
        }
        return resp;
    }

    @Override
    public Response<Sms> getNotification(ChikkaMessage message) {
        Response<Sms> resp = new Response<Sms>();
        if(!StringUtil.isNullOrEmpty(message.getMessageType()) 
           && message.getMessageType().toUpperCase() == ChikkaConstants.SMS_OUTGOING) {
           resp = this.processSms(message);
        }
        return resp;
    }
   /**
    *  
    * @return
    */
   private Response<Sms> processSms(ChikkaMessage message) {
       Response<Sms> resp = new Response<Sms>();
       Sms sms = new Sms();
       Date receivedDate = Calendar.getInstance().getTime();
       sms.setMessage(message.getMessage());
       sms.setShortcode(message.getShortcode());
       sms.setRequestId(message.getRequestId());
       sms.setMessageType(message.getMessageType());
       sms.setTimestamp(message.getTimestamp());
       sms.setSender(message.getMobileNumber());
       sms.setSendDate(receivedDate);
       sms.setReceivedDate(receivedDate);
       try {
           smsDao.saveUpdate(sms);
           resp.setModel(sms);
           resp.setResponseStatus(ChikkaConstants.SMS_ACCEPTED);
       } catch (Exception e) {
           logger.error(e.getMessage());
           resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
           sms.setStatus(ChikkaConstants.SMS_ERROR);
       }
       resp.setModel(sms);
       return resp;
   }
}
