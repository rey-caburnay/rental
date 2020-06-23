package com.shinn.scheduler;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.shinn.dao.repos.CollectionDao;
import com.shinn.dao.repos.ElectricBillDao;
import com.shinn.dao.repos.RentalDao;
import com.shinn.service.BillingService;
import com.shinn.service.CollectionService;
import com.shinn.service.SmsService;
import com.shinn.service.model.Collection;
import com.shinn.service.model.ElectricBill;
import com.shinn.service.model.Transaction;
import com.shinn.util.DateUtil;
import com.shinn.util.RentStatus;
@EnableScheduling
@Component
public class MyScheduledTasks {
  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MyScheduledTasks.class);

  @Autowired
  BillingService billingService;
  @Autowired
  CollectionService collectionService;
  @Autowired
  RentalDao rentalDao;
  @Autowired
  ElectricBillDao electricBillDao;
  @Autowired
  CollectionDao collectionDao;
  @Autowired
  SmsService smsService;

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

  /**
   * * "0 0 * * * *" = the top of every hour of every day. "*\/10 * * * * *" = every ten seconds. "0
   * 0 8-10 * * *" = 8, 9 and 10 o'clock of every day. "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30
   * and 10 o'clock every day. "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays "0 0 0 25
   * 12 ?" = every Christmas Day at midnight
   * 
   */
//  @Scheduled(cron = "0 0 10 * * *") // trigger every 10:00 am
   @Scheduled(cron = "*\\/10 * * * * *") //triger every 10 seconds
  public void run() {
    this.processRoomDueDates();
  }

  private void testSked() {
    logger.info("sendNotifications to Tenant Job ran at {}", dateFormat.format(new Date()));

  }

  private void notifyBeforeDueDate() {
     Date threeDaysBefore = DateUtil.addDays(DateUtil.getCurrentDate(), -3);
     String date = DateUtil.formatDate(threeDaysBefore, DateUtil.YYYYMMDD_DASH);
     List<Transaction> transactions = rentalDao.findByDueDateAndStatus(date, RentStatus.ACTIVE);
     for(Transaction transaction : transactions) {
       Collection collection = collectionDao.getByBillingNo(transaction.getBillingNo());
       if (StringUtils.isEmpty(collection)) {
         logger.info("sending {}", transaction);
         smsService.sendAlert(transaction, RentStatus.BEFORE_DUE_MESSAGE);
       }
     }
  }

  private void notifyOnDueDate() {
    String now =  DateUtil.formatDate(DateUtil.getCurrentDate(), DateUtil.YYYYMMDD_DASH);
    logger.info("fetching transaction due date of:" + now);
    List<Transaction> transactions = rentalDao.findByDueDateAndStatus(now, RentStatus.ACTIVE);
    for(Transaction transaction : transactions) {
      Collection collection = collectionDao.getByBillingNo(transaction.getBillingNo());
      if (StringUtils.isEmpty(collection)) {
        logger.info("sending to in charge");
        smsService.sendAlert(transaction, RentStatus.DUE_DATE_MESSAGE);
        smsService.sendAlertToIncharge(transaction);
      }
    }

  }

  private void processRoomDueDates() {
    String now =  DateUtil.formatDate(DateUtil.getCurrentDate(), DateUtil.YYYYMMDD_DASH);
    logger.info("fetching transaction due date of:" + now);
    List<Transaction> transactions = rentalDao.findByDueDateAndStatus(now, RentStatus.ACTIVE);
    logger.info("transactions count :{}", transactions.size());
    for (Transaction transaction : transactions) {
      Integer dayDifference =
          DateUtil.daysBetween(transaction.getDueDate(), DateUtil.getCurrentDate());

      Collection collection = collectionDao.getByBillingNo(transaction.getBillingNo());

      logger.info("transaction to process:{}", transaction);
      if (dayDifference >= RentStatus.NOTIFY_BEFORE_DUE
          && dayDifference <= RentStatus.NOTIFY_ON_DUE) {
        // notify tenant for payment
        if (StringUtils.isEmpty(collection)) {
          logger.info("sending {}", transaction);
          smsService.sendAlert(transaction, RentStatus.BEFORE_DUE_MESSAGE);
        }
      }

      if (dayDifference >= RentStatus.NOTIFY_ON_DUE
          && dayDifference < Math.abs(RentStatus.NOTIFY_BEFORE_DUE)) {
        // notify tenant regarding payment for 2 days
        // notify person incharge to collect the payment
        if (StringUtils.isEmpty(collection)) {
          logger.info("sending to in charge");
          smsService.sendAlert(transaction, RentStatus.DUE_DATE_MESSAGE);
          smsService.sendAlertToIncharge(transaction);
        }
      }

      if (dayDifference.equals(RentStatus.DAYS_TO_GENERATE_BILL)) {
        // create a new billing.
        billingService.createAutomaticBilling(transaction);
      }

      ElectricBill electricBill =
          electricBillDao.getByAptRoom(transaction.getAptId(), transaction.getRoomId());

      if (!StringUtils.isEmpty(electricBill)) {
        dayDifference = DateUtil.daysBetween(electricBill.getDueDate(), DateUtil.getCurrentDate());

        collection = collectionDao.getByBillingNo(electricBill.getBillingNo());

        if (dayDifference >= RentStatus.NOTIFY_BEFORE_DUE
            && dayDifference <= RentStatus.NOTIFY_ON_DUE) {
          if (StringUtils.isEmpty(collection)) {
            logger.info("sending electric bill before due:{}", electricBill);
            smsService.sendAlert(transaction, RentStatus.BEFORE_DUE_ELECTRIC_MESSAGE);
          }
        }

        if (dayDifference >= RentStatus.NOTIFY_ON_DUE
            && dayDifference < Math.abs(RentStatus.NOTIFY_BEFORE_DUE)) {
          // notify tenant regarding payment for 2 days
          // check if it has already paid
          if (StringUtils.isEmpty(collection)) {
            // send notification
            logger.info("sending electric bill on due date:{}", electricBill);
            smsService.sendAlert(transaction, RentStatus.ELECTRIC_BILL_MESSAGE);
          }
        }

      }

    }
  }


}
