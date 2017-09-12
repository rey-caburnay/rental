package com.shinn.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.dao.factory.ResultStatus;
import com.shinn.dao.repos.CollectionDao;
import com.shinn.dao.repos.CollectionDetailsDao;
import com.shinn.dao.repos.RentalDao;
import com.shinn.dao.repos.RenterDao;
import com.shinn.dao.repos.RenterInfoDao;
import com.shinn.dao.repos.RoomDao;
import com.shinn.service.model.Collection;
import com.shinn.service.model.CollectionDetails;
import com.shinn.service.model.ElectricBill;
import com.shinn.service.model.Renter;
import com.shinn.service.model.RenterInfo;
import com.shinn.service.model.Room;
import com.shinn.service.model.Transaction;
import com.shinn.ui.model.CollectionForm;
import com.shinn.ui.model.RegistrationForm;
import com.shinn.ui.model.Response;
import com.shinn.ui.model.TransactionDetails;
import com.shinn.util.DateUtil;
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
	@Autowired
	CollectionDao collectionDao;
	@Autowired
	CollectionDetailsDao collectionDetailsDao;
	@Autowired
	RoomDao roomDao;

	/**
	 *
	 */
	public Response<RegistrationForm> createTx(RegistrationForm reg) {
		Response<RegistrationForm> resp = new Response<RegistrationForm>();
		Transaction tx = new Transaction();
		try {
			Renter renter = renterDao.getRenterByName(reg.getLastname(), reg.getFirstname(), reg.getRenterMI());
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
			reg.setRenterId(renter.getId() + "");
			tx.setId(rentalDao.getCurrentKey(Transaction.TABLE_NAME));
			tx.setAptId(StringUtil.toInteger(reg.getAptId()));
			tx.setRoomId(StringUtil.toInteger(reg.getRoomId()));
			tx.setDueDate(StringUtil.toDate(reg.getDueDate(), DateUtil.YYYYMMDD_DASH));
			tx.setTxDate(new Date());
			tx.setStartDate(StringUtil.toDate(reg.getStartDate(), DateUtil.YYYYMMDD_DASH));
			tx.setEndDate(StringUtil.toDate(reg.getEndDate(), DateUtil.YYYYMMDD_DASH));
			tx.setDeposit(StringUtil.toDouble(reg.getDeposit()));
			tx.setRenterId(StringUtil.toInteger(reg.getRenterId()));
			tx.setBalance(StringUtil.toDouble(reg.getBalance()));
			// tx.setTxType(reg.getTxType());
			tx.setProvider(reg.getProvider());
			tx.setAmount(StringUtil.toDouble(reg.getAmount()));
			tx.setStatus(RentStatus.ACTIVE);
			tx.setUserId(1); // TODO required
			rentalDao.saveUpdate(tx);
			resp.setResponseStatus(ResultStatus.RESULT_OK);
			resp.setModel(reg);

			// TODO update the room details
			Room room = roomDao.getById(Integer.parseInt(reg.getRoomId()));
			room.setStatus(RentStatus.OCCUPIED);
			room.setOccupied(Integer.parseInt(reg.getPersonCount()));
			roomDao.saveUpdate(room);

			rentalDao.commit();
		} catch (Exception e) {
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
		Response<RenterInfo> resp = new Response<RenterInfo>();
		List<RenterInfo> renterInfos = renterInfoDao.getRentersInfo(renterId);
		resp.setResponseStatus(ResultStatus.NO_RESULT);
		resp.setErrorMsg(ResultStatus.NO_RESULT);
		if (renterInfos != null && renterInfos.size() > 0) {
			resp.setResponseStatus(ResultStatus.RESULT_OK);
			resp.setResult(renterInfos);
		}

		return resp;
	}

	@Override
	public Response<CollectionForm> createCollection(CollectionForm collectionForm) {
		Response<CollectionForm> resp = new Response<CollectionForm>();
		try {
			Double amountPaid = 0d;
			Double deposit = 0d;
			Double balance = 0d;
			Double cashReceived = 0d;
			Transaction tx = null;
			Double newAmount = 0d;
			int updtCnt = 0;
			Iterator<RenterInfo> itr = collectionForm.getTenants().iterator();
			while (itr.hasNext()) {
			    RenterInfo renterInfo = itr.next();
				// create new record in tx_rental if aptId + roomID + renterId
				//is not found.
				tx = rentalDao.getTransactionByAptRoomRenter(collectionForm.getAptId(), 
				      collectionForm.getRoomId(), renterInfo.getId());
				if (tx == null) {
					tx = new Transaction();
				}
				switch (collectionForm.getPaymentType()) {
				case "visa":
				case "credit":
				case "master":
					break;
				default: // cash
					amountPaid = StringUtil.toDouble(collectionForm.getCash().getAmountPaid());
					balance = StringUtil.toDouble(collectionForm.getCash().getBalance());
					deposit = StringUtil.toDouble(collectionForm.getCash().getDeposit());
					cashReceived = StringUtil.toDouble(collectionForm.getCash().getCashReceived());
				}
				tx.setRenterId(renterInfo.getId());
				tx.setRoomId(collectionForm.getRoomId());
				tx.setUserId(StringUtil.toInteger(collectionForm.getUserId()));
				tx.setDueDate(DateUtil.addDays(renterInfo.getDueDate(),DateUtil.THIRTYDAYS));
				tx.setStartDate(renterInfo.getStartDate());
				tx.setBalance(balance);
				tx.setDeposit(deposit);
				tx.setTxType(collectionForm.getPaymentType());
				tx.setUserId(StringUtil.toInteger(collectionForm.getUserId()));
				tx.setUpdateDate(new Date());
				tx.setTxDate(new Date());
				tx.setUpdtCnt(updtCnt);
				tx.setPaymentStatus(RentStatus.PAID);
				if (balance > 0) {
					tx.setPaymentStatus(RentStatus.PARTIAL);
				} 
				int txId = rentalDao.saveUpdate(tx);
				
				Collection collection = new Collection();
				collection.setTxId(txId);
				collection.setRoomId(collectionForm.getRoomId());
				collection.setAptId(collectionForm.getAptId());
				collection.setTxDate(new Date());
				collection.setRenterId(renterInfo.getId());
				collection.setUserId(StringUtil.toInteger(collectionForm.getUserId()));
				collection.setCashReceived(StringUtil.toDouble(collectionForm.getCash().getCashReceived()));
				collection.setAmountPaid(StringUtil.toDouble(collectionForm.getCash().getAmountPaid()));
				collection.setDeposit(StringUtil.toDouble(collectionForm.getCash().getDeposit()));
				collection.setBalance(StringUtil.toDouble(collectionForm.getCash().getBalance()));
				collection.setCashChange(StringUtil.toDouble(collectionForm.getCash().getCashChange()));
				int collectionId = collectionDao.saveUpdate(collection);
				
				//send email or text message as a receipt.
				
			}
			collectionDao.commit();
			resp.setResponseStatus(ResultStatus.RESULT_OK);
		} catch (Exception e) {
			resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
			resp.setErrorMsg(e.getMessage());
			collectionDetailsDao.rollback();
		}
		return resp;
	}

	@Override
	public Response<Renter> registration(Renter renter) {
		Response<Renter> resp = new Response<Renter>();
		try {
		    renter.setStatus(RentStatus.ACTIVE);
			renter.setId(renterDao.saveUpdate(renter));
			resp.setModel(renter);
			resp.setResponseStatus(ResultStatus.RESULT_OK);
			renterDao.commit();
		} catch (Exception e) {
			resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
			resp.setErrorMsg(e.getMessage());
			renterDao.rollback();
		}
		return resp;
	}

	/**
	 * 
	 * @param form
	 * @param transactions
	 * @return
	 */
	/**
	 * private CollectionForm saveCollection(CollectionForm form, List
	 * <Transaction> transactions) { try { int roomsRented =
	 * form.getTransactions().size(); Double amountPaid =
	 * StringUtil.toDouble(form.getCash().getAmountPaid()); Double cashReceived
	 * = 0d; Double balance = 0d; Double deposit = 0d; Double change = 0d;
	 * Iterator<Transaction> itr = transactions.iterator(); while
	 * (itr.hasNext()) { Transaction tx = itr.next(); Room room = tx.getRoom();
	 * Collection collection = new Collection();
	 * collection.setAptId(tx.getAptId()); collection.setRoomId(tx.getRoomId());
	 * collection.setTxDate(new Date()); collection.setStatus(RentStatus.PAID);
	 * collection.setTxId(tx.getId());
	 * collection.setUserId(StringUtil.toInteger(form.getUserId())); //if
	 * multiple room divide the payments to each room switch
	 * (form.getPaymentType()) { case "visa": case "credit": case "master":
	 * break; case "cash": break; default: //cash if (tx.isFullPaid()) {
	 * amountPaid = amountPaid - tx.getAmount();
	 * collection.setAmountPaid(tx.getAmount()); //set as full paid
	 * collection.setCashReceived(tx.getAmount()); collection.setChange(0d);
	 * collection.setBalance(0d); collection.setDeposit(0d); } else { amountPaid
	 * = amountPaid - tx.getAmount(); if (amountPaid > 0 ) {
	 * 
	 * } collection.setAmountPaid(room.getRate()); //set as full paid
	 * collection.setCashReceived(room.getRate()); collection.setChange(0d);
	 * collection.setBalance(0d); collection.setDeposit(0d); }
	 * 
	 * collection.setAmountPaid(StringUtil.toDouble(form.getCash().getAmountPaid
	 * ())); collection.setCashReceived(StringUtil.toDouble(form.getCash().
	 * getCashRecieved()));
	 * collection.setChange(StringUtil.toDouble(form.getCash().getCashChange()))
	 * ;
	 * collection.setBalance(StringUtil.toDouble(form.getCash().getBalance()));
	 * collection.setDeposit(StringUtil.toDouble(form.getCash().getDeposit()));
	 * } collectionDao.saveUpdate(collection); }
	 * form.getCash().setAmountPaid(amountPaid+""); } catch(Exception e) {
	 * logger.error(e.getMessage()); // collectionDao.rollback(); }
	 * 
	 * return form; }
	 */

}
