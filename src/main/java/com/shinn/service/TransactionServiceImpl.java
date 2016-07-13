package com.shinn.service;

import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.dao.factory.ResultStatus;
import com.shinn.dao.repos.RentalDao;
import com.shinn.dao.repos.RenterDao;
import com.shinn.service.model.Renter;
import com.shinn.service.model.Transaction;
import com.shinn.ui.model.Registration;
import com.shinn.ui.model.Response;
import com.shinn.util.RentStatus;
import com.shinn.util.StringUtil;


@Service
public class TransactionServiceImpl implements TransactionService {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AbstractDaoImpl.class);
    @Autowired
    RentalDao rentalDao;
    @Autowired
    RenterDao renterDao;
    
    /**
     * 
     */
    public Response<Registration> createTx(Registration reg)  {
        Response<Registration> resp = new Response<Registration>();
        Transaction tx  = new Transaction();
        try {
            Renter renter = renterDao.getRenterByName(reg.getRenterLastName(), 
                    reg.getRenterLastName(), reg.getRenterMI());
            /** create new record if null **/
            if (renter == null) {
                renter = new Renter();
                renter.setId(renterDao.getCurrentKey(Renter.TABLE_NAME));
                renter.setFirstName(reg.getRenterFirstName());
                renter.setLastName(reg.getRenterLastName());
                renter.setInitial(reg.getRenterMI());
                renter.setMobileNo(reg.getMobileno());
                renter.setIdPresented(reg.getIdPresented());
                renter.setTelno(reg.getTelno());
                renter.setAddress(reg.getAddress());
                renter.setStatus(RentStatus.ACTIVE);
                renterDao.saveUpdate(renter);
                reg.setRenterId(renter.getId()+"");
            }
            tx.setId(rentalDao.getCurrentKey(Transaction.TABLE_NAME));
            tx.setAptId(StringUtil.toInteger(reg.getAptId()));
            tx.setRoomId(StringUtil.toInteger(reg.getRoomId()));
            tx.setDueDate(StringUtil.toDate(reg.getDueDate(),null));
            tx.setTxDate(new Date());
            tx.setStartDate(StringUtil.toDate(reg.getStartDate(),null));
            tx.setEndDate(StringUtil.toDate(reg.getEndDate(),null));
            tx.setDeposit(StringUtil.toDouble(reg.getDeposit()));
            tx.setRenterId(StringUtil.toInteger(reg.getRenterId()));
            tx.setBalance(StringUtil.toDouble(reg.getBalance()));
            tx.setTxType(reg.getTxType());
            tx.setProvider(reg.getProvider());
            tx.setAmount(StringUtil.toDouble(reg.getAmount()));
            tx.setStatus(RentStatus.ACTIVE);
            tx.setUserId(null);
            rentalDao.saveUpdate(tx);
            resp.setResponseStatus(ResultStatus.RESULT_OK);
            resp.setModel(reg);
            
            rentalDao.commit();
        } catch(Exception e) {
            resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
            resp.setErrorMsg(e.getMessage());
            rentalDao.rollback();
        }
        return resp;
    }

}
