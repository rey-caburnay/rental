package com.shinn.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyScheduledTasks {
    private static final SimpleDateFormat dateFormat = 
            new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        
    /**
     * * "0 0 * * * *" = the top of every hour of every day.
     * "*\/10 * * * * *" = every ten seconds.
     * "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
     * "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.
     * "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
     * "0 0 0 25 12 ?" = every Christmas Day at midnight
     * 
     */
        @Scheduled(cron = "0 0 9-10")
        public void run() {

            System.out.println("sendMailToCustomers Job ran at " 
                + dateFormat.format(new Date()));
        }
}
