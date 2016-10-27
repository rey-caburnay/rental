package com.shinn.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinn.dao.factory.ResultStatus;
import com.shinn.dao.repos.ApartmentDao;
import com.shinn.dao.repos.RentalDao;
import com.shinn.dao.repos.RenterDao;
import com.shinn.dao.repos.RoomDao;
import com.shinn.service.model.Apartment;
import com.shinn.service.model.Expense;
import com.shinn.service.model.Renter;
import com.shinn.service.model.RenterInfo;
import com.shinn.ui.model.Response;
import com.shinn.util.RentStatus;
import com.shinn.service.model.Room;
import com.shinn.service.model.Transaction;

/**
 * administrative service for the system
 * @author shinn
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    ApartmentDao apartmentDao;
    @Autowired
    RoomDao roomDao;
    @Autowired
    RenterDao renterDao;
    @Autowired
    RentalDao rentalDao;
    
    /**
     * 
     */
    public Response<Apartment> createApartment(Apartment apt) throws Exception {
        Response<Apartment> resp = new Response<Apartment>();
        try {
            apt.setStatus(RentStatus.ACTIVE);
            apt.setTxDate(Calendar.getInstance().getTime());
            apartmentDao.saveUpdate(apt);
            resp.setModel(apt);
            resp.setResponseStatus(ResultStatus.RESULT_OK);
        }catch(Exception e) {
            resp.setErrorMsg(e.getMessage());
            resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
        }
        return resp;
    }
    /**
     * 
     */
    public Response<Apartment> getApartment(Integer id) throws Exception {
        Response<Apartment> resp = new Response<Apartment>();
        try {
            Apartment apt = apartmentDao.getById(id);
            resp.setModel(apt);
            resp.setResponseStatus(ResultStatus.RESULT_OK);
        }catch(Exception e) {
            resp.setErrorMsg(e.getMessage());
            resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
        }
        return resp;
    }


    /* (non-Javadoc)
     * @see com.shinn.service.AdminService#getApartments()
     */
    @Override
    public Response<Apartment> getApartments() {
      Response<Apartment> resp = new Response<Apartment>();
      try {

          List<Apartment> apartments =  apartmentDao.findAll();
          resp.setResult(apartments);
          resp.setResponseStatus(ResultStatus.RESULT_OK);
      }catch(Exception e) {
          resp.setErrorMsg(e.getMessage());
          resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
      }
      return resp;
    }
    /**
     * 
     */
    public Response<Room> createRoom(Room room) throws Exception {
        Response<Room> resp = new Response<Room>();
        try {
            room.setStatus(RentStatus.ACTIVE);
            roomDao.saveUpdate(room);
            resp.setModel(room);
            resp.setResponseStatus(ResultStatus.RESULT_OK);
        }catch(Exception e) {
            resp.setErrorMsg(e.getMessage());
            resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
        }
        return resp;
    }

    public Response<Room> getRoom(Integer id) throws Exception {
        Response<Room> resp = new Response<Room>();
        try {
            Room room = roomDao.getById(id);
            resp.setModel(room);
            resp.setResponseStatus(ResultStatus.RESULT_OK);
        }catch(Exception e) {
            resp.setErrorMsg(e.getMessage());
            resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
        }
        return resp;
    }

    /* (non-Javadoc)
     * @see com.shinn.service.AdminService#getRooms(java.lang.Integer)
     */
    @Override
    public Response<Room> getRooms(Integer aptId) {
        Response<Room> resp = new Response<Room>();
        try {

            List<Room> rooms =  roomDao.getRooms(aptId);
            resp.setResult(rooms);
            resp.setResponseStatus(ResultStatus.RESULT_OK);
        }catch(Exception e) {
            resp.setErrorMsg(e.getMessage());
            resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
        }
        return resp;
    }

    public Response<Expense> createExpenses(Expense expense) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    public Response<Expense> getExpense(Integer id) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public Response<Renter> getRenters() {
	  Response<Renter> resp = new Response<Renter>();
	  try {
	      List<Renter> rentersInfos = renterDao.getRenters();
	      Iterator<Renter> itr = rentersInfos.iterator();
	        while(itr.hasNext()) {
	            Renter renter = itr.next();
	            List<Transaction> tx = rentalDao.getTransactionByRenterId(renter.getId(), RentStatus.ACTIVE);
	            Iterator<Transaction> itr2 = tx.iterator();
	            while(itr2.hasNext()) {
	                Transaction t = itr2.next();
	                Room room = roomDao.getById(t.getRoomId());
	                t.setRoom(room);
	            }
	            renter.setTransactions(tx);
	        }
	      resp.setResult(rentersInfos);
	      resp.setResponseStatus(ResultStatus.RESULT_OK);
	  }catch(Exception e) {
	      resp.setErrorMsg(e.getMessage());
	      resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
	  }
	  return resp;
	}
}
