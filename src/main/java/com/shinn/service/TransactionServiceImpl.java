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
import com.shinn.dao.repos.RenterInfoDao;
import com.shinn.service.model.Renter;
import com.shinn.service.model.RenterInfo;
import com.shinn.service.model.Transaction;
import com.shinn.ui.model.RegistrationForm;
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
    @Autowired
    RenterInfoDao renterInfoDao;

    /**
     *
     */
    public Response<RegistrationForm> createTx(RegistrationForm reg)  {
        Response<RegistrationForm> resp = new Response<RegistrationForm>();
        Transaction tx  = new Transaction();
        try {
            Renter renter = renterDao.getRenterByName(reg.getLastname(),
                    reg.getFirstname(), reg.getRenterMI());
            /** create new record if null **/
            if (renter == null || renter.getId() == null) {
                renter = new Renter();
                renter.setId(renterDao.getCurrentKey(Renter.TABLE_NAME));
                renter.setFirstName(reg.getFirstname());
                renter.setLastName(reg.getLastname());
                renter.setInitial(reg.getRenterMI());
                renter.setMobileNo(reg.getMobileno());
                renter.setIdPresented(reg.getIdPresented());
                renter.setTelno(reg.getTelno());
                renter.setAddress(reg.getAddress());
                renter.setStatus(RentStatus.ACTIVE);
                renterDao.saveUpdate(renter);

            }
            reg.setRenterId(renter.getId()+"");
            tx.setId(rentalDao.getCurrentKey(Transaction.TABLE_NAME));
            tx.setAptId(StringUtil.toInteger(reg.getAptId()));
            tx.setRoomId(StringUtil.toInteger(reg.getRoomId()));
            tx.setDueDate(StringUtil.toDate(reg.getDueDate(),StringUtil.YYYYMMDD));
            tx.setTxDate(new Date());
            tx.setStartDate(StringUtil.toDate(reg.getStartDate(),StringUtil.YYYYMMDD));
            tx.setEndDate(StringUtil.toDate(reg.getEndDate(),StringUtil.YYYYMMDD));
            tx.setDeposit(StringUtil.toDouble(reg.getDeposit()));
            tx.setRenterId(StringUtil.toInteger(reg.getRenterId()));
            tx.setBalance(StringUtil.toDouble(reg.getBalance()));
//            tx.setTxType(reg.getTxType());
            tx.setProvider(reg.getProvider());
            tx.setAmount(StringUtil.toDouble(reg.getAmount()));
            tx.setStatus(RentStatus.ACTIVE);
            tx.setUserId(1); //TODO required
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

	@Override
	public Response<Transaction> getTxByRenterId(Integer renterId) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public Response<RenterInfo> getRentersInfo(Integer renterId) {
        return null;
    }

//    /**
//     *
//     */
//	@Override
//	public Response<RenterInfo> getTxByRenterId(Integer renterId) {
//		Response<RenterInfo> resp = new Response<RenterInfo>();
//		try {
//			Transaction tx = rentalDao.getTransactionByRenterId(renterId);
//			resp.setmo
//		    resp.setResponseStatus(ResultStatus.RESULT_OK);
//		}catch(Exception e) {
//			resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
//            resp.setErrorMsg(e.getMessage());
//		}
//		return resp;
//	}

}
