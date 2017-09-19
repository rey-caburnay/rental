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
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.security.auth.callback.ChoiceCallback;

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
import com.shinn.dao.repos.SmsDao;
import com.shinn.service.model.RenterInfo;
import com.shinn.service.model.Sms;
import com.shinn.ui.model.Response;
import com.shinn.util.StringUtil;

@Component
public class ChikkaService implements Chikka {
  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ChikkaService.class);


  @Autowired
  SmsDao smsDao;

  // @Override
  // public Response<ChikkaResponse> sendMessage(ChikkaMessage message) {
  // HttpHeaders headers = new HttpHeaders();
  // headers.add("Accept", MediaType.TEXT_PLAIN_VALUE);
  // headers.add("Content-Type", "application/x-www-form-urlencoded");
  // // UriComponentsBuilder builder =
  // // UriComponentsBuilder.fromHttpUrl(ChikkaConstants.CHIKKA_REQUEST)
  // // .queryParam("message_type", message.getMessageType())
  // // .queryParam("mobile_number", message.getMobileNumber())
  // // .queryParam("shortcode", ChikkaConstants.SHORTCODE)
  // // .queryParam("message_id", message.getMessageId())
  // // .queryParam("message", message.getMessage())
  // // .queryParam("client_id", ChikkaConstants.CLIENT_ID)
  // // .queryParam("secret_key", ChikkaConstants.SECRET_KEY);
  // HttpEntity<?> entity = new HttpEntity<>(headers);
  // RestTemplate restTemplate = new RestTemplate();
  // // HttpEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(),
  // // HttpMethod.POST, entity, String.class);
  // // ObjectMapper mapper = new ObjectMapper();
  // // Response<ChikkaResponse> resp = new Response<ChikkaResponse>();
  // // try {
  // // ChikkaResponse chikkaResponse = mapper.readValue(response.getBody(), ChikkaResponse.class);
  // // logger.info(response.toString());
  // // if (chikkaResponse.getStatus() == ChikkaConstants.RESPONSE_ACCEPTED) {
  // // resp.setModel(chikkaResponse);
  // // } else {
  // // resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
  // // }
  // // } catch (Exception e) {
  // // logger.error(e.getMessage());
  // // }
  // // return resp;
  // return null;
  // }

  @Override
  public Response<Sms> readMessage(ChikkaMessage message) {
    Response<Sms> resp = new Response<Sms>();
    if (!StringUtil.isNullOrEmpty(message.getMessageType())
        && message.getMessageType().toUpperCase() == ChikkaConstants.SMS_INCOMING) {
      resp = this.processSms(message);
    }
    return resp;
  }

  @Override
  public Response<Sms> getNotification(ChikkaMessage message) {
    Response<Sms> resp = new Response<Sms>();
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

  @Override
  public Response<ChikkaResponse> sendBillingMessages(List<RenterInfo> tenants) {
    Response<ChikkaResponse> resp = new Response<>();
    ChikkaMessage sms = new ChikkaMessage();
    if (StringUtil.isNullOrEmpty(tenants)) {
      resp.setErrorMsg("No Tenants available");
      resp.setResponseStatus("Not sent");
    } else {
      Iterator<RenterInfo> itr = tenants.iterator();
      while (itr.hasNext()) {
        RenterInfo tenant = itr.next();
        if (!StringUtil.isNullOrEmpty(tenant.getMobileNumber())) {
          sms.setMobileNumber(tenant.getMobileNumber());
           sms.setMessageId(sms.generateMessageId());
           sms.setMessage("");
        }

      }
    }
    return null;
  }

  public void postRequest(ChikkaMessage message) throws IOException {
    // try {
    // RestTemplate rt = new RestTemplate();
    // rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    // rt.getMessageConverters().add(new StringHttpMessageConverter());
    // String uri = new String(ChikkaConstants.CHIKKA_REQUEST);
    // Map<String, String> vars = new HashMap();
    //// vars.put("id", "JS01");
    // vars.put("message_type", message.getMessageType());
    // vars.put("mobile_number", message.getMobileNumber());
    // vars.put("shortcode", ChikkaConstants.SHORTCODE);
    // vars.put("message_id", message.getMessageId());
    // vars.put("message", message.getMessage());
    // vars.put("client_id", ChikkaConstants.CLIENT_ID);
    // vars.put("secret_key", ChikkaConstants.SECRET_KEY);
    //
    // ChikkaResponse reponse = new ChikkaResponse();
    // ChikkaResponse returns = rt.postForObject(uri, reponse, ChikkaResponse.class, vars);
    // logger.debug("Response: " + returns.toString());
    // } catch (HttpClientErrorException e) {
    // /**
    // *
    // * If we get a HTTP Exception display the error message
    // */
    // logger.error("error: " + e.getResponseBodyAsString());
    // ObjectMapper mapper = new ObjectMapper();
    // // ErrorHolder eh = mapper.readValue(e.getResponseBodyAsString(), ErrorHolder.class);
    // // logger.error("error: " + eh.getErrorMessage());
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    try {
//      this.sendPost(message);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
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
              .queryParam("mobile_number", message.getMobileNumber())
              .queryParam("shortcode", ChikkaConstants.SHORTCODE)
              .queryParam("message_id", message.getMessageId())
              .queryParam("message", message.getMessage())
              .queryParam("client_id", ChikkaConstants.CLIENT_ID)
              .queryParam("secret_key", ChikkaConstants.SECRET_KEY);
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

    } catch (IOException e) {
      resp.setErrorMsg(e.getMessage());
      e.printStackTrace();
    }
    return resp;
  }

  @Override
  public Response<ChikkaResponse> sendElectricBillingMessage(List<RenterInfo> tenants) {
    // TODO Auto-generated method stub
    return null;
  }

}
