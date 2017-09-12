package com.shinn.ui.model;

import java.io.Serializable;

import com.shinn.service.model.Billing;
import com.shinn.service.model.ElectricCollection;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ElectricCollectionForm implements Serializable {

  private Integer userId;
  private Integer aptId;
  private Integer roomId;
  private Cash cash;
  private Billing billing;
  private ElectricCollection collection;
}
