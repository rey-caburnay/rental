package com.shinn.mail;

import java.util.List;

import com.shinn.service.model.RenterInfo;
/**
 * 
 * @author rbkshinn
 *
 */
public interface MailService {
  
  public void sendMail(List<RenterInfo> tenants);

}
