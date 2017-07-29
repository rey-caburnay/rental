package com.shinn.mail;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MailModel {
  MailType mailType = MailType.PLAIN_CONTENT;
  String subject;
  String from;
  String[] to;
  String[] cc;
  String[] bcc;
  String templatePath;
  String content;
  Map model;
  List<AttachMent> attachments;

  public List<AttachMent> getAttachments() {
    return attachments;
  }

  public void setAttachements(List<AttachMent> attachments) {
    this.attachments = attachments;
  }

  public String getTemplatePath() {
    return templatePath;
  }

  public void setTemplatePath(String templatePath) {
    this.templatePath = templatePath;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public MailType getMailType() {
    return mailType;
  }

  public void setMailType(MailType mailType) {
    this.mailType = mailType;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String[] getTo() {
    return to;
  }

  public void setTo(String[] to) {
    this.to = to;
  }

  public String[] getCc() {
    return cc;
  }

  public void setCc(String[] cc) {
    this.cc = cc;
  }

  public Map getModel() {
    if (model == null)
      this.model = new HashMap();
    return model;
  }

  public void setModel(Map model) {
    this.model = model;
  }

  public String[] getBcc() {
    return bcc;
  }

  public void setBcc(String[] bcc) {
    this.bcc = bcc;
  }

  public static class AttachMent {
    String fileName;

    File file;

    public String getFileName() {
      return fileName;
    }

    public void setFileName(String fileName) {
      this.fileName = fileName;
    }

    public File getFile() {
      return file;
    }

    public void setFile(File file) {
      this.file = file;
    }

  }
}
