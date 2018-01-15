package com.shinn.service;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinn.chikka.ChikkaService;
import com.shinn.chikka.model.ChikkaMessage;
import com.shinn.chikka.model.ChikkaResponse;
import com.shinn.dao.repos.RentMessageDao;
import com.shinn.dao.repos.RenterDao;
import com.shinn.dao.repos.SmsDao;
import com.shinn.service.model.ElectricBill;
import com.shinn.service.model.Renter;
import com.shinn.service.model.RenterInfo;
import com.shinn.service.model.Sms;
import com.shinn.service.model.Transaction;
import com.shinn.ui.model.BillingForm;
import com.shinn.ui.model.Response;
import com.shinn.util.DateUtil;
import com.shinn.util.RentStatus;
import com.shinn.util.StringUtil;

@Service
public class SmsServiceImpl implements SmsService {
  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
  public static final String BILLING_BEFORE_DUE_DATE_MESSAGE = "billingBeforeDueDate";
  public static final String BILLING_MESSAGE = "billingDueDate";
  public static final String ELECTRIC_BILLING_MESSAGE = "electricBilling";
  public static final String COLLECTION_MESSAGE_PROPERTY = "collection_property";
  public static final String COLLECTION_MESSAGE_ELECTRIC = "colletion_electric";
  private static final Locale ph = new Locale("ph","PH");
  
  @Autowired
  ChikkaService chikkaService;
  @Autowired
  SmsDao smsDao;
  @Autowired
  RenterDao renterDao;
  @Autowired
  RentMessageDao rentMessageDao;

  @Override
  public Response<Sms> sendMessage(String message, String mobile) {
    Response<Sms> response = new Response<Sms>();
    ChikkaMessage chikkaMsg = chikkaService.sendMessage(message, mobile);
    Sms sms = createSmsLog(chikkaMsg);
    response.setModel(sms);
    response.setResponseStatus(sms.getStatus());
    return response;
  }

  @Override
  public Response<Sms> sendBillingMessages(List<RenterInfo> tenants) {
    Response<ChikkaResponse> resp = new Response<>();
    ChikkaMessage sms = new ChikkaMessage();
    if (StringUtil.isNullOrEmpty(tenants)) {
      resp.setErrorMsg("No Tenants available");
      resp.setResponseStatus("Not sent");
    } else {
      Iterator<RenterInfo> itr = tenants.iterator();
      while (itr.hasNext()) {
        RenterInfo tenant = itr.next();
        if (!StringUtil.isNullOrEmpty(tenant.getMobileNumber())) {
          sms.setMobileNumber(tenant.getMobileNumber());
          sms.setMessageId(sms.generateMessageId());
          sms.setMessage("");
        }

      }
    }
    return null;
  }

  @Override
  public Response<Sms> sendElectricBillingMessage(String type, ElectricBill bill) {
    Renter renter = renterDao.getOccupancy(bill.getAptId(), bill.getRoomId());
    Response<Sms> response = new Response<>();
    Date dueDate = DateUtil.getCurrentDate();
    if (renter != null && renter.getMobileNo() != null || renter.getMobileNo() != "none") {
      if (bill.getDueDate() != null) {
        dueDate = bill.getDueDate();
      }
      String msg = generateMessage(type, bill.getTotalAmount(), dueDate.getMonth(), null);
      response = sendMessage(msg, renter.getMobileNo());
    }
    return response;
  }

  private Sms createSmsLog(ChikkaMessage chikkaMessage) {
    Sms sms = new Sms();
    try {
      sms.setMessage(chikkaMessage.getMessage());
      sms.setSendDate(DateUtil.getCurrentDate());
      sms.setMessageType(chikkaMessage.getMessageType());
      sms.setRecipient(chikkaMessage.getMobileNumber());
      sms.setShortcode(chikkaMessage.getShortcode());
      sms.setStatus(chikkaMessage.getStatus());
      sms.setRequestId(chikkaMessage.getMessageId());
      sms.setTimestamp(DateUtil.getCurrentDate().getTime()+"");
      int id = smsDao.saveUpdate(sms);
      sms.setId(id);
      smsDao.commit();
    } catch (Exception e) {
      logger.error("sendMessage: " + e.getMessage(), e);
    }
    return sms;
  }

  private String generateMessage(String messageType, Double amount, int month, String ref) {
    String message = RentStatus.RECEIPT_RENT_MESSAGE;
    NumberFormat formatter = NumberFormat.getCurrencyInstance(ph);
    switch (messageType) {
      case BILLING_BEFORE_DUE_DATE_MESSAGE:
        message = RentStatus.BEFORE_DUE_MESSAGE;
        message = message.replaceAll("\\{amount\\}", formatter.format(amount));
        message = message.replace("{duedate}", DateUtil.getNameOfMonth(month));
        break;
      case BILLING_MESSAGE:
        message = RentStatus.DUE_DATE_MESSAGE;
        message = message.replaceAll("\\{amount\\}", formatter.format(amount));
        message = message.replace("{month}", DateUtil.getNameOfMonth(month));
        break;
      case ELECTRIC_BILLING_MESSAGE:
        message = RentStatus.ELECTRIC_BILL_MESSAGE;
        message = message.replaceAll("\\{amount\\}", formatter.format(amount));
        message = message.replace("{month}", DateUtil.getNameOfMonth(month));
        break;
      case COLLECTION_MESSAGE_PROPERTY:
        message = RentStatus.RECEIPT_RENT_MESSAGE;
        message = message.replaceAll("\\{amount\\}", formatter.format(amount));
        message = message.replace("{reference}", ref);
    }
    return message;
  }

  @Override
  public Response<Sms> sendElectricBillingMessage(BillingForm billing) {
    Response<Sms> response = new Response<>();
    for (ElectricBill electricBill : billing.getRooms()) {
      Renter renter = renterDao.getOccupancy(electricBill.getAptId(), electricBill.getRoomId());
      if (renter != null && renter.getMobileNo() != null) {
        response = sendMessage(ELECTRIC_BILLING_MESSAGE, renter.getMobileNo());
      }
    }
    return response;
  }

  @Override
  public Response<Sms> sendBillingMessages(BillingForm billingForm) {
    Response<Sms> resp = new Response<>();
    switch (billingForm.getBillingType()) {
      case RentStatus.BILL_ELECTRIC:
        for (ElectricBill bill : billingForm.getRooms()) {
          Renter renter = renterDao.getOccupancy(bill.getAptId(), bill.getRoomId());
          if(renter != null && renter.getMobileNo() != null) {
            String message = this.generateMessage(ELECTRIC_BILLING_MESSAGE, bill.getGrossAmount(),
                bill.getDueDate().getMonth(), null);
            this.sendMessage(message, renter.getMobileNo());
          }

        }
        break;
      case RentStatus.BILL_RENT:
        for (Transaction tx : billingForm.getBills()) {
          if (tx.getRenter() != null && tx.getRenter().getMobileNo() != null) {
            String message = this.generateMessage(BILLING_MESSAGE, tx.getAmountPayable(),
                tx.getDueDate().getMonth(), null);
            this.sendMessage(message, tx.getRenter().getMobileNo());
          }

        }
        break;
    }
    return resp;
  }
  
  @Override
  public Response<Sms> sendCollectionMessage(BillingForm billingForm) {
    Response<Sms> resp = new Response<>();
    switch (billingForm.getBillingType()) {
      case RentStatus.BILL_ELECTRIC:
        for (ElectricBill bill : billingForm.getRooms()) {
          Renter renter = renterDao.getOccupancy(bill.getAptId(), bill.getRoomId());
          if(renter != null && renter.getMobileNo() != null) {
            String message = this.generateMessage(COLLECTION_MESSAGE_ELECTRIC, 
                Double.valueOf(billingForm.getCash().getAmountPaid()),
                bill.getDueDate().getMonth(), bill.getCollectionNo());
            this.sendMessage(message, renter.getMobileNo());
          }

        }
        break;
      case RentStatus.BILL_RENT:
        for (Transaction tx : billingForm.getBills()) {
          if (tx.getRenter() != null && tx.getRenter().getMobileNo() != null) {
            String message = this.generateMessage(COLLECTION_MESSAGE_PROPERTY, 
                Double.valueOf(billingForm.getCash().getAmountPaid()),
                tx.getDueDate().getMonth(), tx.getCollectionNo());
            this.sendMessage(message, tx.getRenter().getMobileNo());
          }

        }
        break;
    }
    return resp;
  }


}
