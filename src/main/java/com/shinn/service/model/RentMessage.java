package com.shinn.service.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RentMessage implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -8544625282463213747L;
  private Integer id;
  private String key;
  private String message;
  private Date ctime;
  private Date mtime;
  
}
