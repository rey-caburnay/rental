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
    @JsonProperty("message_type")
    private String mobileNumber;
    @JsonProperty("shortcode")
    private String shortcode;
    @JsonProperty("message_id")
    private String messageId;
    @JsonProperty("message")
    private String message;
    @JsonIgnore
    private String requestId;
    @JsonIgnore
    private String timestamp;
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("secret_key")
    private String secretKey;
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ChikkaMessage [messageType=" + messageType + ", mobileNumber=" + mobileNumber + ", shortcode="
                + shortcode + ", messageId=" + messageId + ", message=" + message + ", requestId=" + requestId
                + ", timestamp=" + timestamp + "]";
    }
     
    public String generateMessageId() {
       
        return StringUtil.getSaltString();
    }
}
