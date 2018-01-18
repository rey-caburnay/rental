package com.shinn.ui.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.shinn.web.view.Views;

public class Response<T extends Serializable> {
    
    @JsonView(Views.Public.class)
    private String responseStatus;
    @JsonView(Views.Public.class)
    private String errorMsg;
    @JsonView(Views.Public.class)
    private List<T> result;
    @JsonView(Views.Public.class)
    private T model;

    /**
     * @return the responseStatus
     */
    public String getResponseStatus() {
        return responseStatus;
    }

    /**
     * @param responseStatus the responseStatus to set
     */
    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    /**
     * @return the errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @param errorMsg the errorMsg to set
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * @return the result
     */
    public List<T> getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(List<T> result) {
        this.result = result;
    }

    /**
     * @return the model
     */
    public T getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(T model) {
        this.model = model;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Response [responseStatus=" + responseStatus + ", errorMsg=" + errorMsg + ", result=" + result
                + ", model=" + model + "]";
    }
    
}
