package com.shinn.service.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ElectricProvider implements Serializable {
  private Integer id;
  private String provider;
  private Double generationCharge;
  private Double transmissionCharge;
  private Double systemLoss;
  private Double distributionCharge;
  private Double supplyCharge;
  private Double retailCustomer;
  private Double meteringSystem;
  private Double lifeLineSubsidy;
  private Double localFranchiseTax;
  private Double distributionTax;
  private Double othersTax;
  private Double npc;
  private Double missionaryElectrification;
  private Double environmentalCharge;
  private Double fitAllRenewable;
  private Double surcharge;
  private Double systemLossTax;
  private Double transmissionTax;
  private Double generationTax;
  private Double overdue;
  private Double seniorCitizenSubsidy;
  
}
