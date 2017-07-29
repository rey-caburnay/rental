package com.shinn.chikka;

import java.util.List;

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
    public Response<ChikkaResponse> sendMessages(List<RenterInfo> tenants);
}
