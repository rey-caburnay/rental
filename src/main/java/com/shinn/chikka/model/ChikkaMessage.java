package com.shinn.chikka.model;

import java.util.UUID;

public class ChikkaMessage {
    private String messageType;
    private String mobileNumber;
    private String shortcode;
    private String messageId;
    private String message;
    private String requestId;
    private String timestamp;
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ChikkaMessage [messageType=" + messageType + ", mobileNumber=" + mobileNumber + ", shortcode="
                + shortcode + ", messageId=" + messageId + ", message=" + message + ", requestId=" + requestId
                + ", timestamp=" + timestamp + "]";
    }
    /**
     * @return the messageType
     */
    public String getMessageType() {
        return messageType;
    }
    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
    /**
     * @return the mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }
    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    /**
     * @return the shortcode
     */
    public String getShortcode() {
        return shortcode;
    }
    /**
     * @param shortcode the shortcode to set
     */
    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }
    /**
     * @return the messageId
     */
    public String getMessageId() {
        return messageId;
    }
    /**
     * @param messageId the messageId to set
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * @return the requestId
     */
    public String getRequestId() {
        return requestId;
    }
    /**
     * @param requestId the requestId to set
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    /**
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }
    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
    public String generateMessageId() {
        UUID uid = UUID.randomUUID();
        return String.valueOf(uid).substring(0, 32);
    }
}
