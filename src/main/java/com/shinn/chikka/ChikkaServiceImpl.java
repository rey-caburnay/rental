package com.shinn.chikka;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.security.auth.callback.ChoiceCallback;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shinn.chikka.model.ChikkaMessage;
import com.shinn.chikka.model.ChikkaResponse;
import com.shinn.controller.NotificationController;
import com.shinn.dao.factory.ResultStatus;
import com.shinn.dao.repos.RenterDao;
import com.shinn.dao.repos.SmsDao;
import com.shinn.service.model.ElectricBill;
import com.shinn.service.model.Renter;
import com.shinn.service.model.RenterInfo;
import com.shinn.service.model.Sms;
import com.shinn.ui.model.Response;
import com.shinn.util.DateUtil;
import com.shinn.util.RentStatus;
import com.shinn.util.StringUtil;

@Service
public class ChikkaServiceImpl implements ChikkaService {
  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ChikkaServiceImpl.class);


  @Autowired
  SmsDao smsDao;
  @Autowired
  RenterDao renterDao;

  @Override
  public Response<ChikkaResponse> readMessage(ChikkaMessage message) {
    Response<ChikkaResponse> resp = new Response<ChikkaResponse>();
    if (!StringUtil.isNullOrEmpty(message.getMessageType())
        && message.getMessageType().toUpperCase() == ChikkaConstants.SMS_INCOMING) {
      resp = this.processSms(message);
    }
    return resp;
  }

  @Override
  public Response<ChikkaResponse> getNotification(ChikkaMessage message) {
    Response<ChikkaResponse> resp = new Response<ChikkaResponse>();
    if (!StringUtil.isNullOrEmpty(message.getMessageType())
        && message.getMessageType().toUpperCase() == ChikkaConstants.SMS_OUTGOING) {
      resp = this.processSms(message);
    }
    return resp;
  }

  /**
   * 
   * @return
   */
  private Response<ChikkaResponse> processSms(ChikkaMessage message) {
    Response<ChikkaResponse> resp = new Response<ChikkaResponse>();
    Sms sms = new Sms();
    ChikkaResponse chikaResponse = new ChikkaResponse();
    Date receivedDate = Calendar.getInstance().getTime();
    chikaResponse.setMessage(message.getMessage());
    // chikaResponse.setStatus(message.);
    // sms.setMessage(message.getMessage());
    // sms.setShortcode(message.getShortcode());
    // sms.setRequestId(message.getRequestId());
    // sms.setMessageType(message.getMessageType());
    // sms.setTimestamp(message.getTimestamp());
    // sms.setSender(message.getMobileNumber());
    // sms.setSendDate(receivedDate);
    // sms.setReceivedDate(receivedDate);
    // try {
    // smsDao.saveUpdate(sms);
    // resp.setModel(sms);
    // resp.setResponseStatus(ChikkaConstants.SMS_ACCEPTED);
    // } catch (Exception e) {
    // logger.error(e.getMessage());
    // resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
    // sms.setStatus(ChikkaConstants.SMS_ERROR);
    // }
    resp.setModel(chikaResponse);
    return resp;
  }

  public Response<ChikkaResponse> sendMessage(ChikkaMessage message) {

    String url = ChikkaConstants.CHIKKA_REQUEST;
    Response<ChikkaResponse> resp = new Response<>();
    ChikkaResponse chikkaResponse = new ChikkaResponse();
    try {
      URL obj = new URL(url);
      HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

      // add reuqest header
      con.setRequestMethod("POST");
      // con.setRequestProperty("User-Agent", "Mozilla/5.0");
      con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

      UriComponentsBuilder builder =
          UriComponentsBuilder.fromHttpUrl(url).queryParam("message_type", ChikkaConstants.SMS_SEND)
              .queryParam("message_type", ChikkaConstants.SMS_SEND)
              .queryParam("mobile_number", message.getMobileNumber())
              .queryParam("shortcode", ChikkaConstants.SHORTCODE)
              .queryParam("message_id", message.getMessageId())
              .queryParam("message", message.getMessage())
              .queryParam("client_id", ChikkaConstants.CLIENT_ID)
              .queryParam("secret_key", ChikkaConstants.SECRET_KEY);
//              .queryParam("timestamp", String.valueOf(DateUtil.getCurrentDate().getTime()));
      String urlParameters = builder.build().encode().getQuery();

      // Send post request
      con.setDoOutput(true);
      DataOutputStream wr = new DataOutputStream(con.getOutputStream());
      wr.writeBytes(urlParameters);
      wr.flush();
      wr.close();

      int responseCode = con.getResponseCode();

      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      ObjectMapper mapper = new ObjectMapper();

      chikkaResponse = mapper.readValue(response.toString(), ChikkaResponse.class);
      resp.setModel(chikkaResponse);
      resp.setResponseStatus(String.valueOf(responseCode));
      resp.setResponseStatus(ResultStatus.RESULT_OK);
    } catch (IOException e) {
      resp.setErrorMsg(e.getMessage());
      resp.setResponseStatus(ResultStatus.SMS_FAILED);
      e.printStackTrace();
    }
    return resp;
  }

  @Override
  public ChikkaMessage sendMessage(String message, String mobile) {
    ChikkaMessage chikkaMsg = new ChikkaMessage();
    chikkaMsg.setMessage(message);
    chikkaMsg.setMessageId(chikkaMsg.generateMessageId());
    chikkaMsg.setShortcode(ChikkaConstants.SHORTCODE);
    chikkaMsg.setSecretKey(ChikkaConstants.SECRET_KEY);
    chikkaMsg.setClientId(ChikkaConstants.CLIENT_ID);
    chikkaMsg.setMobileNumber(mobile);
    chikkaMsg.setStatus(RentStatus.SMS_FAILED);
    chikkaMsg.setTimestamp(String.valueOf(DateUtil.getCurrentDate().getTime()));
    chikkaMsg.setResponseMsg("failed to send sms message");
    chikkaMsg.setMessageType(ChikkaConstants.SMS_SEND);
    Response<ChikkaResponse> resp = this.sendMessage(chikkaMsg);
    if (resp.getResponseStatus().equals(ResultStatus.RESULT_OK)) {
      chikkaMsg.setStatus(String.valueOf(resp.getModel().getStatus()));
      chikkaMsg.setResponseMsg(resp.getModel().getMessage());
     
    }
    return chikkaMsg;
  }

  @Override
  public void postRequest(ChikkaMessage message) throws ClientProtocolException, IOException {
    // TODO Auto-generated method stub
    
  }

}
