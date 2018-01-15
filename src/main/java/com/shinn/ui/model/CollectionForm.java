package com.shinn.ui.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.shinn.service.model.CollectionDetails;
import com.shinn.service.model.RenterInfo;
import com.shinn.service.model.Transaction;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * model to be shared by ui
 * 
 * @author rc185213
 *
 */
@Getter
@Setter
public class CollectionForm implements Serializable {

  private int aptId;
  private int roomId;
  private int renterId;
  private String paymentType;
  private String userId;
  private Date txDate;
  private Date startDate;
  private Date dueDate;
  private List<TransactionDetails> transactions;
  private List<Transaction> bills;
  private List<RenterInfo> tenants;
  private Cash cash;
  private Credit credit;
  private Paypal paypal;
  private String note;
  private String recievedBy;
  private String collectionType;

}
