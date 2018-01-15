package com.shinn.service.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParamMessage implements Serializable{
  
  /**
   * 
   */
  private static final long serialVersionUID = 4704799468957815226L;
  private Integer id;
  private String key;
  private String message;
  private Date ctime;
  private Date mtime;
  private String status;

}
