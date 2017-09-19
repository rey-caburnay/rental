package com.shinn.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.shinn.chikka.ChikkaConstants;
import com.shinn.chikka.model.ChikkaMessage;
import com.shinn.chikka.model.ChikkaResponse;
import com.shinn.configuration.test.TestContext;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestContext.class, loader = AnnotationConfigContextLoader.class)
public class SmsTest {
  
  @Test
  public void sendSMS() throws JsonProcessingException {
    final String uri = ChikkaConstants.CHIKKA_REQUEST;
    
    ChikkaMessage message = new ChikkaMessage();
    message.setMessage("this is a test from rest template");
    message.setMessageId(message.generateMessageId());
    message.setShortcode(ChikkaConstants.SHORTCODE);
    message.setSecretKey(ChikkaConstants.SECRET_KEY);
    message.setClientId(ChikkaConstants.CLIENT_ID);
    message.setMobileNumber("09954645794");
 
    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper mapper = new ObjectMapper();
    String jsonInString = mapper.writeValueAsString(message);
    System.out.println(jsonInString);
    
//    MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
//    jsonHttpMessageConverter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//    restTemplate.getMessageConverters().add(jsonHttpMessageConverter);

//    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    HttpEntity<String> entity = new HttpEntity<String>(jsonInString);
     restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    ChikkaResponse result = restTemplate.postForObject( uri, entity, ChikkaResponse.class);
 
    System.out.println(result);
  }
  
  

}
