package com.shinn.chikka.model;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shinn.util.StringUtil;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChikkaMessage implements Serializable{
    @JsonProperty("message_type")
    private String messageType;
    @JsonProperty("mobile_number")
    private String mobileNumber;
    @JsonProperty("shortcode")
    private String shortcode;
    @JsonProperty("request_id")
    private String messageId;
    @JsonProperty("message")
    private String message;
    @JsonIgnore
    private String requestId;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("secret_key")
    private String secretKey;
    @JsonIgnore
    private String status;
    @JsonIgnore 
    private String responseMsg;
    
    
     
    public String generateMessageId() {
       
        return StringUtil.getSaltString();
    }
}
