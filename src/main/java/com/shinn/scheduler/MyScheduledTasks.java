package com.shinn.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ser.std.CollectionSerializer;
import com.shinn.dao.repos.RentalDao;
import com.shinn.service.BillingService;
import com.shinn.service.BillingServiceImpl;
import com.shinn.service.CollectionService;
import com.shinn.service.model.Transaction;
import com.shinn.util.DateUtil;
import com.shinn.util.RentStatus;

@Component
public class MyScheduledTasks {
    private static final org.slf4j.Logger logger = LoggerFactory
            .getLogger(MyScheduledTasks.class);
    
    @Autowired
    BillingService billingService;
    @Autowired
    CollectionService collectionService;
    @Autowired
    RentalDao rentalDao;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "MM/dd/yyyy HH:mm:ss");

    /**
     * * "0 0 * * * *" = the top of every hour of every day. "*\/10 * * * * *" =
     * every ten seconds. "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
     * "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day. "0
     * 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays "0 0 0 25 12 ?" =
     * every Christmas Day at midnight
     * 
     */
     @Scheduled(cron = "0 0 10 * * *") //trigger every 10:00 am
//    @Scheduled(cron = "*\\/10 * * * * *") //triger every 10 seconds
    public void run() {
        this.processRoomDueDates();
        logger.info("sendNotifications to Tenant Job ran at {}", dateFormat.format(new Date()));
               
    }

    private void processRoomDueDates() {
        List<Transaction> transactions = rentalDao
                .findByStatus(RentStatus.ACTIVE);
        for (Transaction transaction : transactions) {
            Integer dayDifference = DateUtil.daysBetween(
                    transaction.getDueDate(), DateUtil.getCurrentDate());

            if (dayDifference >= RentStatus.NOTIFY_BEFORE_DUE && dayDifference <= RentStatus.NOTIFY_ON_DUE) {
                // notify tenant for payment
            }

            if (dayDifference >= RentStatus.NOTIFY_ON_DUE && dayDifference < Math.abs(RentStatus.NOTIFY_BEFORE_DUE)) {
                // notify tenant regarding payment for 2 days

                // TODO
                // notify person incharge to collect the payment
            }

            if (dayDifference > RentStatus.DAYS_TO_GENERATE_BILL) {
                // create a new billing.
                billingService.createAutomaticBilling(transaction);
            }
        }
    }

}
