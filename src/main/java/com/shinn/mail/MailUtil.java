package com.shinn.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shinn.util.StringUtil;

public class MailUtil
{
    private static final Log LOG = LogFactory.getLog(MailUtil.class);
    
	public static final String CHARSET = "gb2312";

	public static final String TEXT_TYPE = "text/html;charset=gb2312";
	
	public static final String CONTENT_FLAG = "$";
	
	public static final String DELIM_STRING =";,";
	
	public static final String MAIL_SMTP_HOST;

	public static final String MAIL_USER;

	public static final String MAIL_PASSWORD;

	private static final Properties PROPS = new Properties();
	
    
    public static final String EMAIL_SUBJECT = "subject";
    public static final String EMAIL_CONTENT = "content";
    public static final String EMAIL_CONTENT_TPL = "contentTpl";
    public static final String EMAIL_TO_LIST = "toList";
    public static final String EMAIL_CONTENT_TPL_PARAMS = "params";
    public static final String EMAIL_CONTENT_TPL_REASON_PLACEHOLDER = "Reason:$reason$";
    
    public static final String MAIL_SENDER = "no-reply@gmail.com";
    public static final String MAIL_RECEIVER = "rbkshinn@gmail.com";
	static
	{
		try
		{
			PROPS.load(MailUtil.class.getResourceAsStream("/com/shinn/mail/mail.properties"));
		} catch (FileNotFoundException e)
		{
			LOG.error(e);
		} catch (IOException e)
		{
			LOG.error(e);
		}
		MAIL_SMTP_HOST = PROPS.getProperty("smtp");
		MAIL_USER = PROPS.getProperty("account");
		MAIL_PASSWORD = PROPS.getProperty("password");
		PROPS.put("mail.smtp.host", MAIL_SMTP_HOST);
		PROPS.put("mail.smtp.auth", "true");
	}

	private MailUtil()
	{
	  //static class
	}
	
	public static Properties getProperties()
	{
		return PROPS;
	}
	
	public static void buildMessage(MimeMessage message, MailModel mail) throws MessagingException
	{
		if (mail.getBcc() != null)
			MailUtil.setAddress(message, mail.getBcc(),
					Message.RecipientType.BCC);
		if (mail.getCc() != null)
			MailUtil.setAddress(message, mail.getCc(),
					Message.RecipientType.CC);
		if (mail.getTo() != null)
			MailUtil.setAddress(message, mail.getTo(),
					Message.RecipientType.TO);

        if (mail.getFrom() != null) {
          message.setFrom(new InternetAddress(mail.getFrom()));
        } else {
          message.setFrom(new InternetAddress(MailUtil.MAIL_USER));
        }
		if (mail.getSubject() != null)
		{
			message.setSubject(mail.getSubject(), CHARSET);
		}
		message.setSentDate(new Date());
		
		if (mail.getMailType() == MailType.HTML_TEMPLATE)
		{
			Map model = mail.getModel();
			String content = buildContent(mail.getContent(),
					(Map<String, String>) model, false);
			message.setContent(content, TEXT_TYPE);
		} else
		{
			message.setContent(mail.getContent(), TEXT_TYPE);
		}
		
		if (mail.getAttachments() != null)
		{
			MimeMultipart minMimeMultipart = new MimeMultipart();
			message.setContent(minMimeMultipart);
			for (MailModel.AttachMent attachment : mail.getAttachments())
			{
				MailUtil.addAttachment(attachment.getFileName(),
						attachment.getFile(), minMimeMultipart);
			}
		}
	}

	public static String buildContent(String source,
			Map<String, String> context, boolean isHtmlContentType)
	{
		return convertAllMessage(source, context, isHtmlContentType);
	}

	/**
	 * 
	 * Replace all varName to varValue in a string.
	 * 
	 * @param content
	 * @param variables
	 * @param isHtmlContentType
	 *            : true-The conent type is text/html; false- The content is
	 * @return
	 */
	public static String convertAllMessage(String content1,
			Map<String, String> paramAndValues, boolean isHtmlContentType)
	{
		Map<String, String> wrapperedParamAndValues = new HashMap<String, String>();
		Set<String> params = paramAndValues.keySet();
		for (String param : params)
		{
			wrapperedParamAndValues.put(CONTENT_FLAG + param + CONTENT_FLAG,
					String.valueOf(paramAndValues.get(param)));
		}
		return DataFormat.convertAllMessage(content1,
				wrapperedParamAndValues, isHtmlContentType);
	}

	public static void addAttachment(String attachmentFilename, File file,
			MimeMultipart mimeMultipart) throws MessagingException
	{
		FileDataSource dataSource = new FileDataSource(file);
		addAttachment(attachmentFilename, dataSource, mimeMultipart);
	}

	public static void addAttachment(String attachmentFilename,
			DataSource dataSource, MimeMultipart mimeMultipart)
			throws MessagingException
	{
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setDisposition(MimeBodyPart.ATTACHMENT);
		mimeBodyPart.setFileName(attachmentFilename);
		mimeBodyPart.setDataHandler(new DataHandler(dataSource));
		mimeMultipart.addBodyPart(mimeBodyPart);
	}
	
	public static void setAddress(MimeMessage message, String[] users,Message.RecipientType type) throws MessagingException
	{
		message.addRecipients(type, getAddress(users));
	}

	public static InternetAddress[] getAddress(String[] users) throws AddressException
	{
		InternetAddress[] addresses = new InternetAddress[users.length];
		for (int i = 0; i < users.length; i++)
		{
			addresses[i] = new InternetAddress(users[i]);
		}
		return addresses;
	}
	
	public static List<String> getTokeList(String userListStr)
	{
		if (!StringUtil.isNullOrEmpty(userListStr))
		{
			List<String> userList = new ArrayList<String>();
			StringTokenizer stringTokenizer = new StringTokenizer(userListStr,DELIM_STRING);
			while (stringTokenizer.hasMoreTokens())
			{
				String user = stringTokenizer.nextToken();
				userList.add(user);
			}
			return userList;
		}
		return Collections.emptyList();
	}
}
