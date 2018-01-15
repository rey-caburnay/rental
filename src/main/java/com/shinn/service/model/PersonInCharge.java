package com.shinn.service.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonInCharge implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = -3751694861400999756L;

  private Integer id;
  private String name;
  private String mobileno;
  private String phone;
  private String status;
}
