package com.shinn.mail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
//import javax.mail
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import com.shinn.service.model.RenterInfo;
import com.shinn.util.RentStatus;
import com.shinn.util.StringUtil;

/**
 * create record for each mail that is send.
 * TODO
 * @author rbkshinn
 *
 */
@Component
public class MailServiceImpl implements MailService {
  
  public void sendMail(List<RenterInfo> tenants) {
    MailModel mail = null;
    List<String> recepients = new ArrayList<>();
    Iterator<RenterInfo> itr = tenants.iterator();
    while(itr.hasNext()) {
      RenterInfo tenant = itr.next();
      if(!StringUtil.isNullOrEmpty(tenant.getEmail())) {
        recepients.add(tenant.getEmail());
      }
    }
    mail = new MailModel();
    mail.setFrom(MailUtil.MAIL_SENDER);
    mail.setMailType(MailType.PLAIN_CONTENT);
    mail.setTo(recepients.toArray(new String[recepients.size()]));
//    mail.setContent(RentStatus.RECEIPT_GENERIC_PAYMENT_MESSAGE);
    SendEmailUtil.sendEmail(mail);
    
  }
}