package com.shinn.chikka;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.shinn.chikka.model.ChikkaMessage;
import com.shinn.chikka.model.ChikkaResponse;
import com.shinn.service.model.RenterInfo;
import com.shinn.service.model.Sms;
import com.shinn.ui.model.Response;

public interface Chikka {
    
    public Response<Sms> readMessage(ChikkaMessage message);
    
    public Response<Sms> getNotification(ChikkaMessage message);

    public Response<ChikkaResponse> sendMessage(ChikkaMessage message);
    /**
     * 
     * @param message
     * @return
     */
    public Response<ChikkaResponse> sendBillingMessages(List<RenterInfo> tenants);
    public Response<ChikkaResponse> sendElectricBillingMessage(List<RenterInfo> tenants);
    
    public void postRequest(ChikkaMessage message) throws ClientProtocolException, IOException;
}
