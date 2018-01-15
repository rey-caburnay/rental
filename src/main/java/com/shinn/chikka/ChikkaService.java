package com.shinn.chikka;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.shinn.chikka.model.ChikkaMessage;
import com.shinn.chikka.model.ChikkaResponse;
import com.shinn.service.model.ElectricBill;
import com.shinn.service.model.RenterInfo;
import com.shinn.service.model.Sms;
import com.shinn.ui.model.Response;

public interface ChikkaService {
    
    public Response<ChikkaResponse> readMessage(ChikkaMessage message);
    
    public Response<ChikkaResponse> getNotification(ChikkaMessage message);

    public Response<ChikkaResponse> sendMessage(ChikkaMessage message);
    
    public ChikkaMessage sendMessage(String message, String mobile);
    
    public void postRequest(ChikkaMessage message) throws ClientProtocolException, IOException;
}
