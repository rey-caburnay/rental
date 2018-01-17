package com.shinn.service;

import java.text.NumberFormat;
import java.util.ArrayList;
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
import com.shinn.dao.repos.ApartmentDao;
import com.shinn.dao.repos.RentMessageDao;
import com.shinn.dao.repos.RentalDao;
import com.shinn.dao.repos.RenterDao;
import com.shinn.dao.repos.RoomDao;
import com.shinn.dao.repos.SmsDao;
import com.shinn.service.model.Apartment;
import com.shinn.service.model.ElectricBill;
import com.shinn.service.model.RentMessage;
import com.shinn.service.model.Renter;
import com.shinn.service.model.RenterInfo;
import com.shinn.service.model.Room;
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
  private static final Locale ph = new Locale("ph", "PH");
  public static final Integer ALERT_PRIOR_DUE_DATE = -3;


  @Autowired
  ChikkaService chikkaService;
  @Autowired
  SmsDao smsDao;
  @Autowired
  RenterDao renterDao;
  @Autowired
  RentMessageDao rentMessageDao;
  @Autowired
  RentalDao rentalDao;
  @Autowired
  ApartmentDao apartmentDao;
  @Autowired
  RoomDao roomDao;


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
      String msg =
          generateMessage(type, bill.getTotalAmount(), dueDate.getMonth(), null, null, null);
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
      sms.setTimestamp(DateUtil.getCurrentDate().getTime() + "");
      int id = smsDao.saveUpdate(sms);
      sms.setId(id);
      smsDao.commit();
    } catch (Exception e) {
      logger.error("sendMessage: " + e.getMessage(), e);
    }
    return sms;
  }

  private String generateMessage(String messageType, Double amount, int month, String ref,
      String apartment, String room) {
    String message = "";
    NumberFormat formatter = NumberFormat.getCurrencyInstance(ph);
    RentMessage rentMessage = rentMessageDao.findByKey(messageType);
    message = rentMessage.getMessage();
    message = message.replaceAll("\\{amount\\}", formatter.format(amount));
    message = message.replace("{duedate}", DateUtil.getNameOfMonth(month));
    message = message.replace("{reference}", ref);
    message = message.replace("{apartment}", apartment);
    message = message.replace("{room}", room);
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
          if (renter != null && renter.getMobileNo() != null) {
            String message = this.generateMessage(ELECTRIC_BILLING_MESSAGE, bill.getGrossAmount(),
                bill.getDueDate().getMonth(), null, null, null);
            this.sendMessage(message, renter.getMobileNo());
          }

        }
        break;
      case RentStatus.BILL_RENT:
        for (Transaction tx : billingForm.getBills()) {
          if (tx.getRenter() != null && tx.getRenter().getMobileNo() != null) {
            String message = this.generateMessage(BILLING_MESSAGE, tx.getAmountPayable(),
                tx.getDueDate().getMonth(), null, null, null);
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
          if (renter != null && renter.getMobileNo() != null) {
            String message = this.generateMessage(COLLECTION_MESSAGE_ELECTRIC,
                Double.valueOf(billingForm.getCash().getAmountPaid()), bill.getDueDate().getMonth(),
                bill.getCollectionNo(), null, null);
            this.sendMessage(message, renter.getMobileNo());
          }

        }
        break;
      case RentStatus.BILL_RENT:
        for (Transaction tx : billingForm.getBills()) {
          if (tx.getRenter() != null && tx.getRenter().getMobileNo() != null) {
            String message = this.generateMessage(COLLECTION_MESSAGE_PROPERTY,
                Double.valueOf(billingForm.getCash().getAmountPaid()), tx.getDueDate().getMonth(),
                tx.getCollectionNo(), null, null);
            this.sendMessage(message, tx.getRenter().getMobileNo());
          }

        }
        break;
    }
    return resp;
  }

  /**
   * send billing alert to tenant and person incharge of the apartment.
   */
  @Override
  public Response<Transaction> sendBillingAlert() {
    List<Transaction> transactions = rentalDao.findByStatus(RentStatus.ACTIVE);
    try {
      for (Transaction transaction : transactions) {
        int dayDifference = DateUtil.daysDiff(transaction.getDueDate(), DateUtil.getCurrentDate());

        if (dayDifference >= ALERT_PRIOR_DUE_DATE && dayDifference < 1) {
          this.sendAlert(transaction, RentStatus.BEFORE_DUE_MESSAGE);

        }

        if (dayDifference >= 0 && dayDifference <= Math.abs(ALERT_PRIOR_DUE_DATE)) {
          this.sendAlert(transaction, RentStatus.DUE_DATE_MESSAGE);
          this.sendAlertToIncharge(transaction);
        }

        if (dayDifference == 20) {
          // automate generation of new bill;
        }

      }
    } catch (Exception e) {
      logger.error("sendBillingAlert:{}", e.getMessage());
    }
    return null;
  }

  private void sendAlertToIncharge(Transaction transaction) {
    try {
      Double totalAmount = transaction.getAmount() + transaction.getOverdue();
      Apartment apt = apartmentDao.getById(transaction.getAptId());
      Room room = roomDao.getById(transaction.getRoomId());
      String message = this.generateMessage(RentStatus.RENT_ALERT_MESSAGE, totalAmount,
          transaction.getDueDate().getMonth(), transaction.getBillingNo(), apt.getName(),
          room.getRoomDesc());
      this.sendMessage(message, apt.getMobileIncharge());
    } catch (Exception e) {
      logger.error("sendAlertToIncharge:{}", e.getMessage());
    }
  }

  private void sendAlert(Transaction transaction, String messageType) {
    try {
      Apartment apt = apartmentDao.getById(transaction.getAptId());
      Room room = roomDao.getById(transaction.getRoomId());
      Renter renter = renterDao.getById(transaction.getRenterId());
      if (!StringUtil.isNullOrEmpty(renter.getMobileNo())) {
        Double total = transaction.getAmount() + transaction.getOverdue();
        String message =
            this.generateMessage(messageType, total, transaction.getDueDate().getMonth(),
                transaction.getBillingNo(), apt.getName(), room.getRoomDesc());
        this.sendMessage(message, renter.getMobileNo());
      }
    } catch (Exception e) {
      logger.error("sendAlert:{}", e.getMessage());
    }

  }

  @Override
  public Response<ElectricBill> sendElectricBillingAlert() {
    // TODO Auto-generated method stub
    return null;
  }


}
