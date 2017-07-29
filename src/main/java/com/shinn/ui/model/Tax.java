package com.shinn.ui.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Tax implements Serializable {

  private Double localFranchise;
  private Double generation;
  private Double transmission;
  private Double systemLoss;
  private Double distribution;
  private Double others;
  private Double missionaryElectrification;
  private Double environmental;
  private Double npc;
  private Double fitAllRenewable;
  private Double subTotal;
}
