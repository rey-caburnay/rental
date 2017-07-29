package com.shinn.ui.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Bill implements Serializable {
  
 private Double generationCharge;
 private Double transmissionCharge;
 private Double systemLossCharge;
 private Double distributionCharge;
 private Double supplyCharge;
 private Double meteringCharge;
 private Double subsidyOnLifelineCharge;
 private Double seniorCitizenSubsidyCharge;
 private Double customerCharge;
 private Double surcharge;
 private Double subTotal1;
 private Double subTotal2;
 private Double subTotal3;
 private Tax tax;
 private Double grossAmount;
 private Double currentAmount;

}
