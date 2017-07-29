package com.shinn.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer.CollectionReferringAccumulator;
import com.shinn.dao.factory.AbstractDaoImpl;
import com.shinn.dao.factory.ResultStatus;
import com.shinn.dao.repos.ApartmentDao;
import com.shinn.dao.repos.ElectricBillDao;
import com.shinn.dao.repos.ElectricProviderDao;
import com.shinn.dao.repos.RentalDao;
import com.shinn.dao.repos.RenterDao;
import com.shinn.dao.repos.RenterInfoDao;
import com.shinn.dao.repos.RoomDao;
import com.shinn.service.model.Apartment;
import com.shinn.service.model.ElectricBill;
import com.shinn.service.model.ElectricProvider;
import com.shinn.service.model.Expense;
import com.shinn.service.model.Renter;
import com.shinn.service.model.RenterInfo;
import com.shinn.ui.model.Response;
import com.shinn.util.DateUtil;
import com.shinn.util.RentStatus;
import com.shinn.service.model.Room;
import com.shinn.service.model.Tenant;
import com.shinn.service.model.Transaction;

/**
 * administrative service for the system
 * 
 * @author shinn
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

  @Autowired
  ApartmentDao apartmentDao;
  @Autowired
  RoomDao roomDao;
  @Autowired
  RenterDao renterDao;
  @Autowired
  RentalDao rentalDao;
  @Autowired
  RenterInfoDao renterInfoDao;
  @Autowired
  ElectricBillDao electricBillDao;
  @Autowired
  ElectricProviderDao electricProviderDao;

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
    } catch (Exception e) {
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
    } catch (Exception e) {
      resp.setErrorMsg(e.getMessage());
      resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
    }
    return resp;
  }


  /*
   * (non-Javadoc)
   * 
   * @see com.shinn.service.AdminService#getApartments()
   */
  @Override
  public Response<Apartment> getApartments() {
    Response<Apartment> resp = new Response<Apartment>();
    try {

      List<Apartment> apartments = apartmentDao.findAll();
      resp.setResult(apartments);
      resp.setResponseStatus(ResultStatus.RESULT_OK);
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
      resp.setErrorMsg(e.getMessage());
      resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
    }
    return resp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.shinn.service.AdminService#getRooms(java.lang.Integer)
   */
  @Override
  public Response<Room> getRooms(Integer aptId) {
    Response<Room> resp = new Response<Room>();
    try {

      List<Room> rooms = roomDao.getRooms(aptId);
      resp.setResult(rooms);
      resp.setResponseStatus(ResultStatus.RESULT_OK);
    } catch (Exception e) {
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
      resp.setResult(rentersInfos);
      resp.setResponseStatus(ResultStatus.RESULT_OK);
    } catch (Exception e) {
      resp.setErrorMsg(e.getMessage());
      resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
    }
    return resp;
  }

  /**
   * get the list of renters in a room
   */
  @Override
  public Response<RenterInfo> getTenant(int aptId, int roomId) {
    Response<RenterInfo> resp = new Response<RenterInfo>();
    try {
      List<RenterInfo> rentersInfos = renterInfoDao.getTenants(aptId, roomId);
      Double overdue = 0d;
      // TODO recompute overdue based on dueDate not paid
      Iterator<RenterInfo> itr = rentersInfos.iterator();
      while (itr.hasNext()) {
        RenterInfo renterInfo = itr.next();
        // set new dueDate
        Date currentDate = new Date();
        // count number of days from previous dueDate.
        int numberOfDays = DateUtil.daysBetween(renterInfo.getDueDate(), currentDate);
        int unbilledMonth = numberOfDays / DateUtil.THIRTYDAYS;
        overdue = renterInfo.getBalance();
        int additionalDays = DateUtil.THIRTYDAYS;
        if (unbilledMonth > 0) {
          overdue += (renterInfo.getAmount() * unbilledMonth);
          renterInfo.setRemarks("Unbilled Month(s): " + unbilledMonth);
          renterInfo.setBalance(overdue);
          additionalDays = unbilledMonth * DateUtil.THIRTYDAYS;
        }
        if (numberOfDays > DateUtil.FOURTYFIVEDAYS) {
          // 45 days not paid will generate additional 1 month
          renterInfo.setDueDate(
              DateUtil.addDays(renterInfo.getDueDate(), DateUtil.THIRTYDAYS + additionalDays));
        }

        // process as paid if deposit is greater than amount.
        if (renterInfo.getAmount() < renterInfo.getDeposit()) {
          renterInfo.setPaymentStatus(RentStatus.PAID);
        }
      }
      resp.setResult(rentersInfos);
      resp.setResponseStatus(ResultStatus.RESULT_OK);
    } catch (Exception e) {
      resp.setErrorMsg(e.getMessage());
      resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
    }
    return resp;
  }

  @Override
  public Response<ElectricBill> getElectricBills(Integer aptId) {
    Response<ElectricBill> resp = new Response<ElectricBill>();
    try {
      List<ElectricBill> electricBills =  electricBillDao.getElectricBillByApt(aptId);
      Iterator<ElectricBill> itr = electricBills.iterator();
      while (itr.hasNext()) {
        ElectricBill electricBill = itr.next();
        electricBill.setElectricProvider(electricProviderDao.getProvider(electricBill.getProvider()));
      }
      resp.setResult(electricBills);
      resp.setResponseStatus(ResultStatus.RESULT_OK);
    } catch (Exception e) {
      resp.setErrorMsg(e.getMessage());
      resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
    }
    return resp;
   
  }

  @Override
  public Response<ElectricProvider> getElectricProvider(String provider) {
    Response<ElectricProvider> resp = new Response<ElectricProvider>();
    try {
      resp.setModel(electricProviderDao.getProvider(provider));
      resp.setResponseStatus(ResultStatus.RESULT_OK);
    } catch (Exception e) {
      resp.setErrorMsg(e.getMessage());
      resp.setResponseStatus(ResultStatus.GENERAL_ERROR);
    }
    return resp;
  }  
  
}
