package com.shinn.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinn.chikka.ChikkaService;
import com.shinn.chikka.ChikkaServiceImpl;
import com.shinn.chikka.model.ChikkaMessage;
import com.shinn.dao.repos.RentalDao;
import com.shinn.dao.repos.RenterInfoDao;
import com.shinn.dao.repos.SmsDao;
import com.shinn.service.model.RenterInfo;
import com.shinn.ui.model.Response;
import com.shinn.ui.model.Result;
import com.shinn.util.DateUtil;
import com.shinn.util.RentStatus;

@Service
public class NotificationServiceImpl implements NotificationService{


    ChikkaService chikka = new ChikkaServiceImpl();
    
    @Autowired
    SmsDao smsDao;
    @Autowired
    RenterInfoDao renterInfoDao;
    
    @Override
    public Response<Result> notifyRenters() {

        List<RenterInfo> renters = renterInfoDao.getRentersInfo(RentStatus.ACTIVE);
        Iterator<RenterInfo> itr = renters.iterator();
        Date today = Calendar.getInstance().getTime();
        ChikkaMessage message = new ChikkaMessage();

        while(itr.hasNext()) {
            RenterInfo renterInfo = itr.next();
            int daysBeforeDueDate = DateUtil.daysBetween(renterInfo.getDueDate(), today);
            String smsmessage = "";
            if (daysBeforeDueDate == RentStatus.NOTIFY_BEFORE_DUE) {
                smsmessage = RentStatus.BEFORE_DUE_MESSAGE;
                smsmessage.replace("\\{amount\\}", renterInfo.getBalance()+"");
                smsmessage.replace("{duedate}", DateUtil.getNameOfMonth(renterInfo.getDueDate().getMonth()));
            } else if (daysBeforeDueDate == RentStatus.NOTIFY_ON_DUE) {
                smsmessage = RentStatus.DUE_DATE_MESSAGE;
                smsmessage = smsmessage.replaceAll("\\{amount\\}", renterInfo.getBalance() +"");
                smsmessage = smsmessage.replace("{month}", DateUtil.getNameOfMonth(renterInfo.getDueDate().getMonth()));
            }
            message.setMobileNumber(renterInfo.getMobileNumber());
            message.setMessage(smsmessage);
            chikka.sendMessage(message);
        }
        return null;
    }
}
