package com.shinn.service.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Token implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -5724083121320919688L;
  private String series;
  private String value;
  private Date date;
  private String ipAddress;
  private String userAgent;
  private String userLogin;
}
