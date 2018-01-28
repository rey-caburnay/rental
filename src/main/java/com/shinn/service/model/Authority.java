package com.shinn.service.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Authority implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = -6503066950732600296L;
  private Integer id;
  private String name;
  private Date ctime;
  private Date mtime;

}
