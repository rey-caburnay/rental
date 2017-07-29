package com.shinn.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SendEmailUtil {
  private static final Log LOG = LogFactory.getLog(SendEmailUtil.class);

  private SendEmailUtil() {
    // static class
  }

  public static void sendEmail(MailModel mailModel) {
    if (mailModel == null)
      return;
    Properties props = MailUtil.getProperties();
    Session session = Session.getInstance(props, new MyAuthenticator(MailUtil.MAIL_USER, MailUtil.MAIL_PASSWORD));
    MimeMessage message = new MimeMessage(session);
    try {
      MailUtil.buildMessage(message, mailModel);
      Transport.send(message);
    } catch (MessagingException e) {
      LOG.error(e);
    }
  }
}

class MyAuthenticator extends Authenticator {
  private String userName;
  private String password;

  public MyAuthenticator(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  @Override
  protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(userName, password);
  }
}
